package hu.szrnkapeter.cssjsminifier;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.UnaryOperator;
import java.util.logging.Logger;

import hu.szrnkapeter.cssjsminifier.compressor.CSSCompressorFactory;
import hu.szrnkapeter.cssjsminifier.compressor.JSCompressorFactory;
import hu.szrnkapeter.cssjsminifier.filter.CustomFileNameFilter;
import hu.szrnkapeter.cssjsminifier.util.Config;
import hu.szrnkapeter.cssjsminifier.util.PropertyUtil;

public class Main {

	private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

	/**
	 * 1. argument: Directory of js files
	 * 2. argument: optimization mode [whitespace, simple, advanced]
	 * @param args
	 * @throws Exception
	 */
	public static void main(final String[] args) throws Exception {
		final Config config = PropertyUtil.loadProperties();
		final JSCompressorFactory jsCompressorFactory = new JSCompressorFactory(config.getJsCompressor());
		final CSSCompressorFactory cssCompressorFactory = new CSSCompressorFactory(config.getCssCompressor());

		if (config.getCssFolder() != null) {
			executeCompression("css", config.getCssFolder(), config.getCssOut(), path -> {
				try {
					return cssCompressorFactory.getCssCompressor().compress(path);
				} catch (Exception e) {
					LOGGER.warning("Unexpected exception occured!" + e.getMessage());
				}
				
				return null;
			});
		}
		if (config.getJsFolder() != null) {
			executeCompression("js", config.getJsFolder(), config.getJsOut(), path -> {
				try {
					return jsCompressorFactory.getJsCompressor().compress(path, config.getJsCompileType());
				} catch (Exception e) {
					LOGGER.warning("Unexpected exception occured!" + e.getMessage());
				}
				
				return null;
			});
		}
	}
	
	private static void executeCompression(String scope, String folderPath, String outPath, UnaryOperator<String> supplyFunction) throws IOException {
		final File folder = new File(folderPath);
		LOGGER.info(() -> String.format("%s folder: %s", scope.toUpperCase(), folderPath));
		final CustomFileNameFilter filter = new CustomFileNameFilter(scope);
		AtomicLong i = new AtomicLong(0);

		if (folder.isDirectory() && folder.listFiles(filter).length > 0) {
			try(BufferedWriter writer = new BufferedWriter(new FileWriter(outPath))) {
				for (final File item : folder.listFiles(filter)) {

					LOGGER.info(() -> String.format("File: %s; Filesize: %s KB", item.getName(), (item.length() / 1024)));
					if (!item.getName().toLowerCase().endsWith(".min." + scope)) {
						writer.append(supplyFunction.apply(item.getAbsolutePath()));
						writer.newLine();

						LOGGER.info(" - compressed");
					} else {
						try (BufferedReader reader = new BufferedReader(new FileReader(item))) {
							String line;
							while ((line = reader.readLine()) != null) {
								writer.append(line);
								writer.newLine();
							}
						}
						LOGGER.info(" - compress skipped");
					}

					i.incrementAndGet();
				}
			}

			LOGGER.info("--------------------------------------");
			LOGGER.info(() -> String.format("%d file added. %s Outpout: %s", i.get(), scope, outPath));
		} else {
			LOGGER.info(() -> String.format("No %s file found.", scope));
		}
	}
}
