package com.hodavidhara.elasticsearch;

import com.hodavidhara.model.Indexable;

import java.util.Map;

/**
 *
 */
public class IndexableBuilder<T extends Indexable> {

    private Class<T> typeArgumentClass;
    private String id;
    private Map<String, Object> metadata;

    IndexableBuilder(String id, Map<String, Object> metadata, Class<T> clazz) {
        this.id = id;
        this.metadata = metadata;
        this.typeArgumentClass = clazz;
    }

    public T build() {
        T instance;
        try {
            instance = typeArgumentClass.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        instance.setId(id);
        instance.setMetadata(metadata);
        return instance;
    }
}
