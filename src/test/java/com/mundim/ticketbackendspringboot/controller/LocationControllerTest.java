package com.mundim.ticketbackendspringboot.controller;

import com.mundim.ticketbackendspringboot.dto.request.LocationRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.ErrorResponseDto;
import com.mundim.ticketbackendspringboot.dto.response.LocationResponseDto;
import com.mundim.ticketbackendspringboot.mocks.MockLocation;
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
@Sql(scripts = "/sql/event-location/location-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/event-location/location-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class LocationControllerTest {
    MockLocation input;
    @Autowired
    WebTestClient testClient;
    @BeforeEach
    void setUpMocks() throws Exception {
        input = new MockLocation();
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testWillCreateLocation(){
        LocationRequestDto entityDto = input.mockDto(101);
        LocationResponseDto result = testClient
                .post()
                .uri("/api/v1/event/101/location")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .bodyValue(entityDto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(LocationResponseDto.class)
                .returnResult().getResponseBody();
        assertEquals(entityDto.getDescription(), result.getDescription());
        assertEquals(entityDto.getName(),result.getName());
        assertEquals(entityDto.getIsActive(),result.getIsActive());
        assertEquals(entityDto.getSalePrice(),result.getSalePrice());
        assertEquals(entityDto.getStorePrice(), result.getStorePrice());
        assertEquals(entityDto.getStudentPrice(), result.getStudentPrice());
        assertEquals(entityDto.getUnits(), result.getUnits());
        assertEquals(entityDto.getUnitsSolid(), result.getUnitsSolid());
    }

    @Test
    public void testWillThrowUnauthorizedWhenTryCreateLocationWithoutAuthorization(){
        LocationRequestDto entityDto = input.mockDto(102);
        ErrorResponseDto result = testClient
                .post()
                .uri("/api/v1/event/102/location")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(entityDto)
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result).isNull();
    }

    @Test
    public void testWillThrowUnauthorizedWhenTryCreateLocationWithInvalidAccount(){
        LocationRequestDto entityDto = input.mockDto(102);
        ErrorResponseDto result = testClient
                .post()
                .uri("/api/v1/event/102/location")
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
    public void testWillThrowBadRequestWhenTryCreateLocationWithoutValidEvent(){
        LocationRequestDto entityDto = input.mockDto(102);
        ErrorResponseDto result = testClient
                .post()
                .uri("/api/v1/event/110/location")
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
    public void testWillGetEventBatch(){
        LocationResponseDto result = testClient
                .get()
                .uri("/api/v1/event/location/101")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(LocationResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result.getId()).isNotNull();
        assertEquals("name",result.getName());
        assertEquals("description",result.getDescription());
        assertEquals(false,result.getIsActive());
        assertEquals(10,result.getUnits());
        assertEquals(10,result.getSalePrice());
        assertEquals(10,result.getUnitsSolid());
        assertEquals(10,result.getStudentPrice());
        assertEquals(10,result.getStorePrice());
    }

    @Test
    public void testWillThrowNotFoundWhenTryGetLocationWithInvalidId(){
        ErrorResponseDto result = testClient
                .get()
                .uri("/api/v1/event/location/111")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result).isNotNull();
        assertThat(result.getErrorMessage()).isEqualTo("Location not found with the given input data id : '111'");
    }
    @Test
    public void testWillThrowNotFoundWhenTryGetLocationWithoutAuthorization(){
        ErrorResponseDto result = testClient
                .get()
                .uri("/api/v1/event/location/111")
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result).isNotNull();
        assertThat(result.getErrorMessage()).isEqualTo("Location not found with the given input data id : '111'");
    }

    @Test
    public void testWillUpdateLocation(){
        LocationRequestDto entityDto = input.mockDto(101);
        LocationResponseDto result = testClient
                .patch()
                .uri("/api/v1/event/location/101")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .bodyValue(entityDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(LocationResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result.getId()).isNotNull();
        assertEquals(entityDto.getDescription(), result.getDescription());
        assertEquals(entityDto.getName(),result.getName());
        assertEquals(entityDto.getIsActive(),result.getIsActive());
        assertEquals(entityDto.getSalePrice(),result.getSalePrice());
        assertEquals(entityDto.getStorePrice(), result.getStorePrice());
        assertEquals(entityDto.getStudentPrice(), result.getStudentPrice());
        assertEquals(entityDto.getUnits(), result.getUnits());
        assertEquals(entityDto.getUnitsSolid(), result.getUnitsSolid());
    }

    @Test
    public void testWillThrowNotFoundWhenTryUpdateLocationWithInvalidId(){
        LocationRequestDto entityDto = input.mockDto(111);
        ErrorResponseDto result = testClient
                .patch()
                .uri("/api/v1/event/location/111")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .bodyValue(entityDto)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result).isNotNull();
        assertThat(result.getErrorMessage()).isEqualTo("Location not found with the given input data id : '111'");
    }

    @Test
    public void testWillThrowBadRequestWhenTryUpdateLocationWithoutAuthorization(){
        LocationRequestDto entityDto = input.mockDto(101);
        ErrorResponseDto result = testClient
                .patch()
                .uri("/api/v1/location/101")
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
    public void testWillThrowBadRequestWhenTryUpdateLocationWithInvalidAccount(){
        LocationRequestDto entityDto = input.mockDto(101);
        ErrorResponseDto result = testClient
                .patch()
                .uri("/api/v1/location/101")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(entityDto)
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result).isNull();
    }
}