package com.seya330.practiceElasticSearch.repository;

import com.seya330.practiceElasticSearch.document.MovieSearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface MovieSearchJpaRepository extends ElasticsearchRepository<MovieSearch, String> {
}
