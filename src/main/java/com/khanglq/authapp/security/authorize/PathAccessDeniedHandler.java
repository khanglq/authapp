package com.khanglq.authapp.security.authorize;

import com.khanglq.authapp.utils.ApiResult;
import com.khanglq.authapp.utils.ResponseUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by khanglq on 31/8/2020.
 */
@Component
public class PathAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ResponseUtils.out(httpServletResponse, ApiResult.fail(403, e.getMessage()));
    }
}
