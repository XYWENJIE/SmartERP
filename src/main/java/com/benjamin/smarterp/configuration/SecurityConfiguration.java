package com.benjamin.smarterp.configuration;

import com.benjamin.smarterp.domain.entity.Personnel;
import com.benjamin.smarterp.domain.entity.UserLogin;
import com.benjamin.smarterp.repository.jpa.*;
import com.benjamin.smarterp.security.JpaUserDetailsManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import java.util.Optional;

@Slf4j
@EnableWebFluxSecurity
@Configuration
public class SecurityConfiguration {


    @Bean
    public UserDetailsManager userDetailsManager(UserLoginRepository userLoginRepository,
                                                 AuthoritiesRepository authoritiesRepository,
                                                 PasswordEncoder passwordEncoder, PersonnelRepository personnelRepository,
                                                 UserLoginHistoryRepository userLoginHistoryRepository,
                                                 UserLoginPasswordHistoryRepository userLoginPasswordHistoryRepository){
        JpaUserDetailsManager userDetailsManager = new JpaUserDetailsManager(
                userLoginRepository,authoritiesRepository,passwordEncoder,userLoginHistoryRepository,userLoginPasswordHistoryRepository);
        if(!userDetailsManager.userExists("admin")){
            userDetailsManager.createUser(User.withUsername("admin").password("huang1100").roles("ADMIN").build());
            Personnel personnel = personnelRepository.save(Personnel.builder().name("黄文杰").email("xywenjie@outlook.com").build());
            Optional<UserLogin> optional = userLoginRepository.findByUsername("admin");
            if(optional.isPresent()){
            	UserLogin userInfo = optional.get();
                userInfo.setPersonnel(personnel);
                userLoginRepository.save(userInfo);
            }
        }
        return userDetailsManager;

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
