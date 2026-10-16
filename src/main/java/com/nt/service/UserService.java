package com.nt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.User;
import com.nt.repo.IUserRepo;

@Service
public class UserService implements IUserService{
@Autowired
private IUserRepo urepo;

// Register user
public User registerUser(User user) {
    if (urepo.existsByEmail(user.getEmail())) {
        throw new RuntimeException("Email already registered!");
    }

    return urepo.save(user);
}

// Login: find user by email
public User loginUser(String email, String password) {
    User user = urepo.findByEmail(email)
            .orElseThrow(() ->
                new RuntimeException("User not found!"));

    if (!user.getPassword().equals(password)) {
        throw new RuntimeException("Invalid password!");
    }

    return user;
}

// Find user by ID
public User getUserById(Long id) {
    return urepo.findById(id)
            .orElseThrow(() ->
                new RuntimeException("User not found!"));
}

// View all users (admin demo)
public List<User> getAllUsers() {
    return urepo.findAll();
}

// Delete user
public void deleteUser(Long id) {
    User user = getUserById(id);
    urepo.delete(user);
}

}


