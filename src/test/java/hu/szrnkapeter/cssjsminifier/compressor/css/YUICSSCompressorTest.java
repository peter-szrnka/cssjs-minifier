package hu.szrnkapeter.cssjsminifier.compressor.css;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hu.szrnkapeter.cssjsminifier.util.LogHandler;
import hu.szrnkapeter.cssjsminifier.util.TestUtils;

class YUICSSCompressorTest {

	private LogHandler logHandler = new LogHandler();
	private static final Logger LOGGER = Logger.getLogger(YUICSSCompressor.class.getName());

	@BeforeEach
	void setup() {
		LOGGER.addHandler(logHandler);
	}

	@AfterEach
	void teardown() {
		logHandler.clearRecords();
		LOGGER.removeHandler(logHandler);
	}

	@Test
	void test() throws Exception {
		final YUICSSCompressor compressor = new YUICSSCompressor();
		final File tempFile = File.createTempFile("prefix", "suffix");
		final BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
		final StringBuilder sb = new StringBuilder();
		sb.append(".custom-style {\r\ncolor:fff;\r\n}\r\n");
		bw.write(sb.toString());
		bw.close();
		final String result = compressor.compress(tempFile.getAbsolutePath());

		tempFile.deleteOnExit();

		assertEquals(".custom-style{color:fff;}", result);
	}

	@Test
	void testFileNotExists() {
		final YUICSSCompressor compressor = new YUICSSCompressor();

		String result = compressor.compress("file-not-exists.txt");

		// assert
		assertNull(result);
		TestUtils.assertLogExists(logHandler.getList(), Level.SEVERE, "file-not-exists.txt");
	}
}
