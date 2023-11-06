package com.seya330.practiceElasticSearch.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Document(indexName = "movie_search")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
public class MovieSearch {

  @Id
  private String id;

  private String movieCd;

  private String movieNm;

  private String movieNmEn;

  private String prdtYear;

  private String prdtStatNm;

  private String nationAlt;

  private String repGenreNm;

  private List<String> genreAlt;

  private List<Director> directors;

  private List<Company> companys;
}