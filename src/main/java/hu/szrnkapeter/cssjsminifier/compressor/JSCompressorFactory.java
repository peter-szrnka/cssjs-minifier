package hu.szrnkapeter.cssjsminifier.compressor;

import hu.szrnkapeter.cssjsminifier.compressor.js.GoogleClosureCompilerJSCompressor;
import hu.szrnkapeter.cssjsminifier.compressor.js.JSCompressor;
import hu.szrnkapeter.cssjsminifier.compressor.js.YUIJSCompressor;

public class JSCompressorFactory {

	private JSCompressor jsCompressor;

	public JSCompressorFactory(final String method) {
		if ("yui".equals(method)) {
			jsCompressor = new YUIJSCompressor();
		} else if ("closurecompiler".equals(method)) {
			jsCompressor = new GoogleClosureCompilerJSCompressor();
		} else {
			throw new UnsupportedOperationException("The given js compressor method(" + method + ") is not supported!");
		}
	}

	public JSCompressor getJsCompressor() {
		return jsCompressor;
	}
}
