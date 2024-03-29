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

/**
 * @author Peter Szrnka
 */
public class Main {

	private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

	public static void main(final String[] args) throws Exception {
		final Config config = PropertyUtil.loadProperties();
		final JSCompressorFactory jsCompressorFactory = new JSCompressorFactory(config.getJsCompressor());
		final CSSCompressorFactory cssCompressorFactory = new CSSCompressorFactory(config.getCssCompressor());

		executeCompression("css", config.getCssFolder(), config.getCssOut(), path -> 
			cssCompressorFactory.getCssCompressor().compress(path));
		executeCompression("js", config.getJsFolder(), config.getJsOut(), path -> 
			jsCompressorFactory.getJsCompressor().compress(path, config.getJsCompileType()));
	}
	
	private static void executeCompression(String scope, String folderPath, String outPath, UnaryOperator<String> supplyFunction) throws IOException {
		if (folderPath == null) {
			return;
		}
		
		final File folder = new File(folderPath);
		LOGGER.info(() -> String.format("%s folder: %s", scope.toUpperCase(), folderPath));
		final CustomFileNameFilter filter = new CustomFileNameFilter(scope);

		if (!folder.isDirectory() || folder.listFiles(filter).length == 0) {
			LOGGER.info(() -> String.format("No %s file found.", scope));
			return;
			
		}

		iterateFiles(scope, folder, folderPath, outPath, supplyFunction, filter);
	}
	
	private static void iterateFiles(String scope, File folder, String folderPath, String outPath, UnaryOperator<String> supplyFunction, CustomFileNameFilter filter) throws IOException {
		AtomicLong i = new AtomicLong(0);

		try(BufferedWriter writer = new BufferedWriter(new FileWriter(outPath))) {
			for (final File item : folder.listFiles(filter)) {
				LOGGER.info(() -> String.format("File: %s; Filesize: %s KB", item.getName(), (item.length() / 1024)));
				if (!item.getName().toLowerCase().endsWith(".min." + scope)) {
					writer.append(supplyFunction.apply(item.getAbsolutePath()));
					writer.newLine();
					LOGGER.info(" - compressed");
				} else {
					handleUncompressedFile(writer, item);
					LOGGER.info(" - compress skipped");
				}

				i.incrementAndGet();
			}
		}
		
		LOGGER.info("--------------------------------------");
		LOGGER.info(() -> String.format("%d file added. %s Outpout: %s", i.get(), scope, outPath));
	}
	
	private static void handleUncompressedFile(BufferedWriter writer, File item) throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(item))) {
			String line;
			while ((line = reader.readLine()) != null) {
				writer.append(line);
				writer.newLine();
			}
		}
	}
}
