package com.example.BloggingProject.Config;

import com.example.BloggingProject.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class securityConfiguration {
    @Autowired
    CustomUserDetailsService detailService;

    @Bean
    public SecurityFilterChain SFC(HttpSecurity http) throws Exception {
        http.
                csrf().disable().
                authorizeHttpRequests(auth -> auth.
                        requestMatchers("/login", "/register", "/Adminlogin").
                        permitAll().
                        anyRequest().
                        authenticated()).
                formLogin(form -> form.
                        loginPage("/login").
                        loginProcessingUrl("/login").
                        defaultSuccessUrl("/", true).
                        permitAll()
                ).authenticationProvider(daoAuthenticationProvider());


        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(detailService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


}
