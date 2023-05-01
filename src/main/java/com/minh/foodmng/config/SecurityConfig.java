package com.minh.foodmng.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity()
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    final private JwtAuthFilter jwtAuthFilter;

    final private AuthenticationEntryPoint authEntryPoint;

    @Bean
    SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().requestMatchers("/user/auth/**").permitAll().anyRequest().hasAnyAuthority("admin");
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)
                .and()

//                .addFilterBefore(exceptionHandlerFilter, LogoutFilter.class)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
