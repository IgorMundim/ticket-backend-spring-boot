package com.mundim.ticketbackendspringboot.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor @NoArgsConstructor
public class EventAddress extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    Long id;

    @Column(nullable = false, length = 12)
    private String mobileNumber;

    @Column(nullable = false, length = 8)
    String zipcode;

    String complement;

    @Column(nullable = false, length = 100)
    String city;

    @Column(nullable = false, length = 100)
    String neighborhood;

    @Column(nullable = false)
    String number;

    @Column(nullable = false, length = 100)
    String street;

    @Column(nullable = false, length = 2)
    String uf;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true, targetEntity = Event.class)
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event;
}
