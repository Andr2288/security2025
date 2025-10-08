package edu.pzks.security2025.config;

/*
    @author    chorn
    @project   security2025
    @class     SecurityConfig
    @version   1.0.0
    @since     08.10.2025 - 18:06
*/

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(req ->
                        req
                                // PUBLIC
                                .requestMatchers("/index.html").permitAll()

                                // ADMIN ONLY
                                .requestMatchers("/api/v1/flashcards/hello/admin").hasRole("ADMIN")

                                // SUPERADMIN ONLY
                                .requestMatchers("/api/v1/flashcards/hello/superadmin").hasRole("SUPERADMIN")

                                // USER, ADMIN, SUPERADMIN
                                .requestMatchers("/api/v1/flashcards/hello/user").hasAnyRole("USER", "ADMIN", "SUPERADMIN")

                                // DELETE
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/flashcards/**").hasAnyRole("ADMIN", "SUPERADMIN")

                                // POST (CREATE)
                                .requestMatchers(HttpMethod.POST, "/api/v1/flashcards").hasAnyRole("ADMIN", "SUPERADMIN")

                                // PUT (UPDATE)
                                .requestMatchers(HttpMethod.PUT, "/api/v1/flashcards").hasAnyRole("ADMIN", "SUPERADMIN")

                                // GET
                                .requestMatchers(HttpMethod.GET, "/api/v1/flashcards/**").authenticated()

                                // Всі інші запити потребують авторизації
                                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER")
                .build();

        UserDetails superadmin = User.builder()
                .username("superadmin")
                .password(passwordEncoder().encode("superadmin"))
                .roles("SUPERADMIN")
                .build();


        return new InMemoryUserDetailsManager(admin, user, superadmin);
    }
}
