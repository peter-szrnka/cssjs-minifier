package hu.szrnkapeter.cssjsminifier.compressor.js;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.junit.jupiter.api.Test;
import org.mozilla.javascript.EvaluatorException;

import hu.szrnkapeter.cssjsminifier.util.JSCompileType;

class YUIJSCompressorTest {

	@Test
	void test_exception1() throws Exception {
		final YUIJSCompressor compressor = new YUIJSCompressor();
		final File tempFile = File.createTempFile("prefix", "suffix");
		final BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
		final StringBuilder sb = new StringBuilder();
		sb.append("function(str {\r\nalert (str)\r\n}");
		bw.write(sb.toString());
		bw.close();
		
		EvaluatorException exception = assertThrows(EvaluatorException.class, () ->  compressor.compress(tempFile.getAbsolutePath(), JSCompileType.SIMPLE));
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
}
