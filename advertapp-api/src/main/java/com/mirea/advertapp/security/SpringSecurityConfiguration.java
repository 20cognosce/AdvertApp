package com.mirea.advertapp.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;

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
                .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/adverts/count", "/users/count", "/images/**").permitAll()
                .requestMatchers("/", "/error").permitAll()

                .requestMatchers(HttpMethod.GET, "/adverts/**").hasAnyAuthority(ADMIN.name(), USER.name())
                .requestMatchers(HttpMethod.POST, "/adverts/**").hasAnyAuthority(ADMIN.name(), USER.name())

                .requestMatchers(HttpMethod.GET,"/users/**").hasAnyAuthority(ADMIN.name())
                .requestMatchers(HttpMethod.POST,"/users/**").permitAll() //TODO: FIXME: SHOULD BE FIXED IMMEDIATELY
                .requestMatchers(HttpMethod.DELETE,"/adverts/**").hasAnyAuthority(ADMIN.name())

                .anyRequest().authenticated()
                .and()
                .csrf(AbstractHttpConfigurer::disable) //TODO: should be fixed in the future
                .cors()
                .and()
                .httpBasic();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

    @Bean
    public CorsFilter corsFilter(@Value("${app.cors.allowed-origins}") String allowedOriginsLine) {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        //TODO: should be fixed in the future by adding a proxy-server like Nginx
        //var allowedOrigins = Arrays.asList(allowedOriginsLine.split(","));
        config.setAllowCredentials(true);
        config.setAllowedOriginPatterns(Collections.singletonList("*"));
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
