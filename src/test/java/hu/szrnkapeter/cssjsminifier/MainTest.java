package hu.szrnkapeter.cssjsminifier;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Test;

public class MainTest {

	@Test
	public void test_CSSwithfiles() throws Exception {
		final String[] strings = new String[0];

		final Path currentDir = FileSystems.getDefault().getPath(new File("").getAbsolutePath() + "/testcss/");
		final Path tempDir = Files.createDirectory(currentDir);

		final Path tempFile = Files.createTempFile(tempDir, "prefix", ".css");
		final Path tempMinifiedFile = Files.createTempFile(tempDir, "prefix", ".min.css");

		final BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile.toFile()));
		final StringBuilder sb = new StringBuilder();
		sb.append(".custom-50 {\r\nbackground-color:#fc0;\r\n}\r\n");
		bw.write(sb.toString());
		bw.close();

		final BufferedWriter bw2 = new BufferedWriter(new FileWriter(tempMinifiedFile.toFile()));
		bw2.write(sb.toString());
		bw2.close();

		Main.main(strings);

		new File("out.test.min.css").deleteOnExit();
		Files.deleteIfExists(tempMinifiedFile);
		Files.deleteIfExists(tempFile);
		Files.deleteIfExists(tempDir);
	}

	@Test
	public void test_JSwithfiles() throws Exception {
		final String[] strings = new String[0];

		final Path currentDir = FileSystems.getDefault().getPath(new File("").getAbsolutePath() + "/testjs/");
		final Path tempDir = Files.createDirectory(currentDir);

		final Path tempFile = Files.createTempFile(tempDir, "prefix", ".js");
		final Path tempMinifiedFile = Files.createTempFile(tempDir, "prefix", ".min.js");

		final BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile.toFile()));
		final StringBuilder sb = new StringBuilder();
		sb.append("var doit = function(str) {\r\nalert (str);\r\n};doit();");
		bw.write(sb.toString());
		bw.close();

		final BufferedWriter bw2 = new BufferedWriter(new FileWriter(tempMinifiedFile.toFile()));
		bw2.write(sb.toString());
		bw2.close();

		Main.main(strings);

		new File("out.test.min.js").deleteOnExit();
		Files.deleteIfExists(tempMinifiedFile);
		Files.deleteIfExists(tempFile);
		Files.deleteIfExists(tempDir);
	}

	@Test
	public void test_JSwithoutfiles() throws Exception {
		final String[] strings = new String[0];

		final Path currentDir = FileSystems.getDefault().getPath(new File("").getAbsolutePath() + "/testjs/");
		final Path tempDir = Files.createDirectory(currentDir);

		Main.main(strings);

		new File("out.test.min.js").deleteOnExit();
		Files.deleteIfExists(tempDir);
	}
}
