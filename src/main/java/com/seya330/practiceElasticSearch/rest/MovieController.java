package com.seya330.practiceElasticSearch.rest;

import com.seya330.practiceElasticSearch.document.MovieSearch;
import com.seya330.practiceElasticSearch.result.BucketByRepGenreNm;
import com.seya330.practiceElasticSearch.service.AggregationMovieSearchService;
import com.seya330.practiceElasticSearch.service.GetMovieSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

  private final GetMovieSearchService getMovieSearchService;

  private final AggregationMovieSearchService aggregationMovieSearchService;

  @GetMapping
  public ResponseEntity<Page<MovieSearch>> getAllMovieSearch() {
    return ResponseEntity.ok(getMovieSearchService.getAllMovieSearch());
  }

  @GetMapping("/aggregations/native")
  public ResponseEntity<List<BucketByRepGenreNm>> getAggregationByNative() {
    return ResponseEntity.ok(aggregationMovieSearchService.getQtyByRepGenreNm());
  }
}
