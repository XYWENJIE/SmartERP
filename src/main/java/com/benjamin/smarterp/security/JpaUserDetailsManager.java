package com.benjamin.smarterp.security;

import com.benjamin.smarterp.domain.entity.Authorities;
import com.benjamin.smarterp.domain.entity.UserInfo;
import com.benjamin.smarterp.repository.jpa.AuthoritiesRepository;
import com.benjamin.smarterp.repository.jpa.UserInfoRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
public class JpaUserDetailsManager implements UserDetailsManager {

    private final UserInfoRepository userInfoRepository;
    private final AuthoritiesRepository authoritiesRepository;
    private final PasswordEncoder passwordEncoder;

    public JpaUserDetailsManager(UserInfoRepository userInfoRepository, AuthoritiesRepository authoritiesRepository, PasswordEncoder passwordEncoder) {
        this.userInfoRepository = userInfoRepository;
        this.authoritiesRepository = authoritiesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createUser(UserDetails user) {
        UserInfo userInfo = UserInfo.builder().username(user.getUsername()).password(passwordEncoder.encode(user.getPassword())).build();
        this.userInfoRepository.save(userInfo);
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

    }

    @Override
    public boolean userExists(String username) {
        return this.userInfoRepository.existsByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> optional = this.userInfoRepository.findByUsername(username);
        if(optional.isEmpty()){
            throw new UsernameNotFoundException(username);
        }
        UserInfo userInfo = optional.get();
        List<GrantedAuthority> authorities = new ArrayList<>(userInfo.getAuthorities().stream().map(authorities1 -> new SimpleGrantedAuthority(authorities1.getAuthority())).toList());
        return new User(userInfo.getUsername(),userInfo.getPassword(),authorities);
    }
}
