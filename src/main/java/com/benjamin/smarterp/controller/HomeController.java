package com.benjamin.smarterp.controller;

import com.benjamin.smarterp.domain.ResultStatus;
import com.benjamin.smarterp.service.CommonService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
public class HomeController {
	
	private Logger log = LoggerFactory.getLogger(HomeController.class);

    private final AuthenticationManager authenticationManager;
    private final CommonService commonService;
    private final JwtEncoder encoder;

    public HomeController(AuthenticationManager authenticationManager, CommonService commonService, JwtEncoder encoder) {
        this.authenticationManager = authenticationManager;
        this.commonService = commonService;
        this.encoder = encoder;
    }

    public void index(){

    }

    @RequestMapping("version")
    public String version(){
        return commonService.getVersion();
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
        return ResultStatus.successToken(token);
    }

    @GetMapping("csrf")
    public CsrfToken csrf(CsrfToken csrfToken){
        return csrfToken;
    }

    @ExceptionHandler
    public ResultStatus badCredentials(Exception exception){
        if(exception instanceof BadCredentialsException){
            return ResultStatus.error(exception.getMessage());
        }else{
            log.error(exception.getMessage(),exception);
        }
        return ResultStatus.error(exception.getMessage());
    }

    public record LoginResponse(String username,String password){}
}
