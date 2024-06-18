package com.benjamin.smarterp.configuration;

import com.benjamin.smarterp.domain.entity.Personnel;
import com.benjamin.smarterp.domain.entity.UserInfo;
import com.benjamin.smarterp.repository.jpa.AuthoritiesRepository;
import com.benjamin.smarterp.repository.jpa.PersonnelRepository;
import com.benjamin.smarterp.repository.jpa.UserInfoRepository;
import com.benjamin.smarterp.security.JpaUserDetailsManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Optional;

@Slf4j
@EnableWebSecurity
@Configuration
public class SecurityConfiguration {


    @Bean
    public UserDetailsManager userDetailsManager(UserInfoRepository userInfoRepository,
                                                 AuthoritiesRepository authoritiesRepository,
                                                 PasswordEncoder passwordEncoder, PersonnelRepository personnelRepository){
        JpaUserDetailsManager userDetailsManager = new JpaUserDetailsManager(userInfoRepository,authoritiesRepository,passwordEncoder);
        if(!userDetailsManager.userExists("admin")){
            userDetailsManager.createUser(User.withUsername("admin").password("huang1100").roles("ADMIN").build());
            Personnel personnel = personnelRepository.save(Personnel.builder().name("黄文杰").email("xywenjie@outlook.com").build());
            Optional<UserInfo> optional = userInfoRepository.findByUsername("admin");
            if(optional.isPresent()){
                UserInfo userInfo = optional.get();
                userInfo.setPersonnel(personnel);
                userInfoRepository.save(userInfo);
            }
        }
        return userDetailsManager;

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
