package com.seya330.practiceElasticSearch.rest;

import com.seya330.practiceElasticSearch.service.PersonIndexService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonIndexService personIndexService;

    @PostMapping("/index")
    public ResponseEntity<Void> indexPerson() {
        personIndexService.executeIndexing();
        return ResponseEntity.noContent().build();
    }
}
