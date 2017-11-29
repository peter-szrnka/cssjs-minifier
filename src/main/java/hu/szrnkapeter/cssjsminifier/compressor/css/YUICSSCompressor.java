package hu.szrnkapeter.cssjsminifier.compressor.css;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import com.yahoo.platform.yui.compressor.CssCompressor;

public class YUICSSCompressor implements CSSCompressor {

	@Override
	public String compress(final String inputFilename) throws Exception {
		Reader in = new InputStreamReader(new FileInputStream(inputFilename), StandardCharsets.UTF_8);
		final CssCompressor compressor = new CssCompressor(in);
		in.close();
		in = null;

		final StringWriter out = new StringWriter();
		compressor.compress(out, -1);
		return out.toString();
	}

}