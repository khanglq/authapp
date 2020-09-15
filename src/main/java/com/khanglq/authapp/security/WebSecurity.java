package com.khanglq.authapp.security;

import com.khanglq.authapp.security.authorize.PathAccessDecisionManager;
import com.khanglq.authapp.security.authorize.PathAccessDeniedHandler;
import com.khanglq.authapp.security.authen.PathAuthenticationProvider;
import com.khanglq.authapp.security.authorize.PathFilterInvocationSecurityMetadataSource;
import com.khanglq.authapp.service.auth.LoggedUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * Created by khanglq on 31/8/2020.
 */
@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    PathAuthenticationProvider pathAuthenticationProvider;

    @Autowired
    private LoggedUserDetailService loggedUserDetailService;

    private final PathFilterInvocationSecurityMetadataSource pathFilterInvocationSecurityMetadataSource;
    private final PathAccessDecisionManager pathAccessDecisionManager;
    private final PathAccessDeniedHandler pathAccessDeniedHandler;

    public WebSecurity(PathFilterInvocationSecurityMetadataSource pathFilterInvocationSecurityMetadataSource, PathAccessDecisionManager pathAccessDecisionManager, PathAccessDeniedHandler pathAccessDeniedHandler) {
        this.pathFilterInvocationSecurityMetadataSource = pathFilterInvocationSecurityMetadataSource;
        this.pathAccessDecisionManager = pathAccessDecisionManager;
        this.pathAccessDeniedHandler = pathAccessDeniedHandler;
    }

//    @Bean
//    public static PasswordEncoder passwordEncoder() {
//        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//    }

    @SuppressWarnings("deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(pathAuthenticationProvider);
//        auth.userDetailsService(loggedUserDetailService).passwordEncoder(passwordEncoder());
    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(pathAuthenticationProvider);
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.antMatcher("/**").authorizeRequests();


        // Login processing-when the front and back ends are integrated
 registry.and().formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll()
 // Customize the login user name and password attribute name, the default is username and password
 .usernameParameter("username").passwordParameter("password")
 // Exception handling
 .failureUrl("/login/error").permitAll()
                // sign out
 .and().logout().permitAll();

        http.csrf().disable().cors();
        http.exceptionHandling().accessDeniedHandler(pathAccessDeniedHandler);

        registry.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                o.setSecurityMetadataSource(pathFilterInvocationSecurityMetadataSource);
                o.setAccessDecisionManager(pathAccessDecisionManager);
                return o;
            }
        });
        registry.antMatchers("/login", "/index").permitAll();

    }

    @Override
    public void configure(org.springframework.security.config.annotation.web.builders.WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.GET,
                "/favicon.ico",
                "/*.html",
                "/**/*.css",
                "/**/*.js");
    }

    }
