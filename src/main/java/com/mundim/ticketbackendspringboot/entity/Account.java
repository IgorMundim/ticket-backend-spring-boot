package com.mundim.ticketbackendspringboot.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Account extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native")
    private Long id;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false, length = 200)
    private String pwd;

    private String profileImage;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = Permission.class)
    @JoinColumn(name = "permission_id", referencedColumnName = "id")
    private Permission permission_id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address_id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Producer.class)
    @JoinColumn(name = "producer_id", referencedColumnName = "producerId")
    private Producer producer;

}
