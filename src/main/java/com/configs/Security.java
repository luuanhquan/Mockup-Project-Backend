package com.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//
@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {
    String level_1= "hasRole('ADMIN')";
    String level_2= "hasRole('ADMIN') or hasRole('MANAGER')";
    String level_3= "hasRole('ADMIN') or hasRole('MANAGER') or hasRole('PM')";
    String level_4= "hasRole('ADMIN') or hasRole('MANAGER') or hasRole('PM') or hasRole('MEMBER')";

    @Autowired
    UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Password encoder, để Spring Security sử dụng mã hóa mật khẩu người dùng
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userService) // Cung cáp userservice cho spring security
                .passwordEncoder(passwordEncoder()); // cung cấp password encoder
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Tất cả các request khác đều cần phải xác thực mới được truy cập
//         Cho phép từ MEMBER được truy cập request và issue
        http.httpBasic();
        http.cors();
        http.authorizeRequests().antMatchers("/**").permitAll().antMatchers("/*")
                .fullyAuthenticated().and().formLogin()
                .and().csrf().disable();
        http.csrf().disable();
//        http.authorizeRequests()
//                .antMatchers("/request/**","/issue/**")
//                .access(level_1);
//
//        // Cho phép từ PM được truy cập employee, project, report
//        http.authorizeRequests()
//                .antMatchers("/user/**","/project/**","/report/**")
//                .access(level_3);
//        //Cấu hình ban đầu
//        http.authorizeRequests()
//                .antMatchers("/", "/home", "/forgot-password").permitAll(); // Cho phép tất cả mọi người truy cập vào 2 địa chỉ này
//                ;
//
//        //Cấu hình login
//        http.authorizeRequests().and().formLogin() // Cho phép người dùng xác thực bằng form login
//                .loginProcessingUrl("/j_spring_security_login")//
//                .loginPage("/login")
//                .defaultSuccessUrl("/home")
//                .failureUrl("/login?message=error")//
//                .permitAll() // Tất cả đều được truy cập vào địa chỉ này
//                .and()
//                .logout() // Cho phép logout
//                .permitAll();


        // yêu cầu vai trò ADMIN, sẽ chuyển hướng tới trang /403
//        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
    }
}



