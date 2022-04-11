package hu.szrnkapeter.cssjsminifier.compressor.js;



import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hu.szrnkapeter.cssjsminifier.util.LogHandler;
import hu.szrnkapeter.cssjsminifier.util.TestUtils;


class YuiCompressorErrorReporterTest {
	
	private YuiCompressorErrorReporter reporter = new YuiCompressorErrorReporter();
	private LogHandler logHandler = new LogHandler();
	private static final Logger LOGGER = Logger.getLogger(YuiCompressorErrorReporter.class.getName());
	
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
	void testErrorWithLineLeZero() {
		// act
		reporter.error("Message XYZ", "ClassOne.java", -10, "source", 0);
		
		// assert
		TestUtils.assertLogExists(logHandler.getList(), Level.SEVERE, "Message XYZ");
	}
	
	@Test
	void testErrorWithLineGtZero() {
		// act
		reporter.error("Message XYZ", "ClassOne.java", 10, "source", 0);
		
		// assert
		TestUtils.assertLogExists(logHandler.getList(), Level.SEVERE, "10: 0: Message XYZ");
	}
	
	@Test
	void testWarningWithLineLeZero() {
		// act
		reporter.warning("Message XYZ", "ClassOne.java", -10, "source", 0);
		
		// assert
		TestUtils.assertLogExists(logHandler.getList(), Level.WARNING, "Message XYZ");
	}
	
	@Test
	void testWarningWithLineGtZero() {
		// act
		reporter.warning("Message XYZ", "ClassOne.java", 10, "source", 0);
		
		// assert
		TestUtils.assertLogExists(logHandler.getList(), Level.WARNING, "10: 0: Message XYZ");
	}
	
	@Test
	void testRuntimeError() {
		// act
		reporter.runtimeError("Message XYZ", "ClassOne.java", -10, "source", 0);
		
		// assert
		TestUtils.assertLogExists(logHandler.getList(), Level.SEVERE, "Message XYZ");
	}
}