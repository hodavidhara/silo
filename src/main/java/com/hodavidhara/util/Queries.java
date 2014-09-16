package com.hodavidhara.util;

import com.hodavidhara.content.base.File;
import com.hodavidhara.elasticsearch.Query;
import com.hodavidhara.elasticsearch.Type;
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
