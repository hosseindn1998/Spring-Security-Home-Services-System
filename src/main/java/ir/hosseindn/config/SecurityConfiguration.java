package ir.hosseindn.config;

import ir.hosseindn.exception.NotFoundException;
import ir.hosseindn.repository.admin.AdminRepository;
import ir.hosseindn.repository.customer.CustomerRepository;
import ir.hosseindn.repository.technician.TechnicianRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfiguration {


    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;
    private final TechnicianRepository technicianRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        s -> s.anyRequest().permitAll())
                .httpBasic(Customizer.withDefaults())
        ;
        return http.build();
    }

    @Autowired
    public void configureBuild(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> {

            try {
                return customerRepository.findByEmail(username)
                        .orElseThrow(() -> new NotFoundException("Customer not found"));
            } catch (NotFoundException ignored) {

            }
            try {
                return technicianRepository.findByEmail(username)
                        .orElseThrow(() -> new NotFoundException("Technician not found"));
            } catch (NotFoundException ignored) {

            }
            return adminRepository.findByEmail(username)
                    .orElseThrow(() -> new NotFoundException("User not found"));
                })
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
