package com.levserj.config;

/*import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;*/

/**
 * Created by Serhii Levchynskyi on 11.05.2016.
 */
/*@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)*/
public class SpringSecurityConfig /*extends WebSecurityConfigurerAdapter*/ {

 /*   @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
*//*                .exceptionHandling()
                .accessDeniedPage("/web/accessdenied")
                .and()*//*
                .authorizeRequests()
*//*                .antMatchers("main").permitAll()*//*
                .anyRequest().authenticated()
                *//*.antMatchers("/web/admin/interviewers*//**//**").hasAuthority("ADMIN")*//*
                .and()
                .formLogin()
*//*                .loginPage("/web/login")
                .defaultSuccessUrl("/web/groups")
                .permitAll()
                .failureUrl("/web/loginerror")*//*
                .and()
                .logout()
                .permitAll();
*//*                .and()
                .csrf().disable();*//*
    }*/
}
