package com.hodavidhara.elasticsearch;

import com.google.common.collect.Lists;
import com.hodavidhara.util.Functions;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.sort.SortBuilder;

import java.util.List;

/**
 *
 */
public class Query {

    private List<String> types;
    private QueryBuilder query;
    private FilterBuilder postFilter;
    private SortBuilder sort;
    private int from = 0;
    private int size = 20;

    public Query() {}

    public Query(Type... type) {
        this.types = Lists.transform(Lists.newArrayList(type), Functions.typeToTypeName());
    }

    public Query(List<Type> types) {
        this.types = Lists.transform(types, Functions.typeToTypeName());
    }

    public Query query(QueryBuilder queryBuilder) {
        this.query = queryBuilder;
        return this;
    }

    public Query postFilter(FilterBuilder filterBuilder) {
        this.postFilter = filterBuilder;
        return this;
    }

    public Query sort(SortBuilder sortBuilder) {
        this.sort = sortBuilder;
        return this;
    }

    public Query from(int from) {
        this.from = from;
        return this;
    }

    public Query size(int size) {
        this.size = size;
        return this;
    }

    public List<String> getTypes() {
        return types;
    }

    public QueryBuilder getQuery() {
        return query;
    }

    public FilterBuilder getPostFilter() {
        return postFilter;
    }

    public SortBuilder getSort() {
        return sort;
    }

    public int getFrom() {
        return from;
    }

    public int getSize() {
        return size;
    }
}
