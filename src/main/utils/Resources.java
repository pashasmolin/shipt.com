package utils;

import java.io.InputStream;
import java.util.Properties;

/**
 * Resources class helps to retrieve test data from project.properties file
 */

public class Resources {

    private static Resources instance;
    private Properties properties;

    private Resources() {
        properties = new Properties();
        try {
            InputStream in = this.getClass().getResourceAsStream("/project.properties");
            properties.load(in);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private String _getValue(String key) {
        return properties.getProperty(key);
    }

    public static Resources getInstance() {
        if (instance == null) instance = new Resources();
        return instance;
    }

    public static String getValue(String key) {
        return Resources.getInstance()._getValue(key);
    }
}
