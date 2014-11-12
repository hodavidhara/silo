package com.hodavidhara.silo.elasticsearch.admin;

/**
 *
 */
public interface ElasticSearchIndexService {

    boolean createIndex(String indexName);

    boolean putMappingForIndex(String indexName, Mapping map);

    boolean deleteIndex(String indexName);

    boolean indexExists(String indexName);
}
