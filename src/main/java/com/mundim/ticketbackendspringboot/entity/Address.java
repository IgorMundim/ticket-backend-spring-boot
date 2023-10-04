package com.mundim.ticketbackendspringboot.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Address extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    int addressId;

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
}
