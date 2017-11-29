package hu.szrnkapeter.cssjsminifier.compressor;

import hu.szrnkapeter.cssjsminifier.compressor.css.CSSCompressor;
import hu.szrnkapeter.cssjsminifier.compressor.css.YUICSSCompressor;

public class CSSCompressorFactory {

	private CSSCompressor cssCompressor;

	public CSSCompressorFactory(final String method) {
		if ("yui".equals(method)) {
			cssCompressor = new YUICSSCompressor();
		} else {
			throw new UnsupportedOperationException("The given css compressor method(" + method + ") is not supported!");
		}
	}

	public CSSCompressor getCssCompressor() {
		return cssCompressor;
	}
}
