package com.hodavidhara.silo.base;

import com.hodavidhara.silo.elasticsearch.admin.ElasticSearchClient;
import com.hodavidhara.silo.elasticsearch.admin.ElasticSearchIndexService;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;

/**
 *
 */
@Ignore
public class ElasticSearchIntegrationUtilities extends IntegrationTestBase{

    @Autowired
    private ElasticSearchIndexService elasticSearchIndexService;

    @Test
    public void recreateIndex() {
        assertTrue(elasticSearchIndexService.deleteIndex(ElasticSearchClient.MAIN_INDEX));
        assertTrue(elasticSearchIndexService.createIndex(ElasticSearchClient.MAIN_INDEX));
    }
}
