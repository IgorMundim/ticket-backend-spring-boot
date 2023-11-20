package com.mundim.ticketbackendspringboot.controller;

import com.mundim.ticketbackendspringboot.dto.request.AccountRequestDto;
import com.mundim.ticketbackendspringboot.dto.request.PasswordRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.AccountResponseDto;
import com.mundim.ticketbackendspringboot.dto.response.ErrorResponseDto;
import com.mundim.ticketbackendspringboot.mocks.MockAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/account/account-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/account/account-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class AccountControllerTest {
    MockAccount input;
    @Autowired
    WebTestClient testClient;

    @BeforeEach
    void setUpMocks() throws Exception {
        input = new MockAccount();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testWillCreateAccount(){
        AccountRequestDto entityDto = input.mockDto(1);
        AccountResponseDto result = testClient
                .post()
                .uri("/api/v1/account")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(entityDto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(AccountResponseDto.class)
                .returnResult().getResponseBody();
        assertEquals(entityDto.getUsername(), result.getUsername());
        assertEquals(entityDto.getEmail(), result.getEmail());
        assertEquals(entityDto.getProfileImage(), result.getProfileImage());

    }

    @Test
    public void testWillThrowBadRequestWhenTryCreateAccountWithAccountNameAlreadyRegistered(){
        AccountRequestDto entityDto = input.mockDto(1);
        entityDto.setUsername("user");
        ErrorResponseDto result = testClient
                .post()
                .uri("/api/v1/account")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(entityDto)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result).isNotNull();
        assertThat(result.getErrorMessage()).isEqualTo("Username user already registered");
    }

    @Test
    public void testWillCreateAccountAdmin(){
        AccountRequestDto entityDto = input.mockDto(1);
        AccountResponseDto result = testClient
                .post()
                .uri("/api/v1/account/admin")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(entityDto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(AccountResponseDto.class)
                .returnResult().getResponseBody();
        assertEquals(entityDto.getUsername(), result.getUsername());
        assertEquals(entityDto.getEmail(), result.getEmail());
        assertEquals(entityDto.getProfileImage(), result.getProfileImage());
    }

    @Test
    public void testWillThrowBadRequestWhenTryCreateAccountAdminWithAccountNameAlreadyRegistered(){
        AccountRequestDto entityDto = input.mockDto(1);
        entityDto.setUsername("user");
        ErrorResponseDto result = testClient
                .post()
                .uri("/api/v1/account/admin")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(entityDto)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result).isNotNull();
        assertThat(result.getErrorMessage()).isEqualTo("Username user already registered");
    }

    @Test
    public void testWillThrowUnauthorizedWhenTryCreateAccountAdminWithoutAuthorization(){
        AccountRequestDto entityDto = input.mockDto(1);
        entityDto.setUsername("user");
        ErrorResponseDto result = testClient
                .post()
                .uri("/api/v1/account/admin")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(entityDto)
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result).isNull();
    }

    @Test
    public void testWillThrowUnauthorizedWhenTryCreateAccountAdminWithoutAdminAuthorization(){
        AccountRequestDto entityDto = input.mockDto(1);
        entityDto.setUsername("user");
        ErrorResponseDto result = testClient
                .post()
                .uri("/api/v1/account/admin")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "user", "123456"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(entityDto)
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result).isNull();
    }

    @Test
    public void testWillUpdateAccount(){
        testClient
                .patch()
                .uri("/api/v1/account/101")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "user", "123456"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new PasswordRequestDto("@Abc1234", "123456"))
                .exchange()
                .expectStatus().isNoContent()
                .expectBody(AccountResponseDto.class)
                .returnResult().getResponseBody();

    }
    @Test
    public void testWillThrowBadRequestWhenTryUpdateAccountWithEqualPassword(){
        testClient
                .patch()
                .uri("/api/v1/account/101")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "user", "123456"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new PasswordRequestDto("123456", "123456"))
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody(AccountResponseDto.class)
                .returnResult().getResponseBody();
    }

    @Test
    public void testWillThrowForbiddenWhenTryUpdateAccountWithDistictAccount(){
        testClient
                .patch()
                .uri("/api/v1/account/101")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new PasswordRequestDto("@User123", "123456"))
                .exchange()
                .expectStatus().isForbidden()
                .expectBody(AccountResponseDto.class)
                .returnResult().getResponseBody();
    }

    @Test
    public void testWillGetAccount(){
        AccountResponseDto result = testClient
                .get()
                .uri("/api/v1/account/101")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "user", "123456"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(AccountResponseDto.class)
                .returnResult().getResponseBody();
        assertEquals("user", result.getUsername());
        assertEquals("customer@customer.com", result.getEmail());
        assertEquals("profileImage", result.getProfileImage());
    }
    @Test
    public void testWillThrowUnauthorizedWhenTryGetAccountWithoutAuthorization(){
        testClient
                .get()
                .uri("/api/v1/account/101")
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody(AccountResponseDto.class)
                .returnResult().getResponseBody();
    }
    @Test
    public void testWillThrowForbiddenWhenTryGetAccountWithDistictAccount(){
        testClient
                .get()
                .uri("/api/v1/account/101")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .exchange()
                .expectStatus().isForbidden()
                .expectBody(AccountResponseDto.class)
                .returnResult().getResponseBody();
    }
}