package com.asmsi.admin_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import com.asmsi.admin_system.service.UserService;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder; // ✅ Injected from AppConfig

    public SecurityConfig(UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        
       
            .authorizeHttpRequests(auth -> auth
            
            .requestMatchers(
                "/login", "/signup", "/request-account", "/forget-password", "/forgot-password", "/reset-password",
                "/css/**", "/js/**", "/images/**", "/upload-csv", "/home", "/api/attendance/attendance","/api/attendance/**"
            ).permitAll()
                .anyRequest().authenticated()
            )  
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl( "/home", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder); // ✅ Uses passwordEncoder from AppConfig
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    

    
}
