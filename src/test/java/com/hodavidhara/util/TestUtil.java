package com.hodavidhara.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;


/**
 *
 */
public class TestUtil {

    public static InputStream getRandomImageStream() {

        URL url;
        try {
            url = new URL("http://lorempixel.com/400/200/");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        try {
            return url.openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getDataRootPath() {
        String dataDir = getProperty("data.directory");

        return Paths.get(System.getProperty("user.dir"), dataDir).toString();
    }

    public static String buildFullPath(String relativePath) {
        return Paths.get(getDataRootPath(), relativePath).toString();
    }

    public static String getProperty(String key) {
        Resource resource = new ClassPathResource("test.properties");
        Properties props;
        try {
            props = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return props.getProperty(key);
    }

    public static boolean getBooleanProperty(String key) {
        Resource resource = new ClassPathResource("test.properties");
        Properties props;
        try {
            props = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "true".equals(props.getProperty(key));
    }
}
