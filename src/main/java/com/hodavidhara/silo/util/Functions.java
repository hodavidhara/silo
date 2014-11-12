package com.hodavidhara.silo.util;

import com.google.common.base.Function;
import com.hodavidhara.silo.elasticsearch.Indexable;
import com.hodavidhara.silo.elasticsearch.IndexableBuilder;
import com.hodavidhara.silo.elasticsearch.Type;
import org.elasticsearch.search.SearchHit;

import javax.annotation.Nullable;

/**
 *
 */
public class Functions {

    public static Function<Type, String> typeToTypeName() {
        return new Function<Type, String>() {
            @Nullable
            @Override
            public String apply(@Nullable Type input) {
                return input == null ? null : input.getTypeName();
            }
        };
    }

    public static <T extends Indexable> Function<SearchHit, T> hitToIndexable() {
        return new Function<SearchHit, T>() {
            @Nullable
            @Override
            public T apply(@Nullable SearchHit hit) {
                return new IndexableBuilder<T>(hit).build();
            }
        };
    }
}
