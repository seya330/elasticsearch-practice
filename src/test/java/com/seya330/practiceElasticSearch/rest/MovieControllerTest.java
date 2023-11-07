package com.seya330.practiceElasticSearch.rest;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MovieControllerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    void getAllMovieSearch() {
        given()
                .expect()
                .when()
                .get("/movies")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("numberOfElements", is(10))
                .log().all();
    }

    @Test
    void getAggregationByNative() {
        when()
                .get("/movies/aggregations/native")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("repGenreNm", hasItems("드라마", "애니메이션", "액션", "멜로/로맨스"))
                .body("qty[0]", equalTo(14930))
                .log().all();
    }

    @Test
    void searchMovieSearch() {
        when()
                .get("/movies/search")
                .then()
                .statusCode(HttpStatus.OK.value())
                .log().all();
    }

}