package com.example.Globitel.Globitel.controller;

import com.example.Globitel.Globitel.model.User;
import com.example.Globitel.Globitel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> getDAllUsers() {
        return userService.getAllUser();
    }

    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable("userId") Integer userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/user")
    public User signUp(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping("/user/{user_id}")
    public void updateUser(HttpServletResponse httpServletResponse, @PathVariable("user_id") Integer userId, @RequestBody User user) {
        userService.updateUser(user, userId);
    }

    @PutMapping("/user_role/{user_id}")
    public void updateUserAsAdmin(HttpServletResponse httpServletResponse, @PathVariable("user_id") Integer id,  @RequestParam("role") String role) {
        userService.updateUserAsAdmin(id, role);
    }

    @DeleteMapping("/user/{user_id}")
    public void deleteDocument(@PathVariable("user_id") Integer userId) {
        userService.deleteUser(userId);
    }


    @PostMapping("/login")
    public ResponseEntity logIn(@RequestParam("username") String username, @RequestParam("pass") String pass, HttpServletRequest request) {

        return userService.addSession(username, pass, request);
    }

    @PostMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request) {
        userService.removeSession(request);
        return ResponseEntity.ok().build();
    }

}
