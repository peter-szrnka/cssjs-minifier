package hu.szrnkapeter.cssjsminifier.compressor.js;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

import com.yahoo.platform.yui.compressor.JavaScriptCompressor;

import hu.szrnkapeter.cssjsminifier.util.JSCompileType;

public class YUIJSCompressor implements JSCompressor {
	
	private static final Logger LOGGER = Logger.getLogger(YUIJSCompressor.class.getName());

	@Override
	public String compress(final String inputFilename, final JSCompileType compileType) {
		Reader in;
		try {
			in = new InputStreamReader(new FileInputStream(inputFilename), StandardCharsets.UTF_8);
			
			final JavaScriptCompressor compressor = new JavaScriptCompressor(in, new YuiCompressorErrorReporter());
			in.close();

			final StringWriter out = new StringWriter();
			compressor.compress(out, -1, true, false, false, false);

			return out.toString();
		} catch (IOException e) {
			LOGGER.severe(e.getMessage());
		}

		return null;
		
	}
}
