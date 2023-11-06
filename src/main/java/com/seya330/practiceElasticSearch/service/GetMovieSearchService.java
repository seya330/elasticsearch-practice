package com.seya330.practiceElasticSearch.service;

import com.seya330.practiceElasticSearch.document.MovieSearch;
import com.seya330.practiceElasticSearch.repository.MovieSearchRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetMovieSearchService {

  private final MovieSearchRepositoryImpl movieSearchRepositoryImpl;

  public Page<MovieSearch> getAllMovieSearch() {
    return movieSearchRepositoryImpl.findAll();
  }
}