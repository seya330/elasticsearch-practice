package com.seya330.practiceElasticSearch.repository;

import com.seya330.practiceElasticSearch.document.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PersonRepository extends ElasticsearchRepository<Person, String>, BaseEsRepository<Person> {
}
