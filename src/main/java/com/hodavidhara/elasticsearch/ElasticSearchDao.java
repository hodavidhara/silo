package com.hodavidhara.elasticsearch;

import com.hodavidhara.model.Indexable;

/**
 *
 */
public interface ElasticSearchDao {

    <T extends Indexable> T createDocument(T document);

    <T extends Indexable> T readDocument(String id, Class<T> clazz);

    void deleteDocument(String id);
}
