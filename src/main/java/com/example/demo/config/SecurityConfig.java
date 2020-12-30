package com.example.demo.config;

import com.example.demo.service.UserDetailServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserDetailServiceImpl userDetailService;

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // Note:
//        // Use this to enable the tomcat basic authentication (tomcat popup rather than spring login page)
//        // Note that the CSRf token is disabled for all requests (change it as you wish...)
//        http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();
//    }

}
