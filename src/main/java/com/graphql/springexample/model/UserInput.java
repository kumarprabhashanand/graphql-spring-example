package com.graphql.springexample.model;

public record UserInput(
  String firstName,
  String lastName,
  int houseNumber,
  String street,
  String city,
  String zipcode,
  String country
) {
}
