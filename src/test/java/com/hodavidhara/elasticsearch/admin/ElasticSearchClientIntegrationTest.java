package com.hodavidhara.elasticsearch.admin;

import com.hodavidhara.base.IntegrationTestBase;
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
