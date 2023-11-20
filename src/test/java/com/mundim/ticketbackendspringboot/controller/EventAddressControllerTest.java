package com.mundim.ticketbackendspringboot.controller;

import com.mundim.ticketbackendspringboot.dto.request.AddressRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.AddressResponseDto;
import com.mundim.ticketbackendspringboot.dto.response.ErrorResponseDto;
import com.mundim.ticketbackendspringboot.mocks.MockEventAddress;
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
@Sql(scripts = "/sql/event-address/address-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/event-address/address-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class EventAddressControllerTest {
    MockEventAddress input;
    @Autowired
    WebTestClient testClient;

    @BeforeEach
    void setUpMocks() throws Exception {
        input = new MockEventAddress();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testWillCreateEventAddress(){
        AddressRequestDto entityDto = input.mockDto(101);
        AddressResponseDto result = testClient
                .post()
                .uri("/api/v1/event/101/address")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .bodyValue(entityDto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(AddressResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result.getId()).isNotNull();
        assertEquals(result.getCity(),entityDto.getCity());
        assertEquals(result.getUf(),entityDto.getUf());
        assertEquals(result.getNumber(),entityDto.getNumber());
        assertEquals(result.getStreet(),entityDto.getStreet());
        assertEquals(result.getZipcode(),entityDto.getZipcode());
        assertEquals(result.getComplement(),entityDto.getComplement());
        assertEquals(result.getNeighborhood(),entityDto.getNeighborhood());
        assertEquals(result.getMobileNumber(),entityDto.getMobileNumber());
    }

    @Test
    public void testWillThrowBadRequestWhenTryCreateEventAddressWithAddressAlreadyRegistered(){
        AddressRequestDto entityDto = input.mockDto(102);
        ErrorResponseDto result = testClient
                .post()
                .uri("/api/v1/event/102/address")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .bodyValue(entityDto)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result).isNotNull();
        assertThat(result.getErrorMessage()).isEqualTo("Address already registered");
    }

    @Test
    public void testWillThrowUnauthorizedWhenTryCreateEventAddressWithoutAuthorization(){
        AddressRequestDto entityDto = input.mockDto(102);
        ErrorResponseDto result = testClient
                .post()
                .uri("/api/v1/event/102/address")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(entityDto)
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result).isNull();
    }

    @Test
    public void testWillThrowBadRequestWhenTryCreateEventAddressWithoutValidAccount(){
        AddressRequestDto entityDto = input.mockDto(102);
        ErrorResponseDto result = testClient
                .post()
                .uri("/api/v1/event/110/address")
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
    public void testWillGetEventAddress(){
        AddressResponseDto result = testClient
                .get()
                .uri("/api/v1/event/address/101")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(AddressResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result.getId()).isNotNull();
        assertEquals("city",result.getCity());
        assertEquals("MG",result.getUf());
        assertEquals("123",result.getNumber());
        assertEquals("street",result.getStreet());
        assertEquals("12345678",result.getZipcode());
        assertEquals("complement",result.getComplement());
        assertEquals("neighborhood",result.getNeighborhood());
        assertEquals("123456789123",result.getMobileNumber());
    }

    @Test
    public void testWillThrowNotFoundWhenTryGetEventAddressWithInvalidId(){
        ErrorResponseDto result = testClient
                .get()
                .uri("/api/v1/event/address/111")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result).isNotNull();
        assertThat(result.getErrorMessage()).isEqualTo("Address not found with the given input data id : '111'");
    }

    @Test
    public void testWillThrowNotFoundWhenTryGetEventAddressWithoutAuthorization(){
        ErrorResponseDto result = testClient
                .get()
                .uri("/api/v1/event/address/111")
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result).isNotNull();
        assertThat(result.getErrorMessage()).isEqualTo("Address not found with the given input data id : '111'");
    }

    @Test
    public void testWillUpdateEventAddress(){
        AddressRequestDto entityDto = input.mockDto(101);
        AddressResponseDto result = testClient
                .patch()
                .uri("/api/v1/event/address/101")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .bodyValue(entityDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(AddressResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result.getId()).isNotNull();
        assertEquals(entityDto.getCity(),result.getCity());
        assertEquals(entityDto.getUf(),result.getUf());
        assertEquals(entityDto.getNumber(),result.getNumber());
        assertEquals(entityDto.getStreet(),result.getStreet());
        assertEquals(entityDto.getZipcode(),result.getZipcode());
        assertEquals(entityDto.getComplement(),result.getComplement());
        assertEquals(entityDto.getNeighborhood(),result.getNeighborhood());
        assertEquals(entityDto.getMobileNumber(),result.getMobileNumber());
    }

    @Test
    public void testWillThrowNotFoundWhenTryUpdateEventAddressWithInvalidId(){
        AddressRequestDto entityDto = input.mockDto(111);
        ErrorResponseDto result = testClient
                .patch()
                .uri("/api/v1/event/address/111")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .bodyValue(entityDto)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result).isNotNull();
        assertThat(result.getErrorMessage()).isEqualTo("Address not found with the given input data id : '111'");
    }

    @Test
    public void testWillThrowBadRequestWhenTryUpdateEventAddressWithoutAuthorization(){
        AddressRequestDto entityDto = input.mockDto(101);
        ErrorResponseDto result = testClient
                .patch()
                .uri("/api/v1/event/address/101")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(entityDto)
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result).isNull();
    }
}