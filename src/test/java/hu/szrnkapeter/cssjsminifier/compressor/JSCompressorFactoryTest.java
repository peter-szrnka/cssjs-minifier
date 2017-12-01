package hu.szrnkapeter.cssjsminifier.compressor;

import org.junit.Assert;
import org.junit.Test;

import hu.szrnkapeter.cssjsminifier.compressor.js.GoogleClosureCompilerJSCompressor;
import hu.szrnkapeter.cssjsminifier.compressor.js.JSCompressor;
import hu.szrnkapeter.cssjsminifier.compressor.js.YUIJSCompressor;

public class JSCompressorFactoryTest {

	@Test(expected = UnsupportedOperationException.class)
	public void test_Exception() {
		final JSCompressorFactory factory = new JSCompressorFactory("yuiyui");
		factory.getJsCompressor();
	}

	@Test
	public void test_GetCompressor() {
		final JSCompressorFactory factory = new JSCompressorFactory("yui");
		final JSCompressor compressor = factory.getJsCompressor();

		Assert.assertNotNull("Compressor must not be null!", compressor);
	}

	@Test
	public void test_Normal_GoogleClosureCompilerJSCompressor() {
		final JSCompressorFactory factory = new JSCompressorFactory("closurecompiler");
		final JSCompressor compressor = factory.getJsCompressor();

		Assert.assertTrue("The compressor must be an instance of GoogleClosureCompilerJSCompressor!",
				compressor instanceof GoogleClosureCompilerJSCompressor == true);
	}

	@Test
	public void test_Normal_YUIJSCompressor() {
		final JSCompressorFactory factory = new JSCompressorFactory("yui");
		final JSCompressor compressor = factory.getJsCompressor();

		Assert.assertTrue("The compressor must be an instance of YUIJSCompressor!", compressor instanceof YUIJSCompressor == true);
	}
}
