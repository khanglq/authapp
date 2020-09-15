package com.khanglq.authapp.repo;

import com.khanglq.authapp.model.entity.Role;
import com.khanglq.authapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by khanglq on 31/8/2020.
 */

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    List<User> findByUsername(String username);
}
