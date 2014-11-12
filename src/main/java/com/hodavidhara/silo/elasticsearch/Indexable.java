package com.hodavidhara.silo.elasticsearch;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Base class for anything that may be indexed.
 */
public class Indexable {

    private String id;
    private Map<String, Object> metadata = Maps.newHashMap();

    private static final String NAME_PROPERTY = "name";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public <T> T getProperty(String propertyName) {
        return (T) this.metadata.get(propertyName);
    }

    public void setProperty(String propertyName, Object propertyValue) {
        this.metadata.put(propertyName, propertyValue);
    }

    public String getName() {
        return (String) metadata.get(NAME_PROPERTY);
    }

    public void setName(String name) {
        metadata.put(NAME_PROPERTY, name);
    }
}
