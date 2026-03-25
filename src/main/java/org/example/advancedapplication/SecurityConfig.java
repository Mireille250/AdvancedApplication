package org.example.advancedapplication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity // Enables method-level annotations like @PreAuthorize
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {

        // Admin user with both ADMIN and USER roles
        UserDetails admin = User.withUsername("Amiya")
                .password(encoder.encode("123"))   // password: 123
                .roles("ADMIN", "USER")           // Roles assigned
                .build();

        // Regular user with USER role only
        UserDetails user = User.withUsername("Ejaz")
                .password(encoder.encode("123"))   // password: 123
                .roles("USER")                     // Roles assigned
                .build();

        // InMemoryUserDetailsManager stores the users
        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF for simplicity (REST API scenario)
                .csrf(csrf -> csrf.disable())

                // Authorization rules
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/welcome").permitAll()
                        .requestMatchers("/auth/user/**").hasRole("USER")
                        .requestMatchers("/auth/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )

                        // Form login configuration
                        .formLogin(form -> form
                                .defaultSuccessUrl("/auth/welcome", true) // Redirect after login
                        )

                        // Enable HTTP Basic authentication as well
                        .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}