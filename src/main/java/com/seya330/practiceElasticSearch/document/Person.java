package com.seya330.practiceElasticSearch.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Data
@Document(indexName = Person.NAME, createIndex = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {

    @Id
    private String id;

    private String name;

    private int age;

    private String contents;

    private List<Car> cars;

    public static final String NAME = "person";
}
