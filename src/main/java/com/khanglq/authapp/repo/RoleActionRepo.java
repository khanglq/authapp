package com.khanglq.authapp.repo;

import com.khanglq.authapp.model.entity.RoleAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by khanglq on 31/8/2020.
 */

@Repository
public interface RoleActionRepo extends JpaRepository<RoleAction, Integer> {

    List<RoleAction> findByActionId(int actionId);
}
