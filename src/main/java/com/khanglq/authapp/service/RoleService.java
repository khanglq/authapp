package com.khanglq.authapp.service;

import com.khanglq.authapp.model.entity.Role;
import com.khanglq.authapp.repo.RoleRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by khanglq on 31/8/2020.
 */

@Service
@Slf4j
public class RoleService {

    private final RoleRepo roleRepo;

    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    public List<Role> getAll() {
        List<Role> result = roleRepo.findAll();
        if(result != null) return result;
        else {
            System.out.println("No role found");
            return null;
        }
    }

    public Role getById(Integer roleId) {
        log.info("roleId = " + roleId);
        Optional<Role> object = roleRepo.findById(roleId);
        object.orElseThrow(()-> new RuntimeException("Object not found with " + Role.class.getSimpleName()));
        return object.get();
    }
}
