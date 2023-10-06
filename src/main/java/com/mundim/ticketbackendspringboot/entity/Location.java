package com.mundim.ticketbackendspringboot.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Location extends BaseEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native")
    Long id;

    @Column(nullable = false, length = 100)
    String name;

    String description;

    @Column(nullable = false)
    Boolean isActive;

    @Column(nullable = false)
    Long storePrice;

    @Column(nullable = false)
    Long salePrice;

    @Column(nullable = false)
    Long studentPrice;

    @Column(nullable = false)
    int unitsSolid;

    @Column(nullable = false)
    int units;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, targetEntity = Event.class)
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event;
}
