package com.mundim.ticketbackendspringboot.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Permission extends  BaseEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native")
    private int id;

    @Column(nullable = false, unique = true)
    private String roleName;

    @OneToMany(mappedBy = "permission", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Account> accounts;
}
