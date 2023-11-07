package com.seya330.practiceElasticSearch.utils;

import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;

import java.time.Instant;

public class IndexUtil {

    public static IndexCoordinates createIndexNameWithPostFixWrapper(final String indexName) {
        return IndexCoordinates.of(indexName + "-" + Instant.now().toEpochMilli());
    }

    public static IndexCoordinates createIndexNameWrapper(final String indexName) {
        return IndexCoordinates.of(indexName);
    }
}
