package jm.task.core.jdbc.util.properties;

import java.util.Properties;

public final class PropertiesUtil {
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }


    private PropertiesUtil() {

    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

    private static void loadProperties() {
        try {
            PROPERTIES.load(PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
