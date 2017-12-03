package hu.szrnkapeter.cssjsminifier.util;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class PropertyUtilTest {

	@Test
	public void test_withoutPropertyFile() {
		final Config config = PropertyUtil.loadProperties("test.properties");
		Assert.assertEquals("Wrong js compile type!", JSCompileType.SIMPLE, config.getJsCompileType());
	}

	@Test
	public void test_withPropertyFile() {
		final Config config = PropertyUtil.loadProperties();

		Assert.assertEquals("Wrong js compile type!", JSCompileType.ADVANCED, config.getJsCompileType());
	}
}
