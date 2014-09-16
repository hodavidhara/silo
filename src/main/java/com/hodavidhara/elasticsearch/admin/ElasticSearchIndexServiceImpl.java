package com.hodavidhara.elasticsearch.admin;

import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public class ElasticSearchIndexServiceImpl implements ElasticSearchIndexService {

    @Autowired
    private ElasticSearchClient elasticSearchClient;

    @Override
    public boolean createIndex(String indexName) {
        elasticSearchClient.getClient().admin().indices().prepareCreate(indexName).get();

        // TODO: Make this good.
        String tempTest = "{\"directory\": {\"properties\": {\"path\": {\"type\": \"string\", \"index\":\"not_analyzed\"}}}}";
        elasticSearchClient.getClient().admin().indices().preparePutMapping(indexName).setType("directory").setSource(
                tempTest).get();
        return indexExists(indexName);
    }

    @Override
    public boolean deleteIndex(String indexName) {
        elasticSearchClient.getClient().admin().indices().prepareDelete(indexName).get();
        return !indexExists(indexName);
    }

    @Override
    public boolean indexExists(String indexName) {
        IndicesExistsResponse response = elasticSearchClient.getClient().admin().indices().prepareExists(indexName).get();
        return response.isExists();
    }
}
