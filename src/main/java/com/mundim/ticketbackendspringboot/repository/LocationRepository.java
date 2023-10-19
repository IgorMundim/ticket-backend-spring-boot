package com.mundim.ticketbackendspringboot.repository;

import com.mundim.ticketbackendspringboot.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByEventId(Long id);
}
