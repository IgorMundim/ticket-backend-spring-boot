package com.mundim.ticketbackendspringboot.repository;

import com.mundim.ticketbackendspringboot.entity.EventAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventAddressRepository extends JpaRepository<EventAddress, Long> {
    Optional<EventAddress> findByEventId(Long id);

}
