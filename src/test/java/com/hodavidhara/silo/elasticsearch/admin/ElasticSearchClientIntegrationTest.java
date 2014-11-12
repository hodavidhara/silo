package com.hodavidhara.silo.elasticsearch.admin;

import com.hodavidhara.silo.base.IntegrationTestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

/**
 *
 */
public class ElasticSearchClientIntegrationTest extends IntegrationTestBase {

    @Autowired
    private ElasticSearchClient elasticSearchClient;

    @Test
    public void testGetClient() {
        assertNotNull(elasticSearchClient.getClient());
    }
}
