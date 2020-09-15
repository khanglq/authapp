package com.khanglq.authapp.security.authen;

import com.khanglq.authapp.model.auth.LoggedUser;
import com.khanglq.authapp.service.auth.LoggedUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * Created by khanglq on 31/8/2020.
 */
@Component
public class PathAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    LoggedUserDetailService loggedUserDetailService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials().toString();

        LoggedUser loggedUser = (LoggedUser) loggedUserDetailService.loadUserByUsername(userName);

        boolean isValid = password.equalsIgnoreCase(loggedUser.getPassword()); // need to change encript password here
        if (!isValid) {
            throw new BadCredentialsException("wrong username or password！");
        }
        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(loggedUser, password, loggedUser.getAuthorities());
        result.setDetails(authentication.getDetails());
        return result;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
