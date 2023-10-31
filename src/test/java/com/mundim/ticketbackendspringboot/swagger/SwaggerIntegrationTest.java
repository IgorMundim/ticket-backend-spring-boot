package com.mundim.ticketbackendspringboot.swagger;

import com.mundim.ticketbackendspringboot.testcontainers.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.assertj.core.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SwaggerIntegrationTest extends AbstractIntegrationTest {
	@Autowired
	WebTestClient testClient;
	@Test
	void shouldDisplaySwaggerUiPage() {
		var responseBody =  testClient
				.get()
				.uri("/swagger-ui/index.html#/")
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.returnResult().getResponseBody();

		assertThat(responseBody).isNotNull();
		assertThat(responseBody).asString().contains("swagger-ui");

	}

}
