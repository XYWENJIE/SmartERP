package com.benjamin.smarterp.controller;

import com.benjamin.smarterp.domain.ResultStatus;
import com.benjamin.smarterp.service.CommonUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class HomeController {

    private final AuthenticationManager authenticationManager;
    private final JwtEncoder encoder;

    public HomeController(AuthenticationManager authenticationManager, JwtEncoder encoder) {
        this.authenticationManager = authenticationManager;
        this.encoder = encoder;
    }

    public void index(){

    }

    @RequestMapping("version")
    public String version(){
        return CommonUnit.getVersion();
    }

    @PostMapping("/login")
    public ResultStatus doLogin(@RequestBody LoginResponse loginResponse){
        log.info("执行登录");
        //SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(loginResponse.username,loginResponse.password);
        Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);
        //securityContext.setAuthentication(authenticationResponse);
        //SecurityContextHolder.setContext(securityContext);
        Instant now = Instant.now();
        long expiry = 36000L;
        String scope = authenticationResponse.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder().issuer("self").issuedAt(now).expiresAt(now.plusSeconds(expiry)).subject(authenticationResponse.getName()).claim("scope",scope).build();
        String token = this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return ResultStatus.builder().code(0).token(token).build();
    }

    @GetMapping("csrf")
    public CsrfToken csrf(CsrfToken csrfToken){
        return csrfToken;
    }

    @ExceptionHandler
    public ResultStatus badCredentials(Exception exception){
        if(exception instanceof BadCredentialsException){
            return ResultStatus.builder().code(400).message(exception.getMessage()).build();
        }else{
            log.error(exception.getMessage(),exception);
        }
        return ResultStatus.builder().code(500).message(exception.getMessage()).build();
    }

    public record LoginResponse(String username,String password){}
}
