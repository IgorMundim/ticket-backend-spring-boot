package com.mundim.ticketbackendspringboot.swagger;

import com.mundim.ticketbackendspringboot.testcontainers.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.assertj.core.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/permission/permission-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/account/account-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/account/account-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(scripts = "/sql/permission/permission-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
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
