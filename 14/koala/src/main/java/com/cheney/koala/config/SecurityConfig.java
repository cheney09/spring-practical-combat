package com.cheney.koala.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        List<UserDetails> users = new ArrayList<>();
        users.add(new User("test", encoder.encode("test"), List.of(new SimpleGrantedAuthority("ROLE_TEST"))));
        users.add(new User("koala", encoder.encode("koala"), List.of(new SimpleGrantedAuthority("ROLE_MANAGER"))));
        users.add(new User("admin", encoder.encode("admin"), List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))));
        return new InMemoryUserDetailsManager(users);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                // 定义了针对不同请求路径的访问规则，这是一种典型的 Spring Security 请求授权的配置方式。
                // 对于 "/info/" 下的请求允许所有人访问，
                // 对于 "/system/" 下的请求要求用户具有 "ADMIN" 角色，
                // 而对于其他请求则要求用户进行身份验证。
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/**").permitAll()
                        .requestMatchers("/register/**").permitAll()
                        .requestMatchers("/system/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                // 使用默认设置的 HTTP Basic 认证
                .httpBasic(withDefaults())
                // 配置 Spring Security 为了处理登录表单。用户访问受保护的资源时，如果未经身份验证，系统将会重定向到 "/login" 页面。
                // 用户在登录成功后将会被重定向到 "/index" 页面。
                // 最后的 .permitAll() 确保未经身份验证的用户可以访问登录页面。
                .formLogin(customizer -> customizer
                        .loginPage("/login")
                        .defaultSuccessUrl("/index", true)
                        .permitAll()
                )
                // 禁用 CSRF 保护，确保应用程序不会受到 CSRF 攻击的影响。
                .csrf(AbstractHttpConfigurer::disable)
                // 配置 Spring Security 为了处理用户注销操作。用户访问 "/logout" URL 时，系统将执行注销操作，
                // 并且成功注销后会将用户重定向到 "/login?logout" 页面。
                // 最后的 .permitAll() 确保任何用户都可以访问注销的URL。
                .logout(customizer -> customizer
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .build();
    }
}
