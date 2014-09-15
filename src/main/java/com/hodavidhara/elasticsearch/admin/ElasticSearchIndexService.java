package com.hodavidhara.elasticsearch.admin;

/**
 *
 */
public interface ElasticSearchIndexService {

    boolean createIndex(String indexName);

    boolean deleteIndex(String indexName);

    boolean indexExists(String indexName);
}
