package com.khanglq.authapp.service.auth;

import com.khanglq.authapp.model.auth.LoggedUser;
import com.khanglq.authapp.model.entity.*;
import com.khanglq.authapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by khanglq on 31/8/2020.
 */

@Service
@Slf4j
public class LoggedUserDetailService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<User> userList = userService.getByUsername(username);
        User user;
        if (!CollectionUtils.isEmpty(userList)) {
            user = userList.get(0);
        } else {
            throw new UsernameNotFoundException("User notfound！");
        }
        return user != null ? new LoggedUser(user, getUserRoles(user)) : null;
//        return null;
    }

    private List<Role> getUserRoles(User user) {

        log.info("lqk: Begin get Roles of user: " + user.getUsername());
        //Get groups of user
        Set<GroupUser> groupUsers = user.getGroupUsers();
        if (!CollectionUtils.isEmpty(groupUsers)) {
            List<Role> roleList = new ArrayList<Role>();

            //For each group, get roles of group
            for(GroupUser groupUser : groupUsers) {

                Groups groups = groupUser.getGroups();
                if(groups != null) {
                    Set<GroupRole> groupRoles = groups.getGroupRoles();
                    if (!CollectionUtils.isEmpty(groupRoles)) {

                        //Add role to role list if not exist
                        for (GroupRole groupRole : groupRoles) {
                            Role role = groupRole.getRole();
                            if (role != null && !roleList.contains(role)) roleList.add(role);
                        }
                    }
                }
            }

            return roleList;
        } else {
            log.info("lqk said: User belong to no group, please add this user to any group " + user.getUsername());
            return null;
        }


    }
}
