package com.mundim.ticketbackendspringboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Category extends BaseEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native")
    Long id;
    @NotBlank(message = "Name must not be null")
    @Size(min=2, max = 100, message = "Name must not be less than 2 and more than 100")
    String name;
    Boolean isActive;
    @NotBlank(message = "Name must not be null")
    String url;
    @NotBlank(message = "Alt text must not be null")
    @Size(min=2, max = 100, message = "Alt text must not be less than 2 and more than 100")
    String alt_text;
}
