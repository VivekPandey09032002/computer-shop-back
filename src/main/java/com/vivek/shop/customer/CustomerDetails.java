package com.vivek.shop.customer;

import com.vivek.shop.customer.enums.EGender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "customer_details")
@Getter
@Setter
@NoArgsConstructor
class CustomerDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private EGender gender;
    @NotBlank(message = "Please type an address!")
    private String addressLine1;
    private String addressLine2;
    @Size(min = 9, max = 12)
    private String phone;
    @NotBlank(message = "Please type name of city!")
    private String city;
    @NotBlank(message = "Please type name of country!")
    private String country;

    CustomerDetails(
            final String firstName,
            final String lastName,
            final EGender gender,
            final String addressLine1,
            final String addressLine2,
            final String phone,
            final String city,
            final String country
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.phone = phone;
        this.city = city;
        this.country = country;
    }


}
