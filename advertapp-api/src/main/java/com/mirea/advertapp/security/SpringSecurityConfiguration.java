package com.mirea.advertapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static com.mirea.advertapp.domain.entityenum.Role.ADMIN;
import static com.mirea.advertapp.domain.entityenum.Role.USER;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(16);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/api/books", "/api/books/**").hasAnyAuthority(ADMIN.name(), USER.name())
                .requestMatchers(HttpMethod.GET, "/api/users/me").hasAnyAuthority(ADMIN.name(), USER.name())
                .requestMatchers("/api/books", "/api/books/**").hasAuthority(ADMIN.name())
                .requestMatchers("/api/users", "/api/users/**").hasAuthority(ADMIN.name())
                .requestMatchers("/public/**", "/auth/**").permitAll()
                .requestMatchers("/", "/error", "/csrf", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs", "/v3/api-docs/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //http.cors().and().csrf().disable();
        return http.build();
    }
}
