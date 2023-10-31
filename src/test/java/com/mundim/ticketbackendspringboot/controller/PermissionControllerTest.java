package com.mundim.ticketbackendspringboot.controller;

import com.mundim.ticketbackendspringboot.dto.request.PermissionRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.ErrorResponseDto;
import com.mundim.ticketbackendspringboot.dto.response.PermissionResponseDto;
import com.mundim.ticketbackendspringboot.mocks.MockPermission;
import com.mundim.ticketbackendspringboot.testcontainers.AbstractIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/permission/permission-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/account/account-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/account/account-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(scripts = "/sql/permission/permission-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class PermissionControllerTest extends AbstractIntegrationTest {
    MockPermission input;
    @Autowired
    WebTestClient testClient;

    @BeforeEach
    void setUpMocks() throws Exception {
        input = new MockPermission();
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testWillCreatePermission(){
        PermissionRequestDto permissionDto = input.mockDto(1);
        PermissionResponseDto result = testClient
                .post()
                .uri("/api/v1/permission/")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .bodyValue(permissionDto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(PermissionResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result.getRoleName()).isEqualTo("ROLE_1");
    }
    @Test
    public void testWillThrowBadRequestWhenTryCreatePermissionWithPermissionRoleRegistered(){
        PermissionRequestDto permissionDto = input.mockDto(1);
        permissionDto.setRoleName("ROLE_CUSTOMER");
        ErrorResponseDto result = testClient
                .post()
                .uri("/api/v1/permission/")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .bodyValue(permissionDto)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result).isNotNull();
        assertThat(result.getErrorMessage()).isEqualTo("Permission name ROLE_CUSTOMER already registered");
    }
    @Test
    public void testWillThrowUnauthorizedWhenTryCreatePermissionWithoutAuthorization(){
        PermissionRequestDto permissionDto = input.mockDto(1);
        ErrorResponseDto result = testClient
                .post()
                .uri("/api/v1/permission/")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(permissionDto)
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result).isNull();
    }
    @Test
    public void testWillThrowUnauthorizedWhenTryCreatePermissionWithCustomerPermission(){
        PermissionRequestDto permissionDto = input.mockDto(1);
        ErrorResponseDto result = testClient
                .post()
                .uri("/api/v1/permission/")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "user", "123456"))
                .bodyValue(permissionDto)
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result).isNull();
    }


    @Test
    public void testWillGetPermissionById(){
        PermissionResponseDto result = testClient
                .get()
                .uri("/api/v1/permission/1")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(PermissionResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result.getRoleName()).isEqualTo("ROLE_CUSTOMER");
    }
    @Test
    public void testWillThrowNotFoundWhenTryGetPermissionId(){
        ErrorResponseDto result = testClient
                .get()
                .uri("/api/v1/permission/11")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result.getErrorMessage()).isEqualTo("Permission not found with the given input data id : '11'");
    }
    @Test
    public void testWillThrowUnauthorizedWhenTryGetPermissionWithoutAuthorization(){
        ErrorResponseDto result = testClient
                .get()
                .uri("/api/v1/permission/1")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "user", "123456"))
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result).isNull();
    }
    @Test
    public void testWillThrowUnauthorizedWhenTryGetPermissionWithCustomerPermission(){
        ErrorResponseDto result = testClient
                .get()
                .uri("/api/v1/permission/1")
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result).isNull();
    }
    @Test
    public void testWillUpdatePermissionById(){
        PermissionResponseDto result = testClient
                .patch()
                .uri("/api/v1/permission/1")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .bodyValue(new PermissionRequestDto("ROLE_1"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(PermissionResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result.getRoleName()).isEqualTo("ROLE_1");
    }
    @Test
    public void testWillThrowBadRequestWhenTryUpdatePermissionWithPermissionRoleRegistered(){
        PermissionRequestDto permissionDto = input.mockDto(1);
        permissionDto.setRoleName("ROLE_ADMIN");
        ErrorResponseDto result = testClient
                .patch()
                .uri("/api/v1/permission/1")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .bodyValue(permissionDto)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result).isNotNull();
        assertThat(result.getErrorMessage()).isEqualTo("Permission name ROLE_ADMIN already registered");
    }
    @Test
    public void testWillThrowUnauthorizedWhenTryUpdatePermissionWithoutAuthorization(){
        PermissionRequestDto permissionDto = input.mockDto(1);
        ErrorResponseDto result = testClient
                .patch()
                .uri("/api/v1/permission/1")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "user", "123456"))
                .bodyValue(permissionDto)
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result).isNull();
    }
    @Test
    public void testWillThrowUnauthorizedWhenTryUpdatePermissionWithCustomerPermission(){
        PermissionRequestDto permissionDto = input.mockDto(1);
        ErrorResponseDto result = testClient
                .patch()
                .uri("/api/v1/permission/1")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(permissionDto)
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result).isNull();
    }
    @Test
    public void testWillGetAllPermissionById_200(){
        List<PermissionResponseDto> results = testClient
                .get()
                .uri("/api/v1/permission/")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(PermissionResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(results.get(0).getRoleName()).isEqualTo("ROLE_CUSTOMER");
    }

    @Test
    public void testWillThrowUnauthorizedWhenTryGetAllPermissionWithoutAuthorization_401(){
        ErrorResponseDto result = testClient
                .get()
                .uri("/api/v1/permission/1")
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result).isNull();
    }
    @Test
    public void testWillThrowUnauthorizedWhenTryGetAllPermissionWithCustomerPermission_401(){
        ErrorResponseDto result = testClient
                .get()
                .uri("/api/v1/permission/")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "user", "123456"))
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result).isNull();
    }

}