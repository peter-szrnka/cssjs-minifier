package hu.szrnkapeter.cssjsminifier.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {

    public static Config loadProperties() {
        return loadProperties("config.properties");
    }

    public static Config loadProperties(String propertyFile) {
        final Config config = new Config();
        final Properties prop = new Properties();
        InputStream input = null;

        if (PropertyUtil.class.getClassLoader().getResource(propertyFile) == null) {
            System.out.println("No configuration file exists.");
            config.setJsCompressor("yui");
            config.setJsFolder(".");
            config.setJsOut("./out.min.js");
            config.setJsCompileType(JSCompileType.SIMPLE);
            config.setCssCompressor("yui");
            config.setCssFolder(".");
            config.setCssOut("./out.min.css");
            return config;
        }

        try {
            input = PropertyUtil.class.getClassLoader().getResourceAsStream(propertyFile);
            prop.load(input);

            config.setJsCompressor(prop.getProperty("jscompressor"));
            config.setJsFolder(prop.getProperty("jsfolder"));
            config.setJsOut(prop.getProperty("jsout"));
            config.setJsCompileType(JSCompileType.valueOf(prop.getProperty("jscompiletype")));
            config.setCssCompressor(prop.getProperty("csscompressor"));
            config.setCssFolder(prop.getProperty("cssfolder"));
            config.setCssOut(prop.getProperty("cssout"));

        } catch (final IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return config;
    }
}
