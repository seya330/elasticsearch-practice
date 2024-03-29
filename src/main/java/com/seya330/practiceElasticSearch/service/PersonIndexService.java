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
                        .contents("학교 핵교 하악교오오 학교가싫다.")
                        .cars(Arrays.asList(
                                new Car("과학5호기", "SCIENCE5"), new Car("그랜저", "GRDR")
                        )).build(),
                Person.builder()
                        .name("김첨지")
                        .contents("김첨지와 박순이는 한마을에 살았뜨래요~~ 우악")
                        .age(35).build(),
                Person.builder()
                        .name("가불기")
                        .age(18)
                        .contents("안녕하세요 제 이름은 조세진 입니다. 관저고등학교를 다녀요")
                        .cars(Arrays.asList(
                                new Car("BMW i4", "BMI5")
                        )).build(),
                Person.builder()
                        .name("이지희")
                        .age(18)
                        .contents("우리학교 중경고등학교의 선생님은 예뻐")
                        .cars(Arrays.asList(
                                new Car("BMW i4", "BMI5")
                        )).build(),
                Person.builder()
                        .name("나오지마")
                        .age(18)
                        .contents("우리학교는 이걸로 검색은 안되야 해. 학교 시러시러")
                        .cars(Arrays.asList(
                                new Car("BMW i4", "BMI5")
                        )).build(),
                Person.builder()
                        .name("나오지마2")
                        .age(18)
                        .contents("학교 급식 너무 맛있엉")
                        .cars(Arrays.asList(
                                new Car("BMW i4", "BMI5")
                        )).build()
                );
        personRepository.saveAll(list, indexName);
        previousIndices.forEach(existIndexName -> personRepository.deleteIndex(IndexUtil.createIndexNameWrapper(existIndexName)));
    }
}
