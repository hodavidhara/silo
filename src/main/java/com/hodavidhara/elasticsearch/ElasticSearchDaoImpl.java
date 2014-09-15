package com.hodavidhara.elasticsearch;

import com.google.common.collect.Maps;
import com.hodavidhara.elasticsearch.admin.ElasticSearchClient;
import com.hodavidhara.model.Indexable;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 *
 */
@Repository
public class ElasticSearchDaoImpl implements ElasticSearchDao {

    @Autowired
    private ElasticSearchClient elasticSearchClient;

    @Override
    public <T extends Indexable> T createDocument(T document) {

        Map<String, Object> source = Maps.newHashMap(document.getMetadata());

        IndexResponse response = elasticSearchClient.getClient().prepareIndex(ElasticSearchClient.MAIN_INDEX,
                "document").setSource(source).get();

        document.setId(response.getId());

        return document;
    }

    @Override
    public <T extends Indexable> T readDocument(String id, Class<T> clazz) {
        GetResponse response = elasticSearchClient.getClient().prepareGet(ElasticSearchClient.MAIN_INDEX, "document",
                id).get();

        return new IndexableBuilder<T>(response.getId(), response.getSource(), clazz).build();
    }

    @Override
    public void deleteDocument(String id) {
        elasticSearchClient.getClient().prepareDelete(ElasticSearchClient.MAIN_INDEX, "document", id);
    }
}
