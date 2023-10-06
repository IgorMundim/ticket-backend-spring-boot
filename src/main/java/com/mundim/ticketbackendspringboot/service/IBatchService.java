package com.mundim.ticketbackendspringboot.service;

import com.mundim.ticketbackendspringboot.dto.request.BatchRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.BatchResponseDto;
import com.mundim.ticketbackendspringboot.entity.Batch;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IBatchService {
    /**
     *
     * @param batchDto - AddressDto Object
     * @param id - id of event
     */
    BatchResponseDto createBatch(BatchRequestDto batchDto, Long id);

    /**
     *
     * @param id - Input Batch ID
     * @return Batch Details based on a given id
     */
    BatchResponseDto fetchBatch(Long id);

    /**
     *
     * @param batchDto - BatchDto Object
     * @return boolean indicating if the update of Batch details is successful or not
     */
    BatchResponseDto updateBatch(BatchRequestDto batchDto, Long id);

    /**
     *
     * @param id - Event id
     * @return - All batch by id
     */
    List<BatchResponseDto> fetchAllBatch(Long id);
//    /**
//     *
//     * @param id - Input Address ID
//     */
//    void deleteAddress(Long id);
}
