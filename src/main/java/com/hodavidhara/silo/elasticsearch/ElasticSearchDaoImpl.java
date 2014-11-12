package com.hodavidhara.silo.elasticsearch;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hodavidhara.silo.elasticsearch.admin.ElasticSearchClient;
import com.hodavidhara.silo.util.Functions;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 */
@Repository
public class ElasticSearchDaoImpl implements ElasticSearchDao {

    @Autowired
    private ElasticSearchClient elasticSearchClient;

    @Override
    public <T extends Indexable> T create(T indexable, Type type) {

        Map<String, Object> source = Maps.newHashMap(indexable.getMetadata());

        IndexResponse response = elasticSearchClient.getClient().prepareIndex(ElasticSearchClient.MAIN_INDEX,
                type.getTypeName()).setSource(source).setRefresh(true).get();

        indexable.setId(response.getId());

        return indexable;
    }

    @Override
    public <T extends Indexable> T read(String id, Type type) {
        GetResponse response = elasticSearchClient.getClient().prepareGet(ElasticSearchClient.MAIN_INDEX,
                type.getTypeName(), id).get();

        return new IndexableBuilder<T>(response.getId(), response.getSource(), type).build();
    }

    @Override
    public void delete(String id, Type type) {
        elasticSearchClient.getClient().prepareDelete(ElasticSearchClient.MAIN_INDEX, type.getTypeName(),
                id).setRefresh(true).get();
    }

    @Override
    public <T extends Indexable> List<T> search(Query query) {
        SearchResponse response = doSearch(query);

        return Lists.transform(Lists.newArrayList(response.getHits().getHits()), Functions.<T>hitToIndexable());
    }

    @Override
    public <T extends Indexable> T searchSingle(Query query) {
        SearchResponse response = doSearch(query);

        long totalHits = response.getHits().getTotalHits();
        if (totalHits > 1) {
            throw new RuntimeException("Expected at most one result but found:" + totalHits);
        } else if (totalHits == 0) {
            return null;
        } else {
            return Lists.transform(Lists.newArrayList(response.getHits().getHits()), Functions.<T>hitToIndexable())
                    .get(0);
        }
    }

    private SearchResponse doSearch(Query query) {
        SearchRequestBuilder searchRequest = elasticSearchClient.getClient().prepareSearch(
                ElasticSearchClient.MAIN_INDEX);

        if (query.getTypes() != null || !query.getTypes().isEmpty()) {
            searchRequest.setTypes(query.getTypes().toArray(new String[query.getTypes().size()]));
        }

        if (query.getQuery() != null) {
            searchRequest.setQuery(query.getQuery());
        }

        if (query.getPostFilter() != null) {
            searchRequest.setPostFilter(query.getPostFilter());
        }

        if (query.getSort() != null) {
            searchRequest.addSort(query.getSort());
        }
        return searchRequest.setFrom(query.getFrom())
                .setSize(query.getSize())
                .get();
    }
}
