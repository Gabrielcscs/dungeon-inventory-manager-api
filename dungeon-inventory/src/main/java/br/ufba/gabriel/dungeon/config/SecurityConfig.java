package br.ufba.gabriel.dungeon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        http
                // 1. O Segurança da Balada (CORS)
                .cors(cors -> cors.configurationSource(_ -> {
                    var config = new CorsConfiguration();
                    // Aqui mantemos o localhost para você testar no VS Code.
                    // No futuro, adicionaremos a URL do seu Front-end hospedado aqui!
                    config.setAllowedOrigins(List.of("http://127.0.0.1:5500", "http://localhost:5500"));
                    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    config.setAllowedHeaders(List.of("*"));
                    return config;
                }))
                // 2. Desativa proteção contra formulários antigos (não usamos isso em APIs modernas)
                .csrf(csrf -> csrf.disable())

                // 3. Regras de Acesso
                .authorizeHttpRequests(auth -> auth
                        // Libera totalmente a sua API de itens para o Front-end conseguir ler e gravar
                        .requestMatchers("/api/itens/**").permitAll()
                        // Qualquer outra tentativa de acesso desconhecida será bloqueada
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}