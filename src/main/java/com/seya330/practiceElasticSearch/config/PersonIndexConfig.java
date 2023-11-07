package com.seya330.practiceElasticSearch.config;

import com.seya330.practiceElasticSearch.document.Person;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.index.*;

@Configuration
@RequiredArgsConstructor
public class PersonIndexConfig {

    private final ElasticsearchOperations operations;

    private static final String PERSON_INDEX_TEMPLATE = "person_template";

    @PostConstruct
    public void setup() {
        if (operations.indexOps(Person.class).existsIndexTemplate(PERSON_INDEX_TEMPLATE)) {
            return;
        }
        final AliasActions aliasActions = new AliasActions();
        aliasActions.add(new AliasAction.Add(AliasActionParameters.builderForTemplate()
                .withAliases(Person.NAME)
                .build()));

        final Settings settings = new Settings();
        settings.put("number_of_shards", 3);
        settings.put("number_of_replicas", 1);

        final PutIndexTemplateRequest indexTemplateRequest = PutIndexTemplateRequest.builder()
                .withName(PERSON_INDEX_TEMPLATE)
                .withIndexPatterns(Person.NAME + "-*")
                .withSettings(settings)
                .withAliasActions(aliasActions)
                .build();
        operations.indexOps(Person.class).putIndexTemplate(indexTemplateRequest);
    }
}
