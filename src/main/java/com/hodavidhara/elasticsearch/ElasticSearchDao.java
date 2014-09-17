package com.hodavidhara.elasticsearch;

import java.util.List;

/**
 *
 */
public interface ElasticSearchDao {

    <T extends Indexable> T create(T indexable, Type type);

    <T extends Indexable> T read(String id, Type type);

    void delete(String id, Type type);

    public <T extends Indexable> List<T> search(Query query);

    public <T extends Indexable> T searchSingle(Query query);
}
