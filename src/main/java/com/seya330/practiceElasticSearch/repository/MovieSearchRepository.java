package com.seya330.practiceElasticSearch.repository;

import com.seya330.practiceElasticSearch.document.MovieSearch;
import com.seya330.practiceElasticSearch.result.BucketByRepGenreNm;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MovieSearchRepository {

  Page<MovieSearch> findAll();

  List<BucketByRepGenreNm> aggsByRepGenreNmNative();

  void searchMovieSearch();
}
