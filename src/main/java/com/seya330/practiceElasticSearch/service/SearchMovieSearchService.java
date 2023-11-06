package com.seya330.practiceElasticSearch.service;

import com.seya330.practiceElasticSearch.repository.MovieSearchRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchMovieSearchService {

  private final MovieSearchRepositoryImpl movieSearchRepositoryImpl;


}
