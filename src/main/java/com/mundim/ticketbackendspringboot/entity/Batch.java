package com.mundim.ticketbackendspringboot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Batch extends BaseEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native")
    Long id;

    @NotBlank(message = "Percentage must not be null")
    @Pattern(regexp = "(^[0-9]+(\\.[0-9]{1,2})?$)")
    int percentage;

    @NotBlank(message = "Batch stop date must not be null")
    LocalDateTime batchStopDate;

    @NotBlank(message = "Sales quantity must not be null")
    int salesQtd;

    String description;

    @NotBlank(message = "Is active must not be null")
    Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, targetEntity = Event.class)
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event_id;
}
