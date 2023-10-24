package com.mundim.ticketbackendspringboot.service.impl;

import com.mundim.ticketbackendspringboot.controller.*;

import com.mundim.ticketbackendspringboot.dto.request.EventRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.EventResponseDto;
import com.mundim.ticketbackendspringboot.entity.Event;
import com.mundim.ticketbackendspringboot.exception.ResourceNotFoundException;
import com.mundim.ticketbackendspringboot.mapper.Mapper;
import com.mundim.ticketbackendspringboot.repository.EventRepository;
import com.mundim.ticketbackendspringboot.service.IEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequiredArgsConstructor
@Service
public class EventService implements IEventService {
    private final EventRepository eventRepository;
    @Autowired
    PagedResourcesAssembler<EventResponseDto> assembler;
    @Override
    public EventResponseDto create(EventRequestDto eventDto) {
        Event event = Mapper.map(eventDto, Event.class);
        eventRepository.save(event);
        return Mapper.map(event, EventResponseDto.class)
                .add(linkTo(methodOn(EventController.class).getById(event.getId())).withSelfRel())
                .add(linkTo(methodOn(LocationController.class).findAllByIdEvent(event.getId())).withRel("Locations"))
                .add(linkTo(methodOn(BatchController.class).getAllByIdEvent(event.getId())).withRel("Batch"))
                .add(linkTo(methodOn(CategoryController.class).getAllByEventId(event.getId())).withRel("Category"))
                .add(linkTo(methodOn(EventAddressController.class).getByEventId(event.getId())).withRel("Address"));
    }

    @Override
    public EventResponseDto fetch(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Event", "id", Long.toString(id))
        );
        return Mapper.map(event, EventResponseDto.class)
                .add(linkTo(methodOn(EventController.class).getById(id)).withSelfRel())
                .add(linkTo(methodOn(LocationController.class).findAllByIdEvent(id)).withRel("Locations"))
                .add(linkTo(methodOn(BatchController.class).getAllByIdEvent(id)).withRel("Batch"))
                .add(linkTo(methodOn(CategoryController.class).getAllByEventId(id)).withRel("Category"))
                .add(linkTo(methodOn(EventAddressController.class).getByEventId(id)).withRel("Address"));
    }

    @Override
    public PagedModel<EntityModel<EventResponseDto>> findAll(Pageable pageable) {
        var eventPage = eventRepository.findAll(pageable);
        var eventPageDto = eventPage.map(p -> Mapper.map(p, EventResponseDto.class)
                .add(linkTo(methodOn(EventController.class).getById(p.getId())).withSelfRel())
                .add(linkTo(methodOn(LocationController.class).findAllByIdEvent(p.getId())).withRel("Locations"))
                .add(linkTo(methodOn(BatchController.class).getAllByIdEvent(p.getId())).withRel("Batch"))
                .add(linkTo(methodOn(CategoryController.class).getAllByEventId(p.getId())).withRel("Category"))
                .add(linkTo(methodOn(EventAddressController.class).getByEventId(p.getId())).withRel("Address"))
        );
        Link link = linkTo(methodOn(EventController.class)
                .findAll(pageable.getPageNumber(), pageable.getPageSize(), "asc")).withSelfRel();
        return assembler.toModel(eventPageDto, link);
    }

    @Override
    public EventResponseDto update(EventRequestDto eventDto, Long id) {
        Event event = eventRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Event", "id", Long.toString(id))
        );
        Event newEvent = Mapper.map(eventDto, Event.class);
        event.setName(newEvent.getName());
        event.setIsActive(newEvent.getIsActive());
        event.setIsVirtual(newEvent.getIsVirtual());
        event.setIsPublished(newEvent.getIsPublished());
        event.setDateEnd(newEvent.getDateEnd());
        event.setDateStart(newEvent.getDateStart());
        event.setDescription(newEvent.getDescription());
        event.setVideoUrl(newEvent.getVideoUrl());
        eventRepository.save(event);
        return Mapper.map(event, EventResponseDto.class).add(linkTo(methodOn(EventController.class).getById(id)).withSelfRel())
                .add(linkTo(methodOn(LocationController.class).findAllByIdEvent(id)).withRel("Locations"))
                .add(linkTo(methodOn(BatchController.class).getAllByIdEvent(id)).withRel("Batch"))
                .add(linkTo(methodOn(CategoryController.class).getAllByEventId(id)).withRel("Category"))
                .add(linkTo(methodOn(EventAddressController.class).getByEventId(id)).withRel("Address"));
    }

    @Override
    public void delete(Long id) {
        eventRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Event", "id", Long.toString(id))
        );
        eventRepository.deleteById(id);
    }
}
