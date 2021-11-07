package com.example.Globitel.Globitel.service;

import com.example.Globitel.Globitel.model.User;
import com.example.Globitel.Globitel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;


    public List<User> getAllUser() {
        return (ArrayList) userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    public User updateUser(User user, Integer userId) {
        user.setId(userId);
        return userRepository.save(user);
    }

    public User updateUserAsAdmin(Integer id, String role) {
      User user=userRepository.getById(id);
      user.setRole(role);
        return userRepository.save(user);
    }

    public ResponseEntity addSession(String username, String pass, HttpServletRequest request) {
        User user = userRepository.getByUsername(username);
        if (user == null || !pass.equals(user.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid username or password");
        }
        HttpSession session = request.getSession();
        session.setAttribute("ACTIVE_USER", user);

        return ResponseEntity.ok(user);
//        return ResponseEntity.ok("User successfully logged in");
    }

    public void removeSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();

    }
}