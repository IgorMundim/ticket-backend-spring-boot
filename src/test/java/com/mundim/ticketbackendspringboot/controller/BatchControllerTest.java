package com.mundim.ticketbackendspringboot.controller;

import com.mundim.ticketbackendspringboot.dto.request.BatchRequestDto;
import com.mundim.ticketbackendspringboot.dto.request.LocationRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.BatchResponseDto;
import com.mundim.ticketbackendspringboot.dto.response.ErrorResponseDto;
import com.mundim.ticketbackendspringboot.dto.response.LocationResponseDto;
import com.mundim.ticketbackendspringboot.mocks.MockBatch;
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
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/event-batch/batch-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/event-batch/batch-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class BatchControllerTest {
    MockBatch input;
    @Autowired
    WebTestClient testClient;
    @BeforeEach
    void setUpMocks() throws Exception {
        input = new MockBatch();
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testWillCreateBatch(){
        BatchRequestDto entityDto = input.mockDto(101);
        BatchResponseDto result = testClient
                .post()
                .uri("/api/v1/event/101/batch")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .bodyValue(entityDto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(BatchResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result.getId()).isNotNull();
        assertEquals(entityDto.getBatchStopDate(), result.getBatchStopDate());
        assertEquals(entityDto.getIsActive(), result.getIsActive());
        assertEquals(entityDto.getDescription(), result.getDescription());
        assertEquals(entityDto.getSalesQtd(), result.getSalesQtd());
        assertEquals(entityDto.getPercentage(), result.getPercentage());

    }

    @Test
    public void testWillThrowUnauthorizedWhenTryCreateBatchWithoutAuthorization(){
        BatchRequestDto entityDto = input.mockDto(102);
        ErrorResponseDto result = testClient
                .post()
                .uri("/api/v1/event/102/batch")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(entityDto)
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result).isNull();
    }

    @Test
    public void testWillThrowUnauthorizedWhenTryCreateBatchWithInvalidAccount(){
        BatchRequestDto entityDto = input.mockDto(102);
        ErrorResponseDto result = testClient
                .post()
                .uri("/api/v1/event/102/batch")
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
    public void testWillThrowBadRequestWhenTryCreateBatchWithoutValidEvent(){
        BatchRequestDto entityDto = input.mockDto(102);
        ErrorResponseDto result = testClient
                .post()
                .uri("/api/v1/event/110/batch")
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
        BatchResponseDto result = testClient
                .get()
                .uri("/api/v1/event/batch/101")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(BatchResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result.getId()).isNotNull();
        assertEquals("2023-11-10T11:30", result.getBatchStopDate().toString());
        assertEquals(true, result.getIsActive());
        assertEquals("description", result.getDescription());
        assertEquals(10, result.getSalesQtd());
        assertEquals(11.1, result.getPercentage());
    }

    @Test
    public void testWillThrowNotFoundWhenTryGetBatchWithInvalidId(){
        ErrorResponseDto result = testClient
                .get()
                .uri("/api/v1/event/batch/111")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result).isNotNull();
        assertThat(result.getErrorMessage()).isEqualTo("Batch not found with the given input data id : '111'");
    }
    @Test
    public void testWillThrowNotFoundWhenTryGetBatchWithoutAuthorization(){
        ErrorResponseDto result = testClient
                .get()
                .uri("/api/v1/event/batch/111")
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result).isNotNull();
        assertThat(result.getErrorMessage()).isEqualTo("Batch not found with the given input data id : '111'");
    }

    @Test
    public void testWillUpdateBatch(){
        BatchRequestDto entityDto = input.mockDto(101);
        BatchResponseDto result = testClient
                .patch()
                .uri("/api/v1/event/batch/101")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .bodyValue(entityDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(BatchResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result.getId()).isNotNull();
        assertEquals(entityDto.getBatchStopDate(), result.getBatchStopDate());
        assertEquals(entityDto.getIsActive(), result.getIsActive());
        assertEquals(entityDto.getDescription(), result.getDescription());
        assertEquals(entityDto.getSalesQtd(), result.getSalesQtd());
        assertEquals(entityDto.getPercentage(), result.getPercentage());
    }

    @Test
    public void testWillThrowNotFoundWhenTryUpdateBatchWithInvalidId(){
        BatchRequestDto entityDto = input.mockDto(111);
        ErrorResponseDto result = testClient
                .patch()
                .uri("/api/v1/event/batch/111")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "admin", "123456"))
                .bodyValue(entityDto)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result).isNotNull();
        assertThat(result.getErrorMessage()).isEqualTo("Batch not found with the given input data id : '111'");
    }

    @Test
    public void testWillThrowBadRequestWhenTryUpdateBatchWithoutAuthorization(){
        BatchRequestDto entityDto = input.mockDto(101);
        ErrorResponseDto result = testClient
                .patch()
                .uri("/api/v1/batch/101")
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
    public void testWillThrowBadRequestWhenTryUpdateBatchWithInvalidAccount(){
        BatchRequestDto entityDto = input.mockDto(101);
        ErrorResponseDto result = testClient
                .patch()
                .uri("/api/v1/batch/101")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(entityDto)
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody(ErrorResponseDto.class)
                .returnResult().getResponseBody();
        assertThat(result).isNull();
    }
}