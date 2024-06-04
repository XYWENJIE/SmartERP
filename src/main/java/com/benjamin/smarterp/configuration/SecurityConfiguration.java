package com.benjamin.smarterp.configuration;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.List;

@Configurable
public class SecurityConfiguration {

    @Bean
    public UserDetailsService userDetailsService(){
        return new InMemoryUserDetailsManager(new User("user","huang1100", List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))));
    }
}
