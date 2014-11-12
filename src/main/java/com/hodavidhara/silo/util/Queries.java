package com.hodavidhara.silo.util;

import com.hodavidhara.silo.content.base.File;
import com.hodavidhara.silo.elasticsearch.Query;
import com.hodavidhara.silo.elasticsearch.Type;
import org.elasticsearch.index.query.QueryBuilders;

/**
 *
 */
public class Queries {

    public static Query queryByPath(String path, Type... types) {
        Query query = new Query(types);
        query.query(QueryBuilders.boolQuery().must(QueryBuilders.termQuery(File.PATH_PROPERTY, path)));
        return query;
    }
}
