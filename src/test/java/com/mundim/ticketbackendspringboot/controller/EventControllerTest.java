package com.mundim.ticketbackendspringboot.controller;

import com.mundim.ticketbackendspringboot.dto.request.EventRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.ErrorResponseDto;
import com.mundim.ticketbackendspringboot.dto.response.EventResponseDto;
import com.mundim.ticketbackendspringboot.dto.response.ResponseDto;
import com.mundim.ticketbackendspringboot.mocks.MockEvent;
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
@Sql(scripts = "/sql/event/event-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/event/event-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class EventControllerTest {
    MockEvent input;
    @Autowired
    WebTestClient testClient;
    @BeforeEach
    void setUpMocks() throws Exception {
        input = new MockEvent();
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testWillCreateEvent(){
        EventRequestDto entityDto = input.mockDto(1);
        EventResponseDto result = testClient
                .post()
                .uri("/api/v1/event/")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(entityDto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(EventResponseDto.class)
                .returnResult().getResponseBody();
        assertEquals(entityDto.getDateEnd(),result.getDateEnd());
        assertEquals(entityDto.getDescription(),result.getDescription());
        assertEquals(entityDto.getName(),result.getName());
        assertEquals(entityDto.getIsActive(),result.getIsActive());
        assertEquals(entityDto.getDateStart(),result.getDateStart());
        assertEquals(entityDto.getIsPublished(),result.getIsPublished());
        assertEquals(entityDto.getVideoUrl(),result.getVideoUrl());
        assertEquals(entityDto.getIsVirtual(),result.getIsVirtual());
    }

    @Test
    public void testWillThrowUnauthorizedWhenTryCreateEventWithoutAuthorization(){
        EventRequestDto entityDto = input.mockDto(1);
        testClient
                .post()
                .uri("/api/v1/event/")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "user", "123456"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(entityDto)
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody(EventResponseDto.class)
                .returnResult().getResponseBody();
    }
    @Test
    public void testWillThrowUnauthorizedWhenTryCreateEventWithoutAdminAuthorization(){
        EventRequestDto entityDto = input.mockDto(1);
        testClient
                .post()
                .uri("/api/v1/event/")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(entityDto)
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody(EventResponseDto.class)
                .returnResult().getResponseBody();
    }
    @Test
    public void testWillGetEvent(){
        EventResponseDto result = testClient
                .get()
                .uri("/api/v1/event/101")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "user", "123456"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(EventResponseDto.class)
                .returnResult().getResponseBody();
        assertEquals("2023-11-10T11:30",result.getDateEnd().toString());
        assertEquals("description",result.getDescription());
        assertEquals("name",result.getName());
        assertEquals(false,result.getIsActive());
        assertEquals("2023-11-01T11:30",result.getDateStart().toString());
        assertEquals(false,result.getIsPublished());
        assertEquals("videoUrl",result.getVideoUrl());
        assertEquals(true,result.getIsVirtual());
    }

    @Test
    public void testWillThrowNotFoundWhenTryGetEventWithInvalidId(){
        ErrorResponseDto result = testClient
                .get()
                .uri("/api/v1/event/111")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "user", "123456"))
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result.getErrorMessage()).isEqualTo("Event not found with the given input data id : '111'");
    }

    @Test
    public void testWillDeleteEvent(){
        ResponseDto result =testClient
                .delete()
                .uri("/api/v1/event/101")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(ResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result.getMessage()).isEqualTo("Request processed successfully");
    }
    @Test
    public void testWillThrowUnauthorizedWhenTryDeleteEventWithCustomerPermission(){
        ErrorResponseDto result = testClient
                .delete()
                .uri("/api/v1/event/101")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "user", "123456"))
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result).isNull();
    }

    @Test
    public void testWillThrowUnauthorizedWhenTryDeleteEventWithoutAuthorization(){
        ErrorResponseDto result = testClient
                .delete()
                .uri("/api/v1/event/101")
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result).isNull();
    }

    @Test
    public void testWillThrowNotFoundWhenTryDeleteEventWithInvalidId(){
        ErrorResponseDto result = testClient
                .delete()
                .uri("/api/v1/event/111")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result.getErrorMessage()).isEqualTo("Event not found with the given input data id : '111'");
    }

    @Test
    public void testWillUpdateEvent(){
        EventRequestDto entityDto = input.mockDto(101);
        EventResponseDto result = testClient
                .patch()
                .uri("/api/v1/event/101")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(entityDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(EventResponseDto.class)
                .returnResult().getResponseBody();
        assertEquals(entityDto.getDateEnd(),result.getDateEnd());
        assertEquals(entityDto.getDescription(),result.getDescription());
        assertEquals(entityDto.getName(),result.getName());
        assertEquals(entityDto.getIsActive(),result.getIsActive());
        assertEquals(entityDto.getDateStart(),result.getDateStart());
        assertEquals(entityDto.getIsPublished(),result.getIsPublished());
        assertEquals(entityDto.getVideoUrl(),result.getVideoUrl());
        assertEquals(entityDto.getIsVirtual(),result.getIsVirtual());
    }

    @Test
    public void testWillThrowUnauthorizedWhenTryUpdateEventWithoutAdminAuthorization(){
        EventRequestDto entityDto = input.mockDto(101);
        testClient
                .patch()
                .uri("/api/v1/event/101")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "user", "123456"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(entityDto)
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody(EventResponseDto.class)
                .returnResult().getResponseBody();
    }

    @Test
    public void testWillThrowUnauthorizedWhenTryUpdateEventWithoutAuthorization(){
        EventRequestDto entityDto = input.mockDto(101);
        testClient
                .patch()
                .uri("/api/v1/event/101")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(entityDto)
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody(EventResponseDto.class)
                .returnResult().getResponseBody();
    }

    @Test
    public void testWillThrowNotFoundWhenTryUpdateEventWithInvalidId(){
        EventRequestDto entityDto = input.mockDto(101);
        ErrorResponseDto result = testClient
                .patch()
                .uri("/api/v1/event/111")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(entityDto)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result.getErrorMessage()).isEqualTo("Event not found with the given input data id : '111'");
    }

}