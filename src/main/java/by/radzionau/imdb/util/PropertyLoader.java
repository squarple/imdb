package by.radzionau.imdb.util;

import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {

    private PropertyLoader() {

    }

    public static Properties loadProperty(String path) throws IOException {
        Properties properties = new Properties();
        properties.load(PropertyLoader.class.getClassLoader().getResourceAsStream(path));
        return properties;
    }
}
