package com.example.demo.config;

import com.example.demo.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        // Pages don't need authentication
        http.authorizeRequests().antMatchers("/", "/login", "/logout","/index","/*").permitAll();

        // Pages require login with role ROLE_USER or ROLE_ADMIN
        http.authorizeRequests().antMatchers("/userInfo","/home").access("hasAnyRole('ROLE_USER','ROLE_ADMIN')");

        // Pages require login with role ROLE_ADMIN
        http.authorizeRequests().antMatchers("/adminInfo","/addPhone").access("hasRole('ROLE_ADMIN')");

        // Sign in but not have permission
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

        // Configure for login form
        http.authorizeRequests().and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");

//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
//        http.sessionManagement().maximumSessions(2).expiredUrl("/sessionExpired");
//        http.sessionManagement().invalidSessionUrl("/invalidSession");
    }

//    @Bean
//    public HttpSessionEventPublisher httpSessionEventPublisher(){
//        return new HttpSessionEventPublisher();
//    }
}
