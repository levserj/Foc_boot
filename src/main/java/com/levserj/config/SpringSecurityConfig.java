package com.levserj.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Serhii Levchynskyi on 11.05.2016.
 */
@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDeatilsServiceImpl customUserDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling()
                .and()
                .authorizeRequests()

                .antMatchers(/*HttpMethod.POST,*/ "/rest/**").permitAll()
                .antMatchers("/", "/foc", "/signUp", "/resources/**").permitAll()
                .anyRequest().authenticated()
     /*           .antMatchers("myPage").authenticated()*/
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();

/*                .antMatchers("/web/admin/interviewers*").hasAuthority("ADMIN")
                .and()
                .formLogin()
                .loginPage("/web/login")
                .defaultSuccessUrl("/web/groups")
                .permitAll()
                .failureUrl("/web/loginerror")
                .and()
                .logout()
                .permitAll();*/

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
