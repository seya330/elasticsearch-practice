package com.seya330.practiceElasticSearch.repository;

import co.elastic.clients.elasticsearch._types.aggregations.*;
import com.seya330.practiceElasticSearch.document.MovieSearch;
import com.seya330.practiceElasticSearch.result.BucketByRepGenreNm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchAggregation;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchAggregations;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MovieSearchRepositoryImpl implements MovieSearchRepository {

    private final ElasticsearchOperations operations;

    private final MovieSearchJpaRepository movieSearchJpaRepository;

    public Page<MovieSearch> findAll() {
        return movieSearchJpaRepository.findAll(Pageable.ofSize(10));
    }

    public List<BucketByRepGenreNm> aggsByRepGenreNmNative() {
        final NativeQuery query = new NativeQueryBuilder()
                .withAggregation("bucketByRepGepGenreNm", Aggregation.of(aggs -> aggs.terms(termsAggs -> termsAggs.field("repGenreNm"))))
                .build();
        final SearchHits<MovieSearch> search = operations.search(query, MovieSearch.class);
        final ElasticsearchAggregations aggregations = (ElasticsearchAggregations) search.getAggregations();
        final ElasticsearchAggregation aggregation = aggregations.get("bucketByRepGepGenreNm");
        final Aggregate aggregate = aggregation.aggregation().getAggregate();
        final StringTermsAggregate sterms = aggregate.sterms();
        final Buckets<StringTermsBucket> buckets = sterms.buckets();
        return buckets.array().stream().map(stringTermsBucket ->
            BucketByRepGenreNm.builder()
                    .repGenreNm(stringTermsBucket.key().stringValue())
                    .qty(stringTermsBucket.docCount())
                    .build()
            ).toList();
    }

    public void searchMovieSearch() {
        final NativeQuery nativeQuery = new NativeQueryBuilder()
                .withFilter(e -> e.bool(
                        bool -> bool.mustNot(mustNot -> mustNot.term(term -> term.field("repNationNm").value("한국")))
                ))
                .withQuery(
                        query -> query.term(term -> term.field("movieNm").value("행복"))
                ).build();
        final SearchHits<MovieSearch> search = operations.search(nativeQuery, MovieSearch.class);
        System.out.println(1);
    }
}
