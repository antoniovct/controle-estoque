package br.com.antonio_victor.controle_estoque.security;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((authorize -> {
                    authorize.requestMatchers(HttpMethod.POST, "/login", "/cadastro").permitAll();
                    authorize.requestMatchers(HttpMethod.POST,"/produto", "/produto/entrada").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.PUT, "/produto/{codigo}").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.DELETE, "/produto/{codigo}").hasRole("ADMIN");
                    authorize.requestMatchers("/pedido-de-venda/**").hasRole("ADMIN");
                    authorize.requestMatchers("/relatorio-estoque").hasRole("ADMIN");
                    authorize.requestMatchers("/transacao").hasRole("ADMIN");

                    authorize.anyRequest().authenticated();
                }))
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
