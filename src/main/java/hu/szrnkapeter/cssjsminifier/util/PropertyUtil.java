package hu.szrnkapeter.cssjsminifier.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * @author Peter Szrnka
 */
public class PropertyUtil {
	
	private static final Logger LOGGER = Logger.getLogger(PropertyUtil.class.getName());
	
	private PropertyUtil() {}

    public static Config loadProperties() {
        return loadProperties("config.properties");
    }

    public static Config loadProperties(String propertyFile) {
        final Properties prop = new Properties();

        if (PropertyUtil.class.getClassLoader().getResourceAsStream(propertyFile) == null) {
        	LOGGER.info(() -> "No configuration file exists.");
            return getDefaultConfig();
        }

        final Config config = new Config();
        try (InputStream input = PropertyUtil.class.getClassLoader().getResourceAsStream(propertyFile)) {
            prop.load(input);
            config.setJsCompressor(prop.getProperty("jscompressor"));
            config.setJsFolder(prop.getProperty("jsfolder"));
            config.setJsOut(prop.getProperty("jsout"));
            config.setJsCompileType(JSCompileType.valueOf(prop.getProperty("jscompiletype")));
            config.setCssCompressor(prop.getProperty("csscompressor"));
            config.setCssFolder(prop.getProperty("cssfolder"));
            config.setCssOut(prop.getProperty("cssout"));

        } catch (final IOException ex) {
        	LOGGER.severe(ex::getMessage);
        }

        return config;
    }

    private static Config getDefaultConfig() {
    	final Config config = new Config();
    	config.setJsCompressor("yui");
        config.setJsFolder(".");
        config.setJsOut("./out.min.js");
        config.setJsCompileType(JSCompileType.SIMPLE);
        config.setCssCompressor("yui");
        config.setCssFolder(".");
        config.setCssOut("./out.min.css");
        return config;
    }
}
