package com.seya330.practiceElasticSearch.result;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BucketByRepGenreNm {

    String repGenreNm;

    long qty;
}
