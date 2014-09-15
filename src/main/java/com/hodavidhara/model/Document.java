package com.hodavidhara.model;

/**
 *
 */
public class Document extends Indexable {

    private static final String PATH_PROPERTY = "path";

    public String getPath() {
        return getProperty(PATH_PROPERTY);
    }

    public void setPath(String path) {
        setProperty(PATH_PROPERTY, path);
    }
}
