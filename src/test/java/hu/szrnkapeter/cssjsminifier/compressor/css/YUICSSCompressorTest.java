package hu.szrnkapeter.cssjsminifier.compressor.css;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.junit.jupiter.api.Test;

class YUICSSCompressorTest {

	@Test
	void test() throws Exception {
		final YUICSSCompressor compressor = new YUICSSCompressor();
		final File tempFile = File.createTempFile("prefix", "suffix");
		final BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
		final StringBuilder sb = new StringBuilder();
		sb.append(".custom-style {\r\ncolor:fff;\r\n}\r\n");
		bw.write(sb.toString());
		bw.close();
		final String result = compressor.compress(tempFile.getAbsolutePath());

		tempFile.deleteOnExit();

		assertEquals(".custom-style{color:fff;}", result);
	}
}

