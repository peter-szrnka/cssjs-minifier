package hu.szrnkapeter.cssjsminifier.compressor.js;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mozilla.javascript.EvaluatorException;

import hu.szrnkapeter.cssjsminifier.util.JSCompileType;
import hu.szrnkapeter.cssjsminifier.util.LogHandler;
import hu.szrnkapeter.cssjsminifier.util.TestUtils;

class YUIJSCompressorTest {
	
	private LogHandler logHandler = new LogHandler();
	private static final Logger LOGGER = Logger.getLogger(YUIJSCompressor.class.getName());

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
	void test_exception1() throws Exception {
		final YUIJSCompressor compressor = new YUIJSCompressor();
		final File tempFile = File.createTempFile("prefix", "suffix");
		final BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
		final StringBuilder sb = new StringBuilder();
		sb.append("function(str {\r\nalert (str)\r\n}");
		bw.write(sb.toString());
		bw.close();
		
		String absolutePath = tempFile.getAbsolutePath();
		EvaluatorException exception = assertThrows(EvaluatorException.class, () ->  compressor.compress(absolutePath, JSCompileType.SIMPLE));
		assertEquals("Compilation produced 1 syntax errors.", exception.getMessage());

		tempFile.deleteOnExit();
	}

	@Test
	void test_normal() throws Exception {
		final YUIJSCompressor compressor = new YUIJSCompressor();
		final File tempFile = File.createTempFile("prefix", "suffix");
		final BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
		final StringBuilder sb = new StringBuilder();
		sb.append("function(str) {\r\nalert (str);\r\n}");
		bw.write(sb.toString());
		bw.close();
		final String result = compressor.compress(tempFile.getAbsolutePath(), JSCompileType.SIMPLE);

		tempFile.deleteOnExit();

		assertEquals( "function(A){alert(A)};", result);
	}
	
	@Test
	void testFileNotExists() {
		final YUIJSCompressor compressor = new YUIJSCompressor();

		String result = compressor.compress("file-not-exists.txt", JSCompileType.SIMPLE);

		// assert
		assertNull(result);
		TestUtils.assertLogExists(logHandler.getList(), Level.SEVERE, "file-not-exists.txt");
	}
}
