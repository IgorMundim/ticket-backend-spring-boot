package com.mundim.ticketbackendspringboot.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Category extends BaseEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native")
    Long id;

    @Column(nullable = false, length = 100)
    String name;

    @Column(nullable = false)
    Boolean isActive;

    @Column(nullable = false)
    String url;

    @Column(nullable = false, length = 100)
    String alt_text;

    @ManyToMany(mappedBy = "categories")
    private List<Event> events = new ArrayList<>();
}
