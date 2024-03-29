package com.seya330.practiceElasticSearch.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.index.AliasAction;
import org.springframework.data.elasticsearch.core.index.AliasActionParameters;
import org.springframework.data.elasticsearch.core.index.AliasActions;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class BaseEsRepositoryImpl<T> implements BaseEsRepository<T> {

    private final ElasticsearchOperations operations;

    @Override
    public <S extends T> S save(S entity, IndexCoordinates indexName) {
        return operations.save(entity, indexName);
    }

    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> entities, IndexCoordinates indexName) {
        return operations.save(entities, indexName);
    }

    @Override
    public boolean setAlias(IndexCoordinates indexNameWrapper, IndexCoordinates aliasNameWrapper) {
        final IndexOperations indexOperations = operations.indexOps(indexNameWrapper);
        final AliasActions aliasActions = new AliasActions();
        aliasActions.add(new AliasAction.Add(AliasActionParameters.builder()
                .withIndices(indexNameWrapper.getIndexNames())
                .withAliases(aliasNameWrapper.getIndexName())
                .build()));
        return indexOperations.alias(aliasActions);
    }

    @Override
    public Set<String> findIndexNamesByAlias(IndexCoordinates aliasNameWrapper) {
        //alias를 indexName을 통해 가지고 온다.
        final IndexOperations indexOperations = operations.indexOps(aliasNameWrapper);
        if (operations.indexOps(aliasNameWrapper).exists()) {
            return indexOperations.getAliasesForIndex(aliasNameWrapper.getIndexName()).keySet();
        } else {
            return Collections.emptySet();
        }
    }

    @Override
    public boolean deleteIndex(IndexCoordinates indexNameWrapper) {
        final IndexOperations indexOperations = operations.indexOps(indexNameWrapper);
        return indexOperations.delete();
    }
}
