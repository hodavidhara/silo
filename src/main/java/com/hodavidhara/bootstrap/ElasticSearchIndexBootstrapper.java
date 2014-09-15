package com.hodavidhara.bootstrap;

import com.hodavidhara.elasticsearch.admin.ElasticSearchClient;
import com.hodavidhara.elasticsearch.admin.ElasticSearchIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class ElasticSearchIndexBootstrapper extends Bootstrapper {

    @Autowired
    private ElasticSearchIndexService elasticSearchIndexService;

    @Override
    protected void bootstrap() {
        boolean mainIndexExists = elasticSearchIndexService.indexExists(ElasticSearchClient.MAIN_INDEX);
        if (!mainIndexExists) {
            elasticSearchIndexService.createIndex(ElasticSearchClient.MAIN_INDEX);
        }
    }
}
