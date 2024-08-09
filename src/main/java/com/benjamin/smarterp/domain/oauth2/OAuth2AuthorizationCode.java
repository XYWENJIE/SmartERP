package com.benjamin.smarterp.domain.oauth2;

import org.springframework.security.oauth2.core.AbstractOAuth2Token;

import java.time.Instant;

public class OAuth2AuthorizationCode extends AbstractOAuth2Token {

    public OAuth2AuthorizationCode(String tokenValue, Instant issuedAt, Instant expiresAt){
        super(tokenValue,issuedAt,expiresAt);
    }
}
