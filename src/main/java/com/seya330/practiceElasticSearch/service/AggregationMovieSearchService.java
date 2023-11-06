package com.seya330.practiceElasticSearch.service;

import com.seya330.practiceElasticSearch.repository.MovieSearchRepository;
import com.seya330.practiceElasticSearch.result.BucketByRepGenreNm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AggregationMovieSearchService {

    final MovieSearchRepository movieSearchRepository;

    public List<BucketByRepGenreNm> getQtyByRepGenreNm() {
        return movieSearchRepository.aggsByRepGenreNmNative();
    }
}
