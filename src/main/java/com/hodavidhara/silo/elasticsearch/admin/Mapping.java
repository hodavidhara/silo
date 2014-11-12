package com.hodavidhara.silo.elasticsearch.admin;

import com.google.common.collect.Lists;

import java.util.List;

/**
 *
 */
public class Mapping {

    private String typeName;
    private List<MappingProperty> properties = Lists.newArrayList();

    public Mapping(String typeName) {
        this.typeName = typeName;
    }

    public Mapping addProperty(MappingProperty mappingProperty) {
        this.properties.add(mappingProperty);
        return this;
    }

    public List<MappingProperty> getProperties() {
        return properties;
    }

    public String getTypeName() {
        return typeName;
    }
}
