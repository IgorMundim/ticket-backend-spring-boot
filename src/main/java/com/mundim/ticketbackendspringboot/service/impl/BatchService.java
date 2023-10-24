package com.mundim.ticketbackendspringboot.service.impl;


import com.mundim.ticketbackendspringboot.controller.BatchController;
import com.mundim.ticketbackendspringboot.controller.EventController;
import com.mundim.ticketbackendspringboot.controller.LocationController;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


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
        return Mapper.map(batch, BatchResponseDto.class)
                .add(linkTo(methodOn(BatchController.class).getById(batch.getId())).withSelfRel())
                .add(linkTo(methodOn(EventController.class).getById(event.getId())).withRel("Event"));
    }

    @Override
    public BatchResponseDto fetch(Long id) {
        Batch batch = batchRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Batch", "id", Long.toString(id))
        );
        return Mapper.map(batch, BatchResponseDto.class)
                .add(linkTo(methodOn(BatchController.class).getById(batch.getId())).withSelfRel())
                .add(linkTo(methodOn(EventController.class).getById(batch.getEvent().getId())).withRel("Event"));
    }

    @Override
    public BatchResponseDto update(BatchRequestDto batchDto, Long id) {
        batchRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Batch", "id", Long.toString(id))
        );
        Batch batch = Mapper.map(batchDto, Batch.class);
        batch.setId(id);
        batchRepository.save(batch);
        return Mapper.map(batch, BatchResponseDto.class)
                .add(linkTo(methodOn(BatchController.class).getById(batch.getId())).withSelfRel())
                .add(linkTo(methodOn(EventController.class).getById(batch.getEvent().getId())).withRel("Event"));
    }

    @Override
    public List<BatchResponseDto> fetchAll(Long id) {
        List<BatchResponseDto> batches = Mapper.mapList(batchRepository.findByEventId(id), BatchResponseDto.class);
         batches
                 .forEach(p -> Mapper.map(p, BatchResponseDto.class)
                        .add(linkTo(methodOn(BatchController.class).getById(p.getId())).withSelfRel())
                        .add(linkTo(methodOn(EventController.class).getById(id)).withRel("Event")));
        return  batches;
    }

}
