package com.hodavidhara.elasticsearch;

import com.hodavidhara.content.directory.Directory;
import com.hodavidhara.content.document.Document;
import org.elasticsearch.search.SearchHit;

import java.util.Map;

/**
 *
 */
public class IndexableBuilder<T extends Indexable> {

    private Class<T> typeArgumentClass;
    private String id;
    private Map<String, Object> metadata;

    public IndexableBuilder(String id, Map<String, Object> metadata, Type type) {
        this.id = id;
        this.metadata = metadata;
        this.typeArgumentClass = (Class<T>) getClassForHitType(type.getTypeName());
    }

    public IndexableBuilder(SearchHit searchHit) {
        this.id = searchHit.getId();
        this.metadata = searchHit.getSource();
        this.typeArgumentClass = (Class<T>) getClassForHitType(searchHit.getType());
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

    private Class<? extends Indexable> getClassForHitType(String type) {
        Type typeEnum = Type.getType(type);
        switch (typeEnum) {
            case DIRECTORY:
                return Directory.class;
            case DOCUMENT:
                return Document.class;
            case INDEXABLE:
                return Indexable.class;
            default:
                return Indexable.class;
        }
    }
}
