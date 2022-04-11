package hu.szrnkapeter.cssjsminifier.util;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PropertyUtilTest {

	@Test
	void testPrivateConstructor() {
		assertDoesNotThrow(() -> TestUtils.testPrivateConstructor(PropertyUtil.class));
	}

	@Test
	void test_withoutPropertyFile() {
		final Config config = PropertyUtil.loadProperties("test.properties");
		assertEquals(JSCompileType.SIMPLE, config.getJsCompileType());
	}

	@Test
	void test_withPropertyFile() {
		final Config config = PropertyUtil.loadProperties();

		assertEquals(JSCompileType.ADVANCED, config.getJsCompileType());
	}
}
