package com.example.demo.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;

import javax.persistence.*;
import javax.print.DocFlavor;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
//@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "user")

public class User {
    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    private Long id;

    @Size(min = 4, max = 16, message = "Username must be between 4 and 16")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "Username may contain only alphanumeric characters")
    @Column(nullable = false, unique = true)
    private String username;

    @Email
    @Size(min = 1, message = "Email field cannot be empty ")
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @NotEmpty
    @Size(min = 4, message = "Passwords must be at least 4 characters in length")
    @Column(nullable = false)
    private String password;

//    @Column(nullable = false)
//    private String role;

    @Column(nullable = false)
    private String role = "ROLE_ADMIN";

    @Column(columnDefinition = "boolean not null default false")
    private Boolean enabled;


    //DATABASE
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter(value = AccessLevel.NONE)
    private List<Booking> bookings = new ArrayList<>();

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }



    //    String formattedBookings = bookings.toString()
//            .replace(",", "")  //remove the commas
//            .replace("[", "")  //remove the right bracket
//            .replace("]", "")  //remove the left bracket
//            .trim();



}

// CUSTOMER MODEL COPY
//@Entity(name = "customer")
//public class Customer {
//    @Id
//    @GeneratedValue
//    private Long id;
//    @NotNull
////    @Pattern(regexp = "[a-zA-Z]", message = "First name must contain only alphabetic characters")
//    private String firstName;
//    @NotNull
////    @Pattern(regexp = "[a-zA-Z]", message = "Last name must contain only alphabetic characters")
//    private String lastName;
//    @NotNull
//    @Pattern(regexp = "[[0-9]]+", message = "Phone number must contain digits only")
//    private String phoneNumber;
//    @NotNull
//    @Email
//    private String email;

//    @OneToOne(mappedBy = "customer")
//    @Getter(value=AccessLevel.NONE)
//    private User user;

//    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
//    @Setter(value = AccessLevel.NONE)
//    private List<Booking> bookings = new ArrayList<>();
//
//    public void addBooking(Booking booking) {
//        bookings.add(booking);
//    }
