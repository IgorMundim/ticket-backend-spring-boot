package com.mundim.ticketbackendspringboot.service.impl;

import com.mundim.ticketbackendspringboot.dto.request.BatchRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.BatchResponseDto;
import com.mundim.ticketbackendspringboot.entity.Batch;
import com.mundim.ticketbackendspringboot.exception.ResourceNotFoundException;
import com.mundim.ticketbackendspringboot.mocks.MockBatch;
import com.mundim.ticketbackendspringboot.repository.BatchRepository;
import com.mundim.ticketbackendspringboot.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BatchServiceTest {
    MockBatch inputBatch;

    @InjectMocks
    BatchService service;
    @Mock
    EventRepository eventRepository;
    @Mock
    BatchRepository batchRepository;

    @BeforeEach
    void setUpMocks() throws Exception{
        inputBatch = new MockBatch();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("It should not accept the creation of an 'Event Batch' with an invalid event id.")
    void testWillThrowResourceNotFoundExceptionWhenTryGetBatchToCreateAddress(){
        BatchRequestDto entityDto = inputBatch.mockDto(1);
        when(eventRepository.findById(1L)).thenReturn(
                Optional.empty()
        );
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> { service.create(entityDto, 1L); });
        String expectedMessage = "Event not found with the given input data id : '1'";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }
    @Test
    @DisplayName("It should not found a 'Batch' with invalid id.")
    void testWillThrowResourceNotFoundExceptionWhenTryGetBatchById(){
        Batch entity = inputBatch.mockEntity(1);
        when(batchRepository.findById(entity.getId())).thenReturn(Optional.empty());
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> { service.fetch(entity.getId()); });
        String expectedMessage = "Batch not found with the given input data id : '1'";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }
    @Test
    @DisplayName("It should not found a 'Batch' with invalid id.")
    void testWillThrowResourceNotFoundExceptionWhenTryUpdateBatchById(){
        Batch entity = inputBatch.mockEntity(1);
        BatchRequestDto entityDto = inputBatch.mockDto(1);
        when(batchRepository.findById(entity.getId())).thenReturn(Optional.empty());
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> { service.update( entityDto,entity.getId()); });
        String expectedMessage = "Batch not found with the given input data id : '1'";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    @DisplayName("It should create a 'Batch'")
    void testWillCreateBatch(){
        Batch entity = inputBatch.mockEntity(1);

        BatchRequestDto entityDto = inputBatch.mockDto(1);
        when(eventRepository.findById(1L)).thenReturn(Optional.of(entity.getEvent()));
        BatchResponseDto result = service.create(entityDto, entity.getId());
        assertNotNull(result);
        assertEquals(BatchResponseDto.class, result.getClass());
        assertTrue(result.getLinks().toString().contains("</api/v1/event/batch/{id}>;rel=\"self\",</api/v1/event/2>;rel=\"Event\""));
        assertEquals(10.1, result.getPercentage());
        assertEquals(LocalDateTime.of(2024, Month.FEBRUARY, 28, 22,1,1),result.getBatchStopDate());
        assertEquals(10, result.getSalesQtd());
        assertEquals("DescriptionTest1", result.getDescription());
        assertEquals(true, result.getIsActive());
    }
    @Test
    @DisplayName("It should get a 'Batch' by id")
    void testWillGetBatchById(){
        Batch entity = inputBatch.mockEntity(1);

        when(batchRepository.findById(1L)).thenReturn(Optional.of(entity));
        BatchResponseDto result = service.fetch(entity.getId());
        assertNotNull(result);
        assertEquals(BatchResponseDto.class, result.getClass());
        assertTrue(result.getLinks().toString().contains("</api/v1/event/batch/1>;rel=\"self\",</api/v1/event/2>;rel=\"Event\""));
        assertEquals(10.1, result.getPercentage());
        assertEquals(LocalDateTime.of(2024, Month.FEBRUARY, 28, 22,1,1),result.getBatchStopDate());
        assertEquals(10, result.getSalesQtd());
        assertEquals("DescriptionTest1", result.getDescription());
        assertEquals(true, result.getIsActive());
    }
    @Test
    @DisplayName("It should get all 'Batch' by event id")
    void testWillGetAllBatchByEventId(){
        List<Batch> entities = inputBatch.mockEntityList();

        when(batchRepository.findByEventId(2L)).thenReturn(entities);
        List<BatchResponseDto> result = service.fetchAll(2L);
        assertNotNull(result);
        BatchResponseDto firstBatch = result.get(1);
        assertEquals(BatchResponseDto.class, firstBatch.getClass());
        assertTrue(firstBatch.getLinks().toString().contains("</api/v1/event/batch/1>;rel=\"self\",</api/v1/event/2>;rel=\"Event\""));
        assertEquals(10.1, firstBatch.getPercentage());
        assertEquals(LocalDateTime.of(2024, Month.FEBRUARY, 28, 22,1,1),firstBatch.getBatchStopDate());
        assertEquals(10, firstBatch.getSalesQtd());
        assertEquals("DescriptionTest1", firstBatch.getDescription());
        assertEquals(true, firstBatch.getIsActive());
    }
    @Test
    @DisplayName("It should update a 'Batch' by id")
    void testWillUpdateBatchById(){
        Batch entity = inputBatch.mockEntity(1);

        BatchRequestDto entityDto = inputBatch.mockDto(2);
        when(batchRepository.findById(1L)).thenReturn(Optional.of(entity));
        BatchResponseDto result = service.update(entityDto, entity.getId());
        assertNotNull(result);
        assertEquals(BatchResponseDto.class, result.getClass());
        assertTrue(result.getLinks().toString().contains("</api/v1/event/batch/1>;rel=\"self\",</api/v1/event/2>;rel=\"Event\""));
        assertEquals(10.1, result.getPercentage());
        assertEquals(LocalDateTime.of(2024, Month.FEBRUARY, 28, 22,1,1),result.getBatchStopDate());
        assertEquals(10, result.getSalesQtd());
        assertEquals("DescriptionTest2", result.getDescription());
        assertEquals(true, result.getIsActive());
    }
}