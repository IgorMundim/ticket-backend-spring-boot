package com.mundim.ticketbackendspringboot.service;

import com.mundim.ticketbackendspringboot.dto.request.EventRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.EventResponseDto;

public interface IEventService {
    /**
     *
     * @param eventDto - EventDto Object
     */
    EventResponseDto create(EventRequestDto eventDto);

    /**
     *
     * @param id - Input Event ID
     * @return Event Details based on a given id
     */
    EventResponseDto fetch(Long id);

    /**
     *
     * @param eventDto - EventDto Object
     * @return boolean indicating if the update of Event details is successful or not
     */
    EventResponseDto update(EventRequestDto eventDto, Long id);
    /**
     *
     * @param id - Input Event ID
     */
    void delete(Long id);
}
