package com.hodavidhara.content.base;

import com.hodavidhara.elasticsearch.Indexable;

/**
 *
 */
public class File extends Indexable {

    public static final String PATH_PROPERTY = "path";

    public String getPath() {
        return getProperty(PATH_PROPERTY);
    }

    public void setPath(String path) {
        setProperty(PATH_PROPERTY, path);
    }
}
