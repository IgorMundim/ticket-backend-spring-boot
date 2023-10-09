package com.mundim.ticketbackendspringboot.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;


@Data
@Entity
@EqualsAndHashCode(callSuper = true)
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
