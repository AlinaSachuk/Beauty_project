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

    @Autowired
    public SecurityConfig(UserDetailServiceImpl userDetailService) {
        this.customerUserDetailService = userDetailService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf().disable()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/procedure/**").permitAll()
                .antMatchers(HttpMethod.GET, "/product/**").permitAll()
                .antMatchers(HttpMethod.GET, "/status/**").permitAll()
                .antMatchers(HttpMethod.GET, "/employee/**").permitAll()
                .antMatchers(HttpMethod.GET, "/customer/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/customer/getAllVisits/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/customer/getAll").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/visit/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/customer/registration", "/customer/updateInfo")
                .permitAll()
                .antMatchers(HttpMethod.PUT, "/customer/updateInfo").permitAll()
                .antMatchers(HttpMethod.POST, "/procedure", "/product", "/status", "/employee", "/visit")
                .hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/procedure", "/product", "/status", "/employee", "/visit")
                .hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .userDetailsService(customerUserDetailService)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}