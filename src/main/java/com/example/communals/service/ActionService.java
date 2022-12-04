package com.example.communals.service;

import com.example.communals.entity.Action;
import com.example.communals.repo.ActionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActionService {
    private final ActionRepo actionRepo;

    public Action save(Action action){
        return actionRepo.save(action);
    }
    public void deleteById(Long id){
        actionRepo.deleteById(id);
    };
    public Action getById(Long id){
        return actionRepo.findById(id).get();
    }
    public Iterable<Action> getAll(){
        return actionRepo.findAll();
    }
}
