package com.khanglq.authapp.repo;

import com.khanglq.authapp.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by khanglq on 31/8/2020.
 */

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {

//    List<Action> findAll();
}
