package hu.szrnkapeter.cssjsminifier.compressor;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import hu.szrnkapeter.cssjsminifier.compressor.js.GoogleClosureCompilerJSCompressor;
import hu.szrnkapeter.cssjsminifier.compressor.js.JSCompressor;
import hu.szrnkapeter.cssjsminifier.compressor.js.YUIJSCompressor;

public class JSCompressorFactoryTest {

	@Test
	void test_Exception() {
		final JSCompressorFactory factory = new JSCompressorFactory("yuiyui");
		assertThrows(UnsupportedOperationException.class, () -> factory.getJsCompressor());
	}

	@Test
	void test_GetCompressor() {
		final JSCompressorFactory factory = new JSCompressorFactory("yui");
		final JSCompressor compressor = factory.getJsCompressor();

		assertNotNull(compressor);
	}

	@Test
	void test_Normal_GoogleClosureCompilerJSCompressor() {
		final JSCompressorFactory factory = new JSCompressorFactory("closurecompiler");
		final JSCompressor compressor = factory.getJsCompressor();

		assertTrue(compressor instanceof GoogleClosureCompilerJSCompressor);
	}

	@Test
	void test_Normal_YUIJSCompressor() {
		final JSCompressorFactory factory = new JSCompressorFactory("yui");
		final JSCompressor compressor = factory.getJsCompressor();

		assertTrue(compressor instanceof YUIJSCompressor);
	}
}
