package com.mundim.ticketbackendspringboot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Leasing extends BaseEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native")
    Long id;

    @NotBlank(message = "Name must not be null")
    @Size(min=2, max = 100, message = "Name must not be less than 2 and more than 100")
    String name;

    String description;

    @NotBlank(message = "Is active must not be null")
    Boolean isActive;

    @NotBlank(message = "Store price must not be null")
    @Pattern(regexp = "(^[0-9]+(\\.[0-9]{1,2})?$)")
    int storePrice;

    @NotBlank(message = "Sale price must not be null")
    @Pattern(regexp = "(^[0-9]+(\\.[0-9]{1,2})?$)")
    int salePrice;

    @NotBlank(message = "Student price must not be null")
    @Pattern(regexp = "(^[0-9]+(\\.[0-9]{1,2})?$)")
    int studentPrice;

    @NotBlank(message = "Units solid must not be null")
    int units_solid;

    @NotBlank(message = "Units must not be null")
    int units;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, targetEntity = Event.class)
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event_id;
}
