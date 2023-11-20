package com.mundim.ticketbackendspringboot.repository;

import com.mundim.ticketbackendspringboot.entity.Category;
import com.mundim.ticketbackendspringboot.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
  List<Category> findByEventsId(Long id);
}
