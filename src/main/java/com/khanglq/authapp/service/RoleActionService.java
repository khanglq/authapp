package com.khanglq.authapp.service;

import com.khanglq.authapp.model.entity.RoleAction;
import com.khanglq.authapp.repo.RoleActionRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by khanglq on 31/8/2020.
 */

@Service
@Slf4j
public class RoleActionService {

    private final RoleActionRepo roleActionRepo;

    public RoleActionService(RoleActionRepo roleActionRepo) {
        this.roleActionRepo = roleActionRepo;
    }

    public List<RoleAction> getAll() {
        List<RoleAction> allRoleActions = roleActionRepo.findAll();
        if(allRoleActions != null) return allRoleActions;
        else {
            log.info("No role action found");
            return null;
        }
    }

    public List<RoleAction> getListByActionId(int actionId) {
        List<RoleAction> result = roleActionRepo.findByActionId(actionId);
        if(result != null) return result;
        else {
            log.info("No RoleAction found");
            return null;
        }
    }
}
