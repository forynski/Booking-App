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
    private final UserDetailsServiceImpl userDetailsService;


    public SecurityConfig(UserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/users/{id}")
                .hasRole("ADMIN")
                .antMatchers("/users/**", "/booking/**", "/customer/**")
                .hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/user/**", "/booking/**", "/customer/**")
                .hasAnyRole("ADMIN", "MANAGER", "USER")
                .antMatchers("/", "/**", "/login", "/register").permitAll().anyRequest().authenticated();
        http.formLogin()
                .loginPage("/login")
//                .defaultSuccessUrl("/home")
//                .failureUrl("/login?error=true")
                .permitAll();
        http.httpBasic();

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // Note:
//        // Use this to enable the tomcat basic authentication (tomcat popup rather than spring login page)
//        // Note that the CSRf token is disabled for all requests (change it as you wish...)
//        http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();
//    }

//        protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//            auth.inMemoryAuthentication()
//                    .withUser("user1").password(bCryptPasswordEncoder.encode("user1Pass")).roles("USER")
//                    .and()
//                    .withUser("user2").password(bCryptPasswordEncoder.encode("user2Pass")).roles("USER")
//                    .and()
//                    .withUser("admin").password(bCryptPasswordEncoder.encode("adminPass")).roles("ADMIN");
//        }

    }
}
