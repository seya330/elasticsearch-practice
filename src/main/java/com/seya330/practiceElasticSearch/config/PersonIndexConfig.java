package com.seya330.practiceElasticSearch.config;

import com.seya330.practiceElasticSearch.document.Person;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.index.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Configuration
@RequiredArgsConstructor
public class PersonIndexConfig {

    private final ElasticsearchOperations operations;

    private static final String PERSON_INDEX_TEMPLATE = "person_template";

    @PostConstruct
    public void setup() throws IOException {
        if (operations.indexOps(Person.class).existsIndexTemplate(PERSON_INDEX_TEMPLATE)) {
            operations.indexOps(Person.class).deleteIndexTemplate(PERSON_INDEX_TEMPLATE);
        }
        final AliasActions aliasActions = new AliasActions();
        aliasActions.add(new AliasAction.Add(AliasActionParameters.builderForTemplate()
                .withAliases(Person.NAME)
                .build()));

        ClassPathResource classPathResource = new ClassPathResource("person-settings.json");
        final Settings settings = Settings.parse(classPathResource.getContentAsString(StandardCharsets.UTF_8));

        final PutIndexTemplateRequest indexTemplateRequest = PutIndexTemplateRequest.builder()
                .withName(PERSON_INDEX_TEMPLATE)
                .withIndexPatterns(Person.NAME + "-*")
                .withSettings(settings)
                .withMapping(Document.parse(new ClassPathResource("person-mappings.json").getContentAsString(StandardCharsets.UTF_8)))
                .withAliasActions(aliasActions)
                .build();

        operations.indexOps(Person.class).putIndexTemplate(indexTemplateRequest);
    }
}
