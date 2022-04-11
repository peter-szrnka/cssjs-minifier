package hu.szrnkapeter.cssjsminifier.compressor.css;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

import com.yahoo.platform.yui.compressor.CssCompressor;

public class YUICSSCompressor implements CSSCompressor {
	
	private static final Logger LOGGER = Logger.getLogger(YUICSSCompressor.class.getName());

	@Override
	public String compress(final String inputFilename) {
		Reader in;
		try {
			in = new InputStreamReader(new FileInputStream(inputFilename), StandardCharsets.UTF_8);
			final CssCompressor compressor = new CssCompressor(in);
			in.close();

			final StringWriter out = new StringWriter();
			compressor.compress(out, -1);
			return out.toString();
		} catch (IOException e) {
			LOGGER.severe(e.getMessage());
		}

		return null;
	}
}