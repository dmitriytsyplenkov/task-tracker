package com.dts.tasktracker.controller;

import com.dts.tasktracker.model.User;
import com.dts.tasktracker.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("")
    public void newUser(@RequestBody User user, UriComponentsBuilder uriComponentsBuilder) {
        userService.saveUser(user);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User userInfo(@PathVariable String id) {
        return userService.findById(Long.valueOf(id));
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<User> userList() {
        return userService.getUsers();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String id) {
        userService.delete(userService.findById(Long.valueOf(id)));
    }
}
