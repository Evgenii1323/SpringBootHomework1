package com.example.springboothomework1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootHomework1ApplicationTests {
    @Autowired
    private TestRestTemplate restTemplate;
    private static final GenericContainer<?> firstContainer = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);
    private static final GenericContainer<?> secondContainer = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        firstContainer.start();
        secondContainer.start();
    }
    @Test
    void firstContextLoads() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + firstContainer.getMappedPort(8080), String.class);
        String expected = "Current profile is dev";
        Assertions.assertEquals(expected, forEntity.getBody());
    }

    @Test
    void secondContextLoads() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + secondContainer.getMappedPort(8081), String.class);
        String expected = "Current profile is production";
        Assertions.assertEquals(expected, forEntity.getBody());
    }
}