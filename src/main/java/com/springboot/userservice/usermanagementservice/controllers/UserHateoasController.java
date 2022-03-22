package com.springboot.userservice.usermanagementservice.controllers;

import com.springboot.userservice.usermanagementservice.entities.User;
import com.springboot.userservice.usermanagementservice.exceptions.UserNotFoundException;
import com.springboot.userservice.usermanagementservice.repositories.UserRepository;
import com.springboot.userservice.usermanagementservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/hateoas/users")
@Validated
public class UserHateoasController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    // Get user by id
    @GetMapping("/{id}")
    public EntityModel<User> getUserById(@PathVariable("id") @Min(1) Long id){
        try {
            Optional<User> userOptional = userService.getUserById(id);
            User user = userOptional.get();
            Long user_id = user.getId();

            WebMvcLinkBuilder selfLink = linkTo(methodOn(this.getClass()).getUserById(user_id));
            WebMvcLinkBuilder lintToUsers = linkTo(methodOn(this.getClass()).getAllUser());
            WebMvcLinkBuilder lintToOrders = linkTo(methodOn(OrderController.class).getAllOrders(user_id));
//            Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(user_id).withSelfRel();
//            user.add(selfLink);
            EntityModel<User> entityModel = EntityModel.of(user);
            entityModel.add(selfLink.withRel("Self-Link"));
            entityModel.add(lintToUsers.withRel("all-users"));
            entityModel.add(lintToOrders.withRel("all-orders"));
            return entityModel;

        } catch (UserNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }

    }
    // Get all users
    @GetMapping
    public CollectionModel<User> getAllUser() throws UserNotFoundException {
        List<User> allUsers = userService.getAllUser();

        for (User user : allUsers) {
            //self link to each user
            WebMvcLinkBuilder selfLink = linkTo(methodOn(this.getClass()).getUserById(user.getId()));
            user.add(selfLink.withRel("self-link"));
            // relationship-links-with-getAllOrders
            WebMvcLinkBuilder orderLinks = linkTo(methodOn(OrderHateoasController.class).getAllOrders(user.getId()));
            user.add(orderLinks.withRel("order-links"));
            // self link to All Users

        }
        Link link = linkTo(methodOn(this.getClass()).getAllUser()).withSelfRel();
        CollectionModel<User> finalEntity = CollectionModel.of(allUsers, link);

        return finalEntity;
    }
}
