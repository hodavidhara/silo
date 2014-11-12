package com.hodavidhara.silo.content.base;

import com.hodavidhara.silo.elasticsearch.Indexable;

/**
 * Base class for any single item that may be persisted in Silo. It is the parent class for both Directories and
 * Documents.
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
