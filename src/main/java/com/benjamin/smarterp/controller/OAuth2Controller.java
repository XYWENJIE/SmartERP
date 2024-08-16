package com.benjamin.smarterp.controller;

import com.benjamin.smarterp.service.CommonService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.keygen.Base64StringKeyGenerator;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationCode;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationProvider;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationToken;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

@RestController
@RequestMapping("oauth2")
public class OAuth2Controller {
	
	private final Logger log = LoggerFactory.getLogger(OAuth2Controller.class);

    //private final OAuth2AuthorizationService oAuth2AuthorizationService;
    private final CommonService commonService;
    private final OAuth2AuthorizationCodeRequestAuthenticationProvider authorizationCodeRequestAuthenticationProvider;

    public OAuth2Controller(List<OAuth2AuthorizationService> oAuth2AuthorizationService, CommonService commonService,
							@Qualifier("authorizationCodeRequestAuthenticationProider") OAuth2AuthorizationCodeRequestAuthenticationProvider authorizationCodeRequestAuthenticationProvider) {
        //this.oAuth2AuthorizationService = oAuth2AuthorizationService;
		this.commonService = commonService;
		this.authorizationCodeRequestAuthenticationProvider = authorizationCodeRequestAuthenticationProvider;
    }

    /**
     * 参考OAuth2AuthorizationEndpointFilter 类的实现
     * 关于Get方法由前端React来处理
     * @param serverWebExchange
     * @param authorizeRequest
     */
    @PostMapping("authorize")
    public void authorize(ServerWebExchange serverWebExchange, @RequestBody AuthorizeRequest authorizeRequest){
    	//获取参数模仿convert方法
    	String clientId = authorizeRequest.clientId;
    	String authorizationUri = serverWebExchange.getRequest().getURI().toString();;
    	//request.getRequestURL().toString();
    	Authentication principal = SecurityContextHolder.getContext().getAuthentication();
    	String redirectUri = authorizeRequest.redirectUri;
    	Set<String> scopes = null;
    	Map<String,Object> additionalParameters = new HashMap<>();
    	OAuth2AuthorizationCodeRequestAuthenticationToken token = new OAuth2AuthorizationCodeRequestAuthenticationToken(authorizationUri,clientId,principal,redirectUri,authorizeRequest.state(),scopes,additionalParameters);
    	
    	Authentication authenticationResult = this.authorizationCodeRequestAuthenticationProvider.authenticate(token);
    	
    	//OAuth2AuthorizationCodeRequestAuthenticationProvider。authenticate
        StringKeyGenerator authorizationCodeGenerator = new Base64StringKeyGenerator(Base64.getUrlEncoder(),96);
        Instant issuedAt = Instant.now();
        Instant expiresAt = issuedAt.plus(Duration.ofHours(1));
        OAuth2AuthorizationCode oAuth2AuthorizationCode = new OAuth2AuthorizationCode(authorizationCodeGenerator.generateKey(),issuedAt,expiresAt);
        log.info("构建授权码生成");
        //TODO
        //this.oAuth2AuthorizationService.save(oAuth2AuthorizationCode);
        log.info("保存授权");
        String redirectUriss = this.authorizeResponse(authenticationResult);
    }
    
    @PostMapping("token")
    public void Token(@RequestBody TokenRequest tokenRequest) {
    	Authentication clientPrincipal = SecurityContextHolder.getContext().getAuthentication();
    	Authentication authorizationGrantAuthentication = null;
    	Map<String, Object> additionalParameters = new HashMap<>();
    	if(tokenRequest.grantType().equals(AuthorizationGrantType.AUTHORIZATION_CODE.getValue())) {
    		authorizationGrantAuthentication = new OAuth2AuthorizationCodeAuthenticationToken(tokenRequest.code(),clientPrincipal,tokenRequest.redirectUri(),additionalParameters);
    	}
    	if(authorizationGrantAuthentication == null) {
    		throw new IllegalAccessError();
    	}
    }
    
    private String authorizeResponse(Authentication authentication) {
    	OAuth2AuthorizationCodeRequestAuthenticationToken authorizationCodeRequestAuthentication = (OAuth2AuthorizationCodeRequestAuthenticationToken)authentication;
    	
    	UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(authorizationCodeRequestAuthentication.getRedirectUri()).queryParam(OAuth2ParameterNames.CODE, authorizationCodeRequestAuthentication.getAuthorizationCode().getTokenValue());
    	if(StringUtils.hasText(authorizationCodeRequestAuthentication.getState())) {
    		uriBuilder.queryParam(OAuth2ParameterNames.STATE, UriUtils.encode(authorizationCodeRequestAuthentication.getState(), StandardCharsets.UTF_8));
    	}
    	
    	String redirectUri = uriBuilder.build(true).toUriString();
    	return redirectUri;
    }

    public record AuthorizeRequest(@JsonProperty("response_type") String responseType,@JsonProperty("client_id") String clientId,@JsonProperty("redirect_uri") String redirectUri,@JsonProperty("state") String state,@JsonProperty("scopes") String scopes){}
    
    public record TokenRequest(@JsonProperty("grant_type") String grantType,@JsonProperty("code") String code,@JsonProperty("redirect_uri") String redirectUri) {}
}
