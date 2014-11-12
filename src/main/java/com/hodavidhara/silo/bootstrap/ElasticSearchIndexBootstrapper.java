package com.hodavidhara.silo.bootstrap;

import com.hodavidhara.silo.content.directory.Directory;
import com.hodavidhara.silo.elasticsearch.Type;
import com.hodavidhara.silo.elasticsearch.admin.ElasticSearchClient;
import com.hodavidhara.silo.elasticsearch.admin.ElasticSearchIndexService;
import com.hodavidhara.silo.elasticsearch.admin.Mapping;
import com.hodavidhara.silo.elasticsearch.admin.MappingProperty;
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
