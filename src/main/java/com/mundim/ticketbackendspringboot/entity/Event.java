package com.mundim.ticketbackendspringboot.entity;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Event extends BaseEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native")
    Long id;

    @Column(nullable = false, length = 100)
    String name;

    @Column(nullable = false)
    Boolean isActive;

    Boolean isVirtual;
    Boolean isPublished;

    @Column(nullable = false)
    LocalDateTime dateEnd;

    @Column(nullable = false)
    LocalDateTime  dateStart;

    @Column(nullable = false)
    String description;

    @Column(nullable = false)
    String videoUrl;



    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = Address.class)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "event_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Image> images;

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Batch> batches;

    @OneToMany(mappedBy = "event_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Leasing> leases;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "event_category", joinColumns = @JoinColumn(name = "event_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories = new ArrayList<>();

}
