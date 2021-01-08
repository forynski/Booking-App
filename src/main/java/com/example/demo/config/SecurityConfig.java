package com.example.demo.config;

import com.example.demo.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserDetailsServiceImpl userDetailService;

    public SecurityConfig(UserDetailsServiceImpl userDetailService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailService = userDetailService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/user/{id}")
                .hasRole("ADMIN")
                .antMatchers("/user/**", "/booking/**", "/customer/**")
                .hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/booking/**", "/customer/**")
                .hasAnyRole("ADMIN", "MANAGER", "USER")
                .antMatchers("/", "/**", "/login", "/register").permitAll().anyRequest().authenticated();
        http.formLogin()
                .loginPage("/login")
                .permitAll();
        http.httpBasic();

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // Note:
//        // Use this to enable the tomcat basic authentication (tomcat popup rather than spring login page)
//        // Note that the CSRf token is disabled for all requests (change it as you wish...)
//        http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();
//    }

    }
}
