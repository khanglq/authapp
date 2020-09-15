package com.khanglq.authapp.repo;

import com.khanglq.authapp.model.entity.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by khanglq on 31/8/2020.
 */

@Repository
public interface ActionRepo extends JpaRepository<Action, Integer> {

//    List<Action> findAll();
}
