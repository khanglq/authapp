package com.khanglq.authapp.security.authorize;

import com.khanglq.authapp.model.entity.Action;
import com.khanglq.authapp.model.entity.Role;
import com.khanglq.authapp.model.entity.RoleAction;
import com.khanglq.authapp.service.ActionService;
import com.khanglq.authapp.service.RoleActionService;
import com.khanglq.authapp.service.RoleService;
import com.khanglq.authapp.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by khanglq on 31/8/2020.
 */
@Component
@Slf4j
public class PathFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    ActionService actionService;

    @Autowired
    RoleActionService roleActionService;

    @Autowired
    RoleService roleService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // Get the current request url
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        log.debug("Request Url = " + requestUrl);
        // TODO ignore url, please put it here for filtering and release
        if ("/login".equals(requestUrl) || requestUrl.contains("logout")) {
            return null;
        }

        // All URLs in the database
        List<Action> actionList = actionService.getAll();
        for (Action action : actionList) {

            log.debug("checking in loop of action : " + action);

            // Get the permission corresponding to the url
            if (requestUrl.equals(action.getPath())) {
                List<RoleAction> roleActionList = roleActionService.getListByActionId(action.getId());

                log.debug("roleActionList : " + roleActionList);

                List<String> roles = new LinkedList<>();
                if (!CollectionUtils.isEmpty(roleActionList)){
//                    Integer roleId = roleActionList.get(0).getId();
//                    log.info("roleId = " + roleId);
//                    Role role = roleService.getById(roleId);

                    Role role = roleActionList.get(0).getRole();
                    log.info("Rolename = " + role.getRoleName());
                    roles.add(role.getRoleName());
                }
                // Save the role permission information corresponding to the url
                return SecurityConfig.createList(roles.toArray(new String[roles.size()]));
            }
        }
        // If the corresponding url resource is not found in the data, it is illegal access, and the user is required to log in and perform the operation
        return SecurityConfig.createList(Constants.ROLE_LOGIN);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
