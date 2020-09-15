package com.khanglq.authapp.service;

import com.khanglq.authapp.model.entity.Action;
import com.khanglq.authapp.repo.ActionRepo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by khanglq on 31/8/2020.
 */

@Service
public class ActionService {

    private final ActionRepo actionRepo;

    public ActionService(ActionRepo actionRepo) {
        this.actionRepo = actionRepo;
    }

    public List<Action> getAll() {
        List<Action> allAction = actionRepo.findAll();
        if(allAction != null) return allAction;
        else {
            System.out.println("No action found");
            return null;
        }
    }
}
