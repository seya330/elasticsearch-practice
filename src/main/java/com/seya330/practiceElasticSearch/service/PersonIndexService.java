package com.seya330.practiceElasticSearch.service;

import com.seya330.practiceElasticSearch.document.Car;
import com.seya330.practiceElasticSearch.document.Person;
import com.seya330.practiceElasticSearch.repository.PersonRepository;
import com.seya330.practiceElasticSearch.utils.IndexUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PersonIndexService {

    private final PersonRepository personRepository;

    public void executeIndexing() {
        final Set<String> previousIndices = personRepository.findIndexNamesByAlias(IndexUtil.createIndexNameWrapper(Person.NAME));
        final IndexCoordinates indexName = IndexUtil.createIndexNameWithPostFixWrapper(Person.NAME);
        final List<Person> list = Arrays.asList(Person.builder()
                        .name("홍길동")
                        .age(30)
                        .cars(Arrays.asList(
                                new Car("과학5호기", "SCIENCE5"), new Car("그랜저", "GRDR")
                        )).build(),
                Person.builder()
                        .name("김첨지")
                        .age(35).build(),
                Person.builder()
                        .name("가불기")
                        .age(18)
                        .cars(Arrays.asList(
                                new Car("BMW i4", "BMI5")
                        )).build());

        personRepository.saveAll(list, indexName);
        previousIndices.forEach(existIndexName -> personRepository.deleteIndex(IndexUtil.createIndexNameWrapper(existIndexName)));
    }
}
