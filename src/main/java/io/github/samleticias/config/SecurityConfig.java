package io.github.samleticias.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// adicionar dependencias de security no pom.xml
// @Configuration
public class SecurityConfig {
    /*
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

     */
}
