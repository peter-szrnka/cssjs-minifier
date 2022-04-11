package hu.szrnkapeter.cssjsminifier;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hu.szrnkapeter.cssjsminifier.util.TestLogHandler;
import hu.szrnkapeter.cssjsminifier.util.TestUtils;

class MainTest {
	
	private TestLogHandler logHandler = new TestLogHandler();
	private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
	
	@BeforeEach
	void setup() {
		LOGGER.addHandler(logHandler);
	}
	
	@AfterEach
	void teardown() {
		logHandler.clearRecords();
		LOGGER.removeHandler(logHandler);
	}

	@Test
	void test_CSSwithfiles() throws Exception {
		final String[] strings = new String[0];

		final Path currentDir = FileSystems.getDefault().getPath(new File("").getAbsolutePath() + "/testcss/");
		final Path tempDir = Files.exists(currentDir) ? currentDir : Files.createDirectory(currentDir);

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

		final File outFile = new File("out.test.min.css");
		outFile.delete();
		Files.deleteIfExists(tempMinifiedFile);
		Files.deleteIfExists(tempFile);
		Files.deleteIfExists(tempDir);
		
		TestUtils.assertLogExists(logHandler.getList(), Level.INFO, "CSS folder");
		TestUtils.assertLogExists(logHandler.getList(), Level.INFO, "JS folder");
	}

	@Test
	void test_JSwithfiles() throws Exception {
		final String[] strings = new String[0];

		final Path currentDir = FileSystems.getDefault().getPath(new File("").getAbsolutePath() + "/testjs/");
		final Path tempDir = Files.exists(currentDir) ? currentDir : Files.createDirectory(currentDir);

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

		final File outFile = new File("out.test.min.js");
		outFile.delete();
		Files.deleteIfExists(tempMinifiedFile);
		Files.deleteIfExists(tempFile);
		Files.deleteIfExists(tempDir);
		
		TestUtils.assertLogExists(logHandler.getList(), Level.INFO, "CSS folder");
		TestUtils.assertLogExists(logHandler.getList(), Level.INFO, "JS folder");
	}

	@Test
	void test_JSwithoutfiles() throws Exception {
		final String[] strings = new String[0];

		final Path currentDir = FileSystems.getDefault().getPath(new File("").getAbsolutePath() + "/testjs/");
		final Path tempDir = Files.exists(currentDir) ? currentDir : Files.createDirectory(currentDir);

		Main.main(strings);
		Files.deleteIfExists(tempDir);
		
		TestUtils.assertLogExists(logHandler.getList(), Level.INFO, "CSS folder");
		TestUtils.assertLogExists(logHandler.getList(), Level.INFO, "JS folder");
	}
}
