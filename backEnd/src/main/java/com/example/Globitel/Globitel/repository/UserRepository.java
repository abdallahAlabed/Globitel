package com.example.Globitel.Globitel.repository;

import com.example.Globitel.Globitel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User getByUsername(String userName);
}