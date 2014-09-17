package com.hodavidhara.elasticsearch.admin;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 *
 */
public class MappingProperty {

    private String propertyName;
    private Map<String, String> mappingRules = Maps.newHashMap();

    public MappingProperty(String propertyName) {
        this.propertyName = propertyName;
    }

    public MappingProperty addRule(String ruleName, String value) {
        this.mappingRules.put(ruleName, value);
        return this;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public Map<String, String> getMappingRules() {
        return mappingRules;
    }
}
