package com.example.communals.repo;

import com.example.communals.entity.Action;
import org.springframework.data.repository.CrudRepository;

public interface ActionRepo extends CrudRepository<Action,Long> {
    Iterable<Action> findByNameIsContainingIgnoreCase(String filter);
}
