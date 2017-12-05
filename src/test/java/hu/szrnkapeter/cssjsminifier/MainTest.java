package hu.szrnkapeter.cssjsminifier;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Test;

public class MainTest {

	@Test
	public void test() throws Exception {
		final String[] strings = new String[0];

		final Path tempDir = Files.createTempDirectory("js");

		final Path tempFile = Files.createTempFile(tempDir, "prefix", "js");
		final BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile.toFile()));
		final StringBuilder sb = new StringBuilder();
		sb.append("var doit = function(str) {\r\nalert (str);\r\n};doit();");
		bw.write(sb.toString());
		bw.close();

		Main.main(strings);

		Files.delete(tempFile);
		Files.deleteIfExists(tempDir);
	}
}
