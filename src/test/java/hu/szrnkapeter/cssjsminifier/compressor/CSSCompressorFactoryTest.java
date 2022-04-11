package hu.szrnkapeter.cssjsminifier.compressor;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import hu.szrnkapeter.cssjsminifier.compressor.css.CSSCompressor;
import hu.szrnkapeter.cssjsminifier.compressor.css.YUICSSCompressor;

class CSSCompressorFactoryTest {

	@Test
	void test_Exception() {
		assertThrows(UnsupportedOperationException.class, () ->new CSSCompressorFactory("yuiyui"));
	}

	@Test
	void test_GetYUICompressor() {
		final CSSCompressorFactory factory = new CSSCompressorFactory("yui");
		final CSSCompressor compressor = factory.getCssCompressor();

		assertNotNull(compressor);
	}

	@Test
	void test_Normal() {
		final CSSCompressorFactory factory = new CSSCompressorFactory("yui");
		final CSSCompressor compressor = factory.getCssCompressor();

		assertTrue(compressor instanceof YUICSSCompressor == true);
	}
}
