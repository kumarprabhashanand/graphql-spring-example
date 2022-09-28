package com.graphql.springexample.model;

public record Address(
  int addressId,
  int userId,
  int houseNumber,
  String street,
  String city,
  String zipcode,
  String country
) {
    public Address() {
        this(0,0,0,"","","","");
    }
}
