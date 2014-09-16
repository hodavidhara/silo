package com.hodavidhara.bootstrap;

import com.hodavidhara.content.directory.Directory;
import com.hodavidhara.elasticsearch.Type;
import com.hodavidhara.elasticsearch.admin.ElasticSearchClient;
import com.hodavidhara.elasticsearch.admin.ElasticSearchIndexService;
import com.hodavidhara.elasticsearch.admin.Mapping;
import com.hodavidhara.elasticsearch.admin.MappingProperty;
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
            elasticSearchIndexService.putMappingForIndex(ElasticSearchClient.MAIN_INDEX,
                    generateDirectoryMapping());
        }
    }

    private Mapping generateDirectoryMapping() {
        return new Mapping(Type.DIRECTORY.getTypeName())
                .addProperty(new MappingProperty(Directory.PATH_PROPERTY)
                        .addRule("type", "string")
                        .addRule("index", "not_analyzed"));
    }
}
