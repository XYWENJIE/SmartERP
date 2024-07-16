package com.benjamin.smarterp.service;


import com.benjamin.smarterp.domain.entity.Personnel;
import com.benjamin.smarterp.domain.entity.UserLogin;
import com.benjamin.smarterp.repository.jpa.PersonnelRepository;
import com.benjamin.smarterp.repository.jpa.UserLoginRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CommonService {

    private final PersonnelRepository personnelRepository;
    private final UserLoginRepository userLoginRepository;

    public CommonService(PersonnelRepository personnelRepository, UserLoginRepository userLoginRepository) {
        this.personnelRepository = personnelRepository;
        this.userLoginRepository = userLoginRepository;
    }


    public String getVersion() {
        String version = CommonService.class.getPackage().getImplementationVersion();
        return version + "WORKING";
        //return "0.0.1-SNAPSHOT";
    }

    public Personnel authenticationConvert(){
        return authenticationConvert(SecurityContextHolder.getContext().getAuthentication());
    }

    public Personnel authenticationConvert(Authentication authentication){
        if(authentication instanceof JwtAuthenticationToken){
            log.debug("JWT Authentication");
            JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;
            String name = token.getName();
            log.debug("name:{}",name);
            Optional<UserLogin> optional = this.userLoginRepository.findByUsername(name);
            if(optional.isPresent()){
                Optional<Personnel> optionalUserInfo = this.personnelRepository.findByUserLogin(optional.get());
                return optionalUserInfo.get();
            }

        }
        return null;
    }
}
