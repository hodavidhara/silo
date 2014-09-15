package com.hodavidhara.elasticsearch.admin;

import com.hodavidhara.base.IntegrationTestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;

/**
 *
 */
public class ElasticSearchIndexServiceIntegrationTest extends IntegrationTestBase {

    @Autowired
    private ElasticSearchIndexService elasticSearchIndexService;

    @Test
    public void testCreateAndDeleteIndex() {
        assertTrue(elasticSearchIndexService.createIndex("test"));
        assertTrue(elasticSearchIndexService.deleteIndex("test"));
    }
}
