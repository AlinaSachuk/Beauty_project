package com.beauty_project.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {
    private final UserDetailServiceImpl customerUserDetailService;
    private final String ADMIN_ROLE = "ADMIN";
    private final String CUSTOMER_ROLE = "CUSTOMER";

    @Autowired
    public SecurityConfig(UserDetailServiceImpl userDetailService) {
        this.customerUserDetailService = userDetailService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.POST, "/customer/registration")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/procedure/**", "/product/**", "/status/**", "/employee/**")
                .permitAll()
                .antMatchers(HttpMethod.PUT, "/procedure", "/product", "/status", "/employee"
                        , "/visit", "/customer/updateStatus").hasRole(ADMIN_ROLE)
                .antMatchers(HttpMethod.PUT, "/customer/updateInfo").hasAnyRole(CUSTOMER_ROLE, ADMIN_ROLE)
                .antMatchers(HttpMethod.GET, "/visit/**", "/customer/getAll").hasRole(ADMIN_ROLE)
                .antMatchers(HttpMethod.GET, "/customer/{id}", "/customer/getVisits/{id}")
                .hasAnyRole(CUSTOMER_ROLE, ADMIN_ROLE)
                .antMatchers(HttpMethod.POST, "/**").hasRole(ADMIN_ROLE)
                .antMatchers(HttpMethod.DELETE, "/customer/{id}").hasRole(CUSTOMER_ROLE)
                .antMatchers(HttpMethod.DELETE, "/**").hasRole(ADMIN_ROLE)
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .userDetailsService(customerUserDetailService)
                .httpBasic()
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}