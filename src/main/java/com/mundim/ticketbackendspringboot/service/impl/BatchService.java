package com.mundim.ticketbackendspringboot.service.impl;


import com.mundim.ticketbackendspringboot.dto.request.BatchRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.BatchResponseDto;
import com.mundim.ticketbackendspringboot.entity.Batch;
import com.mundim.ticketbackendspringboot.entity.Event;
import com.mundim.ticketbackendspringboot.exception.ResourceNotFoundException;
import com.mundim.ticketbackendspringboot.mapper.Mapper;
import com.mundim.ticketbackendspringboot.repository.BatchRepository;
import com.mundim.ticketbackendspringboot.repository.EventRepository;
import com.mundim.ticketbackendspringboot.service.IBatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Service
public class BatchService implements IBatchService {
    private final EventRepository eventRepository;
    private final BatchRepository batchRepository;
    @Transactional
    @Override
    public BatchResponseDto create(BatchRequestDto batchDto, Long id) {
        Event event = eventRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Event", "id", Long.toString(id))
        );

        Batch batch = Mapper.map(batchDto, Batch.class);
        batch.setEvent(event);
        batchRepository.save(batch);
        return Mapper.map(batch, BatchResponseDto.class);
    }

    @Override
    public BatchResponseDto fetch(Long id) {
        Batch batch = batchRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Batch", "id", Long.toString(id))
        );
        return Mapper.map(batch, BatchResponseDto.class);
    }

    @Override
    public BatchResponseDto update(BatchRequestDto batchDto, Long id) {
        batchRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Batch", "id", Long.toString(id))
        );
        Batch batch = Mapper.map(batchDto, Batch.class);
        batch.setId(id);
        batchRepository.save(batch);
        return Mapper.map(batch, BatchResponseDto.class);
    }

    @Override
    public List<BatchResponseDto> fetchAll(Long id) {
        List<Batch> batch = batchRepository.findByEventId(id);
        return  Mapper.mapList(batch, BatchResponseDto.class);
    }

}
