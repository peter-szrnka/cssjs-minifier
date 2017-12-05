package hu.szrnkapeter.cssjsminifier;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import hu.szrnkapeter.cssjsminifier.compressor.CSSCompressorFactory;
import hu.szrnkapeter.cssjsminifier.compressor.JSCompressorFactory;
import hu.szrnkapeter.cssjsminifier.filter.CustomFileNameFilter;
import hu.szrnkapeter.cssjsminifier.util.Config;
import hu.szrnkapeter.cssjsminifier.util.PropertyUtil;

public class Main {

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
			// Currently it's just merge the css files
			final File folder = new File(config.getCssFolder());
			final CustomFileNameFilter filter = new CustomFileNameFilter("css");

			if (folder.isDirectory() && folder.listFiles(filter).length > 0) {
				long i = 0;
				final BufferedWriter writer = new BufferedWriter(new FileWriter(config.getCssOut()));
				for (final File item : folder.listFiles(filter)) {
					System.out.print("File: " + item.getName() + "; Filesize: " + item.length() / 1024 + "KB");
					if (!item.getName().toLowerCase().contains("min")) {
						writer.append(cssCompressorFactory.getCssCompressor().compress(item.getAbsolutePath()));
						writer.newLine();
						System.out.println(" - compressed");
					} else {
						try (BufferedReader reader = new BufferedReader(new FileReader(item))) {
							String line;
							while ((line = reader.readLine()) != null) {
								writer.append(line);
								writer.newLine();
							}
						}
						System.out.println(" - compress skipped");
					}
					i++;
				}
				writer.close();
				System.out.println("--------------------------------------");
				System.out.println(i + " file added. CSS Output: " + config.getCssOut());
			} else {
				System.out.println("No css file found.");
			}

		}
		if (config.getJsFolder() != null) {
			final File folder = new File(config.getJsFolder());
			System.out.println("JS folder: " + folder.getAbsolutePath());
			final CustomFileNameFilter filter = new CustomFileNameFilter("js");
			long i = 0;

			if (folder.isDirectory() && folder.listFiles(filter).length > 0) {
				final BufferedWriter writer = new BufferedWriter(new FileWriter(config.getJsOut()));
				for (final File item : folder.listFiles(filter)) {

					System.out.print("File: " + item.getName() + "; Filesize: " + item.length() / 1024 + "KB");
					if (!item.getName().toLowerCase().contains("min")) {
						writer.append(jsCompressorFactory.getJsCompressor().compress(item.getAbsolutePath(), config.getJsCompileType()));
						writer.newLine();

						System.out.println(" - compressed");
					} else {
						try (BufferedReader reader = new BufferedReader(new FileReader(item))) {
							String line;
							while ((line = reader.readLine()) != null) {
								writer.append(line);
								writer.newLine();
							}
						}
						System.out.println(" - compress skipped");
					}
					i++;
				}
				writer.close();
				System.out.println("--------------------------------------");
				System.out.println(i + " file added. JS Output: " + config.getJsOut());
			} else {
				System.out.println("No js file found.");
			}
		}
	}
}
