package hu.szrnkapeter.cssjsminifier.compressor.js;

import hu.szrnkapeter.cssjsminifier.util.JSCompileType;

public interface JSCompressor {
	String compress(final String inputFilename, JSCompileType compileType);
}