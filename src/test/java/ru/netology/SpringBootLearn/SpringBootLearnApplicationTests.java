package ru.netology.SpringBootLearn;


import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RequiredArgsConstructor
class SpringBootLearnApplicationTests {

	private  TestRestTemplate restTemplate;

	private static final GenericContainer<?> myAppFirst = new GenericContainer<>("devapp")
			.withExposedPorts(8080);

	private static final GenericContainer<?> myAppSecond = new GenericContainer<>("prodapp")
			.withExposedPorts(8081);

	@BeforeAll
    static void setUp() {
		myAppFirst.start();
		myAppSecond.start();
	}

	@Test
	void contextLoads() {

		ResponseEntity<String> entityFirst = restTemplate.getForEntity("http://localhost:" + myAppFirst.getMappedPort(8080), String.class);
		ResponseEntity<String> entitySecond = restTemplate.getForEntity("http://localhost:" + myAppSecond.getMappedPort(8081), String.class);
		System.out.println(entityFirst.getBody());
		System.out.println(entitySecond.getBody());
			}

}
