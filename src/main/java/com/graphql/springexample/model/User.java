package com.graphql.springexample.model;

public record User(
  int userId,
  String firstName,
  String lastName
){
    public User() {
        this(0,"","");
    }
}
