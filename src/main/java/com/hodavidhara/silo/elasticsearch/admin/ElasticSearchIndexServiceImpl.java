package com.hodavidhara.silo.elasticsearch.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

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

        return indexExists(indexName);
    }

    @Override
    public boolean putMappingForIndex(String indexName, Mapping mapping) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode mappingNode = mapper.createObjectNode();
        ObjectNode typeNode = mappingNode.putObject(mapping.getTypeName());
        ObjectNode propertiesNode = typeNode.putObject("properties");
        for (MappingProperty mappingProperty : mapping.getProperties()) {
            ObjectNode propertyNode = propertiesNode.putObject(mappingProperty.getPropertyName());
            for(Map.Entry<String, String> rule : mappingProperty.getMappingRules().entrySet()) {
                propertyNode.put(rule.getKey(), rule.getValue());
            }
        }

        String mappingJson;
        try {
            mappingJson = mapper.writeValueAsString(mappingNode);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        elasticSearchClient.getClient().admin().indices().preparePutMapping(indexName).setType(mapping.getTypeName())
                .setSource(mappingJson)
                .get();
        return false;
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
