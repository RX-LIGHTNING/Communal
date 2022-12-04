package com.example.communals.service;

import com.example.communals.entity.User;
import com.example.communals.entity.enums.Role;
import com.example.communals.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public User registration(User user) {
        if (userRepo.findByUsername(user.getUsername()).isPresent()) {
            return null;
        }
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        user.setRoles(Collections.singleton(Role.ADMIN));
        return userRepo.save(user);
    }
    public User findByUsername(String username){
        return userRepo.findByUsername(username).get();
    }
    public Iterable<User> getAll(){
        return userRepo.findAll();
    }

    public User getById(Long id){
        return userRepo.findById(id).get();
    }

    public void delete(Long id){
        userRepo.deleteById(id);
    }
    public User edit(User user, Role[] roles){
        Set<Role> targetSet = new HashSet<Role>();
        Collections.addAll(targetSet, roles);
        user.setRoles(targetSet);
        return userRepo.save(user);
    }
}
