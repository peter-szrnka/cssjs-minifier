package hu.szrnkapeter.cssjsminifier.filter;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

public class CustomFileNameFilterTest {

	private static final String EXTENSION1 = "css";
	private static final String EXTENSION2 = "js";

	@Test
	public void test_negative() {
		final CustomFileNameFilter filter = new CustomFileNameFilter(EXTENSION1);
		final boolean result = filter.accept(new File("."), "test_file1.java");

		Assert.assertFalse("The result cannot be true!", result);
	}

	@Test
	public void test_positive1() {
		final CustomFileNameFilter filter = new CustomFileNameFilter(EXTENSION1);
		final boolean result = filter.accept(new File("."), "test_file1.cSs");

		Assert.assertTrue("The result cannot be false!", result);
	}

	@Test
	public void test_positive2() {
		final CustomFileNameFilter filter = new CustomFileNameFilter(EXTENSION2);
		final boolean result = filter.accept(new File("."), "UPPERCASEFILE.JS");

		Assert.assertTrue("The result cannot be false!", result);
	}
}
