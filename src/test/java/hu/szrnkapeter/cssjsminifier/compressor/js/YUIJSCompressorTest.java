package hu.szrnkapeter.cssjsminifier.compressor.js;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.junit.Assert;
import org.junit.Test;
import org.mozilla.javascript.EvaluatorException;

import hu.szrnkapeter.cssjsminifier.util.JSCompileType;
import org.junit.Ignore;

@Ignore
public class YUIJSCompressorTest {

	@Test(expected = EvaluatorException.class)
	public void test_exception1() throws Exception {
		final YUIJSCompressor compressor = new YUIJSCompressor();
		final File tempFile = File.createTempFile("prefix", "suffix");
		final BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
		final StringBuilder sb = new StringBuilder();
		sb.append("function(str {\r\nalert (str);\r\n}");
		bw.write(sb.toString());
		bw.close();
		final String result = compressor.compress(tempFile.getAbsolutePath(), JSCompileType.SIMPLE);

		tempFile.deleteOnExit();
	}

	@Test
	public void test_normal() throws Exception {
		final YUIJSCompressor compressor = new YUIJSCompressor();
		final File tempFile = File.createTempFile("prefix", "suffix");
		final BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
		final StringBuilder sb = new StringBuilder();
		sb.append("function(str) {\r\nalert (str);\r\n}");
		bw.write(sb.toString());
		bw.close();
		final String result = compressor.compress(tempFile.getAbsolutePath(), JSCompileType.SIMPLE);

		tempFile.deleteOnExit();

		Assert.assertEquals("Wrong result!", "function(A){alert(A)};", result);
	}
}
