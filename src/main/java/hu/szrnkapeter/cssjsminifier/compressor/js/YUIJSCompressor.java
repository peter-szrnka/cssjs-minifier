package hu.szrnkapeter.cssjsminifier.compressor.js;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import com.yahoo.platform.yui.compressor.JavaScriptCompressor;

import hu.szrnkapeter.cssjsminifier.util.JSCompileType;

public class YUIJSCompressor implements JSCompressor {

	@Override
	public String compress(final String inputFilename, final JSCompileType compileType) throws Exception {
		Reader in = new InputStreamReader(new FileInputStream(inputFilename), StandardCharsets.UTF_8);
		final JavaScriptCompressor compressor = new JavaScriptCompressor(in, new YuiCompressorErrorReporter());
		in.close();

		final StringWriter out = new StringWriter();
		compressor.compress(out, -1, true, false, false, false);

		return out.toString();
	}
}
