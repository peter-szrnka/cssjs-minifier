package hu.szrnkapeter.cssjsminifier.compressor;

import org.junit.Assert;
import org.junit.Test;

import hu.szrnkapeter.cssjsminifier.compressor.css.CSSCompressor;
import hu.szrnkapeter.cssjsminifier.compressor.css.YUICSSCompressor;

public class CSSCompressorFactoryTest {

	@Test(expected = UnsupportedOperationException.class)
	public void test_Exception() {
		final CSSCompressorFactory factory = new CSSCompressorFactory("yuiyui");
		factory.getCssCompressor();
	}

	@Test
	public void test_GetYUICompressor() {
		final CSSCompressorFactory factory = new CSSCompressorFactory("yui");
		final CSSCompressor compressor = factory.getCssCompressor();

		Assert.assertNotNull("Compressor must not be null!", compressor);
	}

	@Test
	public void test_Normal() {
		final CSSCompressorFactory factory = new CSSCompressorFactory("yui");
		final CSSCompressor compressor = factory.getCssCompressor();

		Assert.assertTrue("The compressor must be an instance of YUICSSCompressor!", compressor instanceof YUICSSCompressor == true);
	}
}
