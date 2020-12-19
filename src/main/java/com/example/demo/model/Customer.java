package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "customer")
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
//    @Pattern(regexp = "[a-zA-Z]", message = "First name must contain only alphabetic characters")
    private String firstName;
    @NotNull
//    @Pattern(regexp = "[a-zA-Z]", message = "Last name must contain only alphabetic characters")
    private String lastName;
    @NotNull
    private String bookingId;
    @NotNull
    @Pattern(regexp = "[[0-9]]+", message = "Phone number must contain digits only")
    private String phoneNumber;
    @NotNull
    @Email
    private String email;


}
