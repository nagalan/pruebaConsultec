// Configuracion de seguridad
package com.consultec.gestioncliente.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${spring.mvc.servlet.path}")
    private String springMvcServletPath;

    @Value("${spring.profiles.active}")
    private String springProfileActive;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
            .withUser("user").password("{noop}password").roles("USER");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String path;
        if (springProfileActive.compareTo("test") == 0) {
            path = "";
        } else {
            path = springMvcServletPath;
        }

        http.httpBasic().and().authorizeRequests()
                .antMatchers(HttpMethod.GET, path.concat("/offices/**")).hasRole("USER")
                .antMatchers(HttpMethod.POST, path.concat("/offices/**")).hasRole("USER")
                .antMatchers(HttpMethod.PUT, path.concat("/offices/**")).hasRole("USER")
                .antMatchers(HttpMethod.DELETE, path.concat("/offices/**")).hasRole("USER")
                .antMatchers(HttpMethod.GET, path.concat("/customers/**")).hasRole("USER")
                .antMatchers(HttpMethod.POST, path.concat("/customers/**")).hasRole("USER")
                .antMatchers(HttpMethod.PUT, path.concat("/customers/**")).hasRole("USER")
                .antMatchers(HttpMethod.DELETE, path.concat("/customers/**")).hasRole("USER").and().csrf().disable()
                .formLogin().disable();
    }

}