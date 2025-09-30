package com.frknozbek.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.frknozbek.handler.AuthEntryPoint;
import com.frknozbek.jwt.JWTAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    public static final String REGISTER = "/register/**";
    public static final String AUTHENTICATE = "/authenticate/**";
    public static final String REFRESH_TOKEN = "/refreshToken/**";

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private AuthEntryPoint authEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        String[] allowedPaths = { "/", "/register", "/authenticate", "/refreshToken" };

        http
                .csrf(csrf -> csrf.disable())
                .httpBasic((basic) -> basic.disable())
                .formLogin(formLogin -> formLogin.disable())
                .authorizeHttpRequests(
                        request -> request.requestMatchers(allowedPaths).permitAll()
                                .anyRequest()
                                .authenticated())
                .exceptionHandling(handling -> handling.authenticationEntryPoint(authEntryPoint))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
