package com.mundim.ticketbackendspringboot.config;

import com.mundim.ticketbackendspringboot.security.jwt.JwtAuthenticationEntryPoint;
import com.mundim.ticketbackendspringboot.security.jwt.JwtAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@Configuration
public class SecurityConfig {
    private static final String[] DOCUMENTATION_OPENAPI = {
            "/docs/index.html",
            "/docs-park.html", "/docs-park/**",
            "/v3/api-docs/**",
            "/swagger-ui-custom.html", "/swagger-ui.html", "/swagger-ui/**",
            "/**.html", "/webjars/**", "/configuration/**", "/swagger-resources/**"
    };
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        authorizeHttpRequests -> authorizeHttpRequests
                                .requestMatchers(HttpMethod.POST,"/api/v1/auth").permitAll()
                                .requestMatchers(DOCUMENTATION_OPENAPI).permitAll()
                                .requestMatchers(HttpMethod.POST,"/api/v1/account").permitAll()
                                .requestMatchers(HttpMethod.POST,"/api/v1/account/admin").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/v1/event/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/api/v1/event/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PATCH,"/api/v1/event/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"/api/v1/event/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET,"/api/v1/category/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/api/v1/category/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PATCH,"/api/v1/category/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"/api/v1/category/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                ).addFilterBefore(
                        jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class
                ).exceptionHandling(ex -> ex
                        .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                )
                .build();
    }
    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
