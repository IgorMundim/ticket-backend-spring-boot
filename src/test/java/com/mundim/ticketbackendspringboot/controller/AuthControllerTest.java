package com.mundim.ticketbackendspringboot.controller;

import com.mundim.ticketbackendspringboot.dto.request.LoginRequestDto;
import com.mundim.ticketbackendspringboot.security.jwt.JwtToken;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/account/account-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/account/account-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class AuthControllerTest {
    @Autowired
    WebTestClient testClient;
    @Test
    public void testWillGetToken(){
        String token = testClient
                .post()
                .uri("/api/v1/auth")
                .bodyValue(new LoginRequestDto("user", "123456"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(JwtToken.class)
                .returnResult().getResponseBody().getToken();
        assertThat(token).isNotNull();
    }
    @Test
    public void testWillNotGetTokenWithInvalidCredential(){
        testClient
                .post()
                .uri("/api/v1/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new LoginRequestDto("user", "1234567"))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .returnResult().getResponseBody();
    }
}