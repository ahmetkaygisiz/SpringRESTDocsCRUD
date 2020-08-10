package com.restdocs.controller;

import com.restdocs.domain.User;
import com.restdocs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }

    /*
    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable("username") String username){
        return userService.getUserByUsername(username);
    }
    */

    @DeleteMapping("/{username}")
    public void deleteUserByUsername(@PathVariable("username") String username){
        userService.deleteUserByUsername(username);
    }

    @PostMapping
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @PutMapping("/{id}")
    public void updateEmployee(@RequestBody User user, @PathVariable Long id) {
        User dbUser = userService.getUserById(id);

        dbUser.setFirstName(user.getFirstName());
        dbUser.setLastName(user.getLastName());
        dbUser.setEmail(user.getEmail());
        dbUser.setPhoneNumber(user.getPhoneNumber());
        dbUser.setUsername(user.getUsername());

        userService.addUser(dbUser);
    }
}


