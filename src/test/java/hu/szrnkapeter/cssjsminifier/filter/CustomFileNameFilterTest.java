package hu.szrnkapeter.cssjsminifier.filter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;

public class CustomFileNameFilterTest {

    private static final String EXTENSION1 = "css";
    private static final String EXTENSION2 = "js";

    @Test
    void test_negative() {
        final CustomFileNameFilter filter = new CustomFileNameFilter(EXTENSION1);
        final boolean result = filter.accept(new File("."), "test_file1.java");

        assertFalse(result);
    }

    @Test
    void test_positive1() {
        final CustomFileNameFilter filter = new CustomFileNameFilter(EXTENSION1);
        final boolean result = filter.accept(new File("."), "test_file1.cSs");

        assertTrue(result);
    }

    @Test
    void test_positive2() {
        final CustomFileNameFilter filter = new CustomFileNameFilter(EXTENSION2);
        final boolean result = filter.accept(new File("."), "UPPERCASEFILE.JS");

        assertTrue(result);
    }
}
