package com.example.spring;

import lombok.Data;

@Data
public class Address {

    private String streetName;
    private Integer streetNumber;
    private Integer postalCode;
    private String city;
    private String country;

    public Address(String streetName, Integer streetNumber, Integer postalCode, String city, String country) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }
}
