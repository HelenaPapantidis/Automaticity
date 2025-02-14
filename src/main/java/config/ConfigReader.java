package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    public static  Properties properties;

    static {
        try {
            properties = new Properties();
            InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties");
            properties.load(inputStream);
            assert inputStream != null;
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException("Ne može da učita config.properties fajl!", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }


}






