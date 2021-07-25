package com.configs;

import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


//
@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {
    static final String level_1 = "hasRole('ADMIN')";
    static final String level_2 = "hasRole('ADMIN') or hasRole('MANAGER')";
    static final String level_3 = "hasRole('ADMIN') or hasRole('MANAGER') or hasRole('PM')";
    static final String level_4 = "hasRole('ADMIN') or hasRole('MANAGER') or hasRole('PM') or hasRole('MEMBER')";

    @Autowired
    UsersService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Password encoder, để Spring Security sử dụng mã hóa mật khẩu người dùng
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new
                UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userService) // Cung cáp userservice cho spring security
                .passwordEncoder(passwordEncoder()); // cung cấp password encoder
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("a").password("{noop}password").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable()
//        http.httpBasic().and()
                .authorizeRequests()
                .antMatchers( "/", "/home", "/forgot-password")
                    .permitAll()
                .antMatchers("/user/**", "/project/**", "/report/**")
                    .access(level_3)
                .antMatchers("/request/**", "/issue/**","/auth")
                    .access(level_1)
                .anyRequest().fullyAuthenticated()
                .and()
                    .logout() // (6)
                    .permitAll();



        // yêu cầu vai trò ADMIN, sẽ chuyển hướng tới trang /403
//        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
    }
}



