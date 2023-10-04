package com.mundim.ticketbackendspringboot.entity;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Name must not be null")
    @Size(min=2, max = 100, message = "Name must not be less than 2 and more than 100")
    String name;

    Boolean isActive;
    Boolean isVirtual;
    Boolean isPublished;

    @NotBlank(message = "Date End must not be null")
    LocalDateTime dateEnd;

    @NotBlank(message = "Date Start must not be null")
    String date_start;

    @NotBlank(message = "Name must not be null")
    String description;

    @NotBlank(message = "Video URL must not be null")
    String videUrl;

    @OneToMany(mappedBy = "event_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Image> images;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId")
    private Address address;

}
