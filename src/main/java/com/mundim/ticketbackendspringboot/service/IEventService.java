package com.mundim.ticketbackendspringboot.service;

import com.mundim.ticketbackendspringboot.dto.request.EventRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.EventResponseDto;

public interface IEventService {
    /**
     *
     * @param eventDto - EventDto Object
     */
    EventResponseDto createEvent(EventRequestDto eventDto);

    /**
     *
     * @param id - Input Event ID
     * @return Event Details based on a given id
     */
    EventResponseDto fetchEvent(Long id);

    /**
     *
     * @param eventDto - EventDto Object
     * @return boolean indicating if the update of Event details is successful or not
     */
    EventResponseDto updateEvent(EventRequestDto eventDto, Long id);
    /**
     *
     * @param id - Input Event ID
     */
    void deleteEvent(Long id);
}
