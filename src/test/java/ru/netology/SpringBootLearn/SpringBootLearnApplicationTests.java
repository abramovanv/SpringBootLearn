package ru.netology.SpringBootLearn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootLearnApplicationTests {

    private final RestTemplate restTemplate = new RestTemplate();

    @Container
    private static final GenericContainer<?> myAppDev = new GenericContainer<>("devapp")
            .withExposedPorts(8080);
    @Container
    private static final GenericContainer<?> myAppProm = new GenericContainer<>("prodapp")
            .withExposedPorts(8081);

    @Test
    @DisplayName("Проверка стенда Dev")
    void contextLoadsDev() {
        ResponseEntity<String> entityFirst = restTemplate.getForEntity("http://localhost:" + myAppDev.getMappedPort(8080) + "/profile", String.class);
        Assertions.assertEquals("Current profile is dev", entityFirst.getBody().toString());
    }

    @Test
    @DisplayName("Проверка стенда Prom")
    void contextLoadsProm() {
        ResponseEntity<String> entitySecond = restTemplate.getForEntity("http://localhost:" + myAppProm.getMappedPort(8081) + "/profile", String.class);
        Assertions.assertEquals("Current profile is production", entitySecond.getBody().toString());
    }
}
