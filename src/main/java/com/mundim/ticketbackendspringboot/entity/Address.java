package com.mundim.ticketbackendspringboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "Zip code must not be blank")
    @Pattern(regexp = "(^$|[0-9]{8})", message = "Zip code must be 5 digits")
    String zipcode;
    String complement;
    @NotBlank(message = "City must not be blank")
    @Size(min = 2, max = 100, message = "City must be more than 2 characters and less 100")
    String city;
    @Size(min = 2, max = 100, message = "Neighborhood must be more than 2 characters and less 100")
    String neighborhood;
    @Size(min = 2, max = 100, message = "Number must be more than 2 characters and less 100")
    String number;
    @Size(min = 2, max = 100, message = "Street must be more than 2 characters and less 100")
    String street;
    @Pattern(regexp = "(^(?i)(\\s*(AC|AL|AP|AM|BA|CE|DF|ES|GO|MA|MT|MS|MG|PA|PB|PR|PE|PI|RJ|RN|RS|RO|RR|SC|SP|SE|TO)?)$)", message = "UF must be 2 characters")
    String uf;
}
