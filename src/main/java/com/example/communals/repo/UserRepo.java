package com.example.communals.repo;

import com.example.communals.entity.User;
import com.example.communals.entity.enums.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends CrudRepository<User,Long> {
    Optional<User> findByUsername(String name);

    List<User> findAllByRoles(Role role);
}
