package com.benjamin.smarterp.security;

import com.benjamin.smarterp.domain.entity.Authorities;
import com.benjamin.smarterp.domain.entity.UserLogin;
import com.benjamin.smarterp.domain.entity.UserLoginHistory;
import com.benjamin.smarterp.domain.entity.UserLoginPasswordHistory;
import com.benjamin.smarterp.repository.jpa.AuthoritiesRepository;
import com.benjamin.smarterp.repository.jpa.UserLoginRepository;
import com.benjamin.smarterp.repository.jpa.UserLoginHistoryRepository;
import com.benjamin.smarterp.repository.jpa.UserLoginPasswordHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Transactional
public class JpaUserDetailsManager implements UserDetailsManager {

    private final UserLoginRepository userLoginRepository;
    private final AuthoritiesRepository authoritiesRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserLoginHistoryRepository userLoginHistoryRepository;
    private final UserLoginPasswordHistoryRepository userLoginPasswordHistoryRepository;

    private SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();

    public JpaUserDetailsManager(UserLoginRepository userLoginRepository, AuthoritiesRepository authoritiesRepository, PasswordEncoder passwordEncoder, UserLoginHistoryRepository userLoginHistoryRepository, UserLoginPasswordHistoryRepository userLoginPasswordHistoryRepository) {
        this.userLoginRepository = userLoginRepository;
        this.authoritiesRepository = authoritiesRepository;
        this.passwordEncoder = passwordEncoder;
        this.userLoginHistoryRepository = userLoginHistoryRepository;
        this.userLoginPasswordHistoryRepository = userLoginPasswordHistoryRepository;
    }

    @Override
    public void createUser(UserDetails user) {
    	UserLogin userInfo = UserLogin.builder().username(user.getUsername()).password(passwordEncoder.encode(user.getPassword())).build();
        log.info("CreateUser");
        this.userLoginRepository.save(userInfo);

        this.userLoginPasswordHistoryRepository.save(
                UserLoginPasswordHistory.builder()
                        .currentPassword(user.getPassword())
                        .userLoginId(userInfo.getId())
                        .build()
        );
        List<Authorities> authoritiesList = user.getAuthorities().stream().map(authority -> Authorities.builder().username(user.getUsername()).authority(authority.getAuthority()).build()).toList();
        this.authoritiesRepository.saveAll(authoritiesList);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        Authentication currentUser = this.securityContextHolderStrategy.getContext().getAuthentication();
        Optional<UserLogin> optional = this.userLoginRepository.findByUsername(currentUser.getName());
        if(optional.isPresent()){
            UserLogin userLogin = optional.get();
            Optional<UserLoginPasswordHistory> userLoginPasswordHistoryOptional = this.userLoginPasswordHistoryRepository.findByCurrentPassword(userLogin.getPassword());
            if(userLoginPasswordHistoryOptional.isPresent()){
                userLoginPasswordHistoryOptional.get().setThruDate(LocalDateTime.now());
                this.userLoginPasswordHistoryRepository.save(userLoginPasswordHistoryOptional.get());
                userLogin.setPassword(passwordEncoder.encode(newPassword));
                this.userLoginRepository.save(userLogin);
                this.userLoginPasswordHistoryRepository.save(UserLoginPasswordHistory.builder()
                        .userLoginId(userLogin.getId())
                        .currentPassword(userLogin.getPassword())
                        .build());
            }
        }
    }

    @Override
    public boolean userExists(String username) {
        return this.userLoginRepository.existsByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserLogin> optional = this.userLoginRepository.findByUsername(username);
        if(optional.isEmpty()){
            throw new UsernameNotFoundException(username);
        }
        UserLogin userInfo = optional.get();
        this.userLoginHistoryRepository.save(UserLoginHistory.builder().userLoginId(userInfo.getId()).formDate(LocalDateTime.now()).build());
        List<GrantedAuthority> authorities = new ArrayList<>(userInfo.getAuthorities().stream().map(authorities1 -> new SimpleGrantedAuthority(authorities1.getAuthority())).toList());
        return new User(userInfo.getUsername(),userInfo.getPassword(),authorities);
    }
}
