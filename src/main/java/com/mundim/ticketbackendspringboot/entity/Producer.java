package com.mundim.ticketbackendspringboot.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Producer extends BaseEntity {
    @Id
    private int producerId;

    @Column(nullable = false,length = 100)
    String companyName;

    @Column(nullable = false, unique = true, length = 14)
    int cnpj;

    @Column(nullable = false, length = 100)
    String fantasyName;

    @Column(nullable = false, length = 14)
    String stateRegistration;

    @Column(nullable = false, length = 100)
    String municipalRegistration;
}
