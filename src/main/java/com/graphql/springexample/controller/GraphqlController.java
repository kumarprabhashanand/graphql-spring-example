package com.graphql.springexample.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;

import com.graphql.springexample.model.Address;
import com.graphql.springexample.model.User;
import com.graphql.springexample.model.UserInput;
import com.graphql.springexample.service.GraphqlService;

@Controller
public class GraphqlController {

    @Autowired
    private GraphqlService graphqlService;

    //@QueryMapping//("getGreetingMessage")
    //@SchemaMapping(typeName = "Query", field = "getGreetingMessage")
    public String getGreetingMessage(@Argument Optional<String> name) {
        String message = graphqlService.greet(name);
        return message + "\n" + graphqlService.initializeData();
    }

    @QueryMapping
    public Collection<User> getAllUsers() {
        return graphqlService.getAllUsers();
    }

    @QueryMapping
    public User getUserById(@Argument int userId) {
        return graphqlService.getUserById(userId);
    }

    //@SchemaMapping(typeName = "User", field = "address")
    public Address getAddressByUserId(User user) {
        System.out.println("=============Address============");
        return graphqlService.getAddressByUserId(user.userId());
    }

    @BatchMapping(field = "address")
    public Map<User, Address> getAddressByUserIds(List<User> userList) {
        System.out.println("=============Address============");
        Set<Integer> userIdList = userList.stream().map(a -> a.userId()).collect(Collectors.toSet());
        return graphqlService.getAddressByUserIdList(userIdList);
    }

    @MutationMapping
    public User addNewUser(@Argument UserInput user) {
        return graphqlService.addNewUser(user);
    }

    @SubscriptionMapping
    public Publisher<String> greetNewUsers() {
        return graphqlService.greetNewUser();
    }


}
