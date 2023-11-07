package com.seya330.practiceElasticSearch.repository;

import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;

import java.util.Set;

public interface BaseEsRepository<T> {

    <S extends T> S save(S entity, IndexCoordinates indexName);

    <S extends T> Iterable<S> saveAll(Iterable<S> entities, IndexCoordinates indexName);

    boolean setAlias(IndexCoordinates indexNameWrapper, IndexCoordinates aliasNameWrapper);

    Set<String> findIndexNamesByAlias(IndexCoordinates aliasNameWrapper);

    boolean deleteIndex(IndexCoordinates indexNameWrapper);
}
