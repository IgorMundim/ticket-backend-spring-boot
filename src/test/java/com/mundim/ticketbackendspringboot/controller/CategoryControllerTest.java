package com.mundim.ticketbackendspringboot.controller;

import com.mundim.ticketbackendspringboot.dto.request.CategoryRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.CategoryResponseDto;
import com.mundim.ticketbackendspringboot.dto.response.ErrorResponseDto;
import com.mundim.ticketbackendspringboot.dto.response.ResponseDto;
import com.mundim.ticketbackendspringboot.mocks.MockCategory;
import com.mundim.ticketbackendspringboot.testcontainers.AbstractIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/permission/permission-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/account/account-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/category/category-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/account/account-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(scripts = "/sql/permission/permission-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(scripts = "/sql/category/category-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

class CategoryControllerTest extends AbstractIntegrationTest {
    MockCategory input;
    @Autowired
    WebTestClient testClient;
    @BeforeEach
    void setUpMocks() throws Exception {
        input = new MockCategory();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testWillCreateCategory(){
        CategoryRequestDto entityDto = input.mockDto(1);
        CategoryResponseDto result = testClient
                .post()
                .uri("/api/v1/category")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .bodyValue(entityDto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(CategoryResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result.getIsActive()).isEqualTo(true);
        assertThat(result.getAltText()).isEqualTo("AltTextTest1");
        assertThat(result.getUrl()).isEqualTo("UrlTest1");
        assertThat(result.getName()).isEqualTo("NameTest1");
    }

    @Test
    public void testWillThrowBadRequestWhenTryCreateCategoryWithPermissionNameRegistered(){
        CategoryRequestDto entityDto = input.mockDto(1);
        entityDto.setName("CategoryName");

        ErrorResponseDto resultError = testClient
                .post()
                .uri("/api/v1/category")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .bodyValue(entityDto)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(resultError.getErrorMessage()).isEqualTo("Category name CategoryName already registered");
    }

    @Test
    public void testWillThrowUnauthorizedWhenTryCreateCategoryWithoutAuthorization(){
        CategoryRequestDto categoryDto = input.mockDto(1);
        ErrorResponseDto result = testClient
                .post()
                .uri("/api/v1/category")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(categoryDto)
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result).isNull();
    }

    @Test
    public void testWillThrowUnauthorizedWhenTryCreateCategoryWithCustomerPermission(){
        CategoryRequestDto categoryDto = input.mockDto(1);
        ErrorResponseDto result = testClient
                .post()
                .uri("/api/v1/category")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(categoryDto)
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "user", "123456"))
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result).isNull();
    }

    @Test
    public void testGetCategory(){
        CategoryResponseDto result = testClient
                .get()
                .uri("/api/v1/category/100")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(CategoryResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result.getIsActive()).isEqualTo(true);
        assertThat(result.getAltText()).isEqualTo("altText");
        assertThat(result.getUrl()).isEqualTo("url");
        assertThat(result.getName()).isEqualTo("categoryName");
    }

    @Test
    public void testWillThrowNotFoundWhenTryGetCategoryId(){
        ErrorResponseDto result = testClient
                .get()
                .uri("/api/v1/category/11")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result.getErrorMessage()).isEqualTo("Category not found with the given input data id : '11'");
    }

    @Test
    public void testGetAllCategory(){
        List<CategoryResponseDto> result = testClient
                .get()
                .uri("/api/v1/category/")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(CategoryResponseDto.class)
                .returnResult().getResponseBody();

        assertThat(result.get(0).getIsActive()).isEqualTo(true);
        assertThat(result.get(0).getAltText()).isEqualTo("altText");
        assertThat(result.get(0).getUrl()).isEqualTo("url");
        assertThat(result.get(0).getName()).isEqualTo("categoryName");
    }

    @Test
    public void testWillUpdateCategoryById(){
        CategoryRequestDto entityDto = input.mockDto(100);
        CategoryResponseDto result = testClient
                .patch()
                .uri("/api/v1/category/100")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .bodyValue(entityDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(CategoryResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result.getIsActive()).isEqualTo(true);
        assertThat(result.getAltText()).isEqualTo("AltTextTest100");
        assertThat(result.getUrl()).isEqualTo("UrlTest100");
        assertThat(result.getName()).isEqualTo("NameTest100");
    }

    @Test
    public void testWillThrowBadRequestWhenTryUpdateCategoryWithPermissionNameRegistered(){
        CategoryRequestDto entityDto = input.mockDto(1);

         CategoryResponseDto entity = testClient
                .post()
                .uri("/api/v1/category")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .bodyValue(entityDto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(CategoryResponseDto.class)
                .returnResult().getResponseBody();
        entityDto.setName("categoryName");
        ErrorResponseDto result = testClient
                .patch()
                .uri("/api/v1/category/"+entity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .bodyValue(entityDto)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result.getErrorMessage()).isEqualTo("Category name categoryName already registered");
    }
    @Test
    public void testWillThrowUnauthorizedWhenTryUpdateCategoryWithCustomerPermission(){
        CategoryRequestDto entityDto = input.mockDto(1);
        ErrorResponseDto result = testClient
                .patch()
                .uri("/api/v1/category/100")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "user", "123456"))
                .bodyValue(entityDto)
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result).isNull();
    }

    @Test
    public void testWillThrowUnauthorizedWhenTryUpdateCategoryWithWithoutAuthorization(){
        CategoryRequestDto entityDto = input.mockDto(1);
        ErrorResponseDto result = testClient
                .patch()
                .uri("/api/v1/category/100")
                .bodyValue(entityDto)
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result).isNull();
    }

    @Test
    public void testDeleteCategory(){
        ResponseDto result = testClient
                .delete()
                .uri("/api/v1/category/100")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(ResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result.getMessage()).isEqualTo("Request processed successfully");
    }

    @Test
    public void testWillThrowUnauthorizedWhenTryDeleteCategoryWithCustomerPermission(){
        CategoryRequestDto entityDto = input.mockDto(1);
        ErrorResponseDto result = testClient
                .delete()
                .uri("/api/v1/category/100")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "user", "123456"))
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result).isNull();
    }

    @Test
    public void testWillThrowUnauthorizedWhenTryDeleteAllCategoryWithoutAuthorization(){
        CategoryRequestDto entityDto = input.mockDto(1);
        ErrorResponseDto result = testClient
                .delete()
                .uri("/api/v1/category/100")
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result).isNull();
    }

}