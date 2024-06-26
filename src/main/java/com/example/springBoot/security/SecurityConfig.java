package com.example.springBoot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(
                        authorize -> {
                            authorize.requestMatchers("/api/users", "/api/users/delete/{id}").permitAll()
                                    .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasAuthority("ADMIN")
                                    .requestMatchers("/api/companies/**").hasAnyAuthority("ADMIN")
                                    .requestMatchers("/api/courses/**").hasAnyAuthority("ADMIN", "INSTRUCTOR")
                                    .requestMatchers("/api/groups/**").hasAnyAuthority("ADMIN")
                                    .anyRequest().authenticated();
                        })
                .httpBasic(Customizer.withDefaults())
                .authenticationProvider(daoAuthenticationProvider())
                .build();
    }
}
