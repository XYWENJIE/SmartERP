package com.benjamin.smarterp.domain.entity.oauth2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "TAB_OAUTH2_AUTHORIZATION")
public class Authorization implements Serializable {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "REGISTERED_CLIENT_ID", nullable = false)
    private String registeredClientId;

    @Column(name = "PRINCIPAL_NAME",nullable = false)
    private String principalName;

    @Column(name = "AUTHORIZATION_GRANT_TYPE",nullable = false)
    private String authorizationGrantType;

    @Column(name = "AUTHORIZATION_SCOPE",length = 1000)
    private String authorizationScope;

    @Column(name = "ATTRIBUTES",length = 4000)
    private String attributes;

    @Column(name = "STATE",length = 500)
    private String state;

    @Column(name = "AUTHORIZATION_CODE_VALUE",length = 4000)
    private String authorizationCodeValue;

    @Column(name = "AUTHORIZATION_CODE_ISSUED_AT")
    private Instant authorizationCodeIssuedAt;

    @Column(name = "AUTHORIZATION_CODE_EXPIRES_AT")
    private Instant authorizationCodeExpiresAt;

    @Column(name = "AUTHORIZATION_CODE_METADATA",length =  2000)
    private String authorizationCodeMetadata;

    @Column(name = "ACCESS_TOKEN_VALUE",length = 4000)
    private String accessTokenValue;

    @Column(name = "ACCESS_TOKEN_ISSUED_AT")
    private Instant accessTokenIssuedAt;

    @Column(name = "ACCESS_TOKEN_EXPIRES_AT")
    private Instant accessTokenExpiresAt;

    @Column(name = "ACCESS_TOKEN_METADATA")
    private String accessTokenMetadata;

    @Column(name = "ACCESS_TYPE_TYPE",length = 255)
    private String accessTokenType;

    @Column(name = "ACCESS_TOKEN_SCOPES")
    private String accessTokenScopes;

    @Column(name = "REFRESH_TOKEN_VALUE")
    private String refreshTokenValue;

    @Column(name = "REFRESH_TOKEN_ISSUED_AT")
    private Instant refreshTokenIssuedAt;

    @Column(name = "REFRESH_TOKEN_EXPIRES_AT")
    private Instant refreshTokenExpiresAt;

    @Column(name = "REFRESH_TOKEN_METADATA")
    private String refreshTokenMetadata;

    @Column(name = "OIDC_ID_TOKEN_VALUE",length = 4000)
    private String oidcIdTokenValue;

    @Column(name = "OIDC_ID_TOKEN_ISSUED_AT")
    private Instant oidcIdTokenIssuedAt;

    @Column(name = "OIDC_ID_TOKEN_EXPIRES_AT")
    private Instant oidcIdTokenExpiresAt;

    private String oidcIdTokenMetadata;

    @Column(name = "OIDC_ID_TOKEN_CLAIMS",length = 2000)
    private String oidcIdTokenClaims;

    @Column(name = "USER_CODE_VALUE")
    private String userCodeValue;

    @Column(name = "USER_CODE_ISSUED_AT")
    private Instant userCodeIssuedAt;

    @Column(name = "USER_CODE_EXPIRES_AT")
    private Instant userCodeExpiresAt;

    private String userCodeMetadata;

    private String deviceCodeValue;

    private Instant deviceCodeIssuedAt;

    private Instant deviceCodeExpiresAt;

    private String deviceCodeMetadata;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegisteredClientId() {
        return registeredClientId;
    }

    public void setRegisteredClientId(String registeredClientId) {
        this.registeredClientId = registeredClientId;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public String getAuthorizationGrantType() {
        return authorizationGrantType;
    }

    public void setAuthorizationGrantType(String authorizationGrantType) {
        this.authorizationGrantType = authorizationGrantType;
    }

    public String getAuthorizationScope() {
        return authorizationScope;
    }

    public void setAuthorizationScope(String authorizationScope) {
        this.authorizationScope = authorizationScope;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAuthorizationCodeValue() {
        return authorizationCodeValue;
    }

    public void setAuthorizationCodeValue(String authorizationCodeValue) {
        this.authorizationCodeValue = authorizationCodeValue;
    }

    public Instant getAuthorizationCodeIssuedAt() {
        return authorizationCodeIssuedAt;
    }

    public void setAuthorizationCodeIssuedAt(Instant authorizationCodeIssuedAt) {
        this.authorizationCodeIssuedAt = authorizationCodeIssuedAt;
    }

    public Instant getAuthorizationCodeExpiresAt() {
        return authorizationCodeExpiresAt;
    }

    public void setAuthorizationCodeExpiresAt(Instant authorizationCodeExpiresAt) {
        this.authorizationCodeExpiresAt = authorizationCodeExpiresAt;
    }

    public String getAuthorizationCodeMetadata() {
        return authorizationCodeMetadata;
    }

    public void setAuthorizationCodeMetadata(String authorizationCodeMetadata) {
        this.authorizationCodeMetadata = authorizationCodeMetadata;
    }

    public String getAccessTokenValue() {
        return accessTokenValue;
    }

    public void setAccessTokenValue(String accessTokenValue) {
        this.accessTokenValue = accessTokenValue;
    }

    public Instant getAccessTokenIssuedAt() {
        return accessTokenIssuedAt;
    }

    public void setAccessTokenIssuedAt(Instant accessTokenIssuedAt) {
        this.accessTokenIssuedAt = accessTokenIssuedAt;
    }

    public Instant getAccessTokenExpiresAt() {
        return accessTokenExpiresAt;
    }

    public void setAccessTokenExpiresAt(Instant accessTokenExpiresAt) {
        this.accessTokenExpiresAt = accessTokenExpiresAt;
    }

    public String getAccessTokenMetadata() {
        return accessTokenMetadata;
    }

    public void setAccessTokenMetadata(String accessTokenMetadata) {
        this.accessTokenMetadata = accessTokenMetadata;
    }

    public String getAccessTokenType() {
        return accessTokenType;
    }

    public void setAccessTokenType(String accessTokenType) {
        this.accessTokenType = accessTokenType;
    }

    public String getAccessTokenScopes() {
        return accessTokenScopes;
    }

    public void setAccessTokenScopes(String accessTokenScopes) {
        this.accessTokenScopes = accessTokenScopes;
    }

    public String getRefreshTokenValue() {
        return refreshTokenValue;
    }

    public void setRefreshTokenValue(String refreshTokenValue) {
        this.refreshTokenValue = refreshTokenValue;
    }

    public Instant getRefreshTokenIssuedAt() {
        return refreshTokenIssuedAt;
    }

    public void setRefreshTokenIssuedAt(Instant refreshTokenIssuedAt) {
        this.refreshTokenIssuedAt = refreshTokenIssuedAt;
    }

    public Instant getRefreshTokenExpiresAt() {
        return refreshTokenExpiresAt;
    }

    public void setRefreshTokenExpiresAt(Instant refreshTokenExpiresAt) {
        this.refreshTokenExpiresAt = refreshTokenExpiresAt;
    }

    public String getRefreshTokenMetadata() {
        return refreshTokenMetadata;
    }

    public void setRefreshTokenMetadata(String refreshTokenMetadata) {
        this.refreshTokenMetadata = refreshTokenMetadata;
    }

    public String getOidcIdTokenValue() {
        return oidcIdTokenValue;
    }

    public void setOidcIdTokenValue(String oidcIdTokenValue) {
        this.oidcIdTokenValue = oidcIdTokenValue;
    }

    public Instant getOidcIdTokenIssuedAt() {
        return oidcIdTokenIssuedAt;
    }

    public void setOidcIdTokenIssuedAt(Instant oidcIdTokenIssuedAt) {
        this.oidcIdTokenIssuedAt = oidcIdTokenIssuedAt;
    }

    public Instant getOidcIdTokenExpiresAt() {
        return oidcIdTokenExpiresAt;
    }

    public void setOidcIdTokenExpiresAt(Instant oidcIdTokenExpiresAt) {
        this.oidcIdTokenExpiresAt = oidcIdTokenExpiresAt;
    }

    public String getOidcIdTokenMetadata() {
        return oidcIdTokenMetadata;
    }

    public void setOidcIdTokenMetadata(String oidcIdTokenMetadata) {
        this.oidcIdTokenMetadata = oidcIdTokenMetadata;
    }

    public String getOidcIdTokenClaims() {
        return oidcIdTokenClaims;
    }

    public void setOidcIdTokenClaims(String oidcIdTokenClaims) {
        this.oidcIdTokenClaims = oidcIdTokenClaims;
    }

    public String getUserCodeValue() {
        return userCodeValue;
    }

    public void setUserCodeValue(String userCodeValue) {
        this.userCodeValue = userCodeValue;
    }

    public Instant getUserCodeIssuedAt() {
        return userCodeIssuedAt;
    }

    public void setUserCodeIssuedAt(Instant userCodeIssuedAt) {
        this.userCodeIssuedAt = userCodeIssuedAt;
    }

    public Instant getUserCodeExpiresAt() {
        return userCodeExpiresAt;
    }

    public void setUserCodeExpiresAt(Instant userCodeExpiresAt) {
        this.userCodeExpiresAt = userCodeExpiresAt;
    }

    public String getUserCodeMetadata() {
        return userCodeMetadata;
    }

    public void setUserCodeMetadata(String userCodeMetadata) {
        this.userCodeMetadata = userCodeMetadata;
    }

    public String getDeviceCodeValue() {
        return deviceCodeValue;
    }

    public void setDeviceCodeValue(String deviceCodeValue) {
        this.deviceCodeValue = deviceCodeValue;
    }

    public Instant getDeviceCodeIssuedAt() {
        return deviceCodeIssuedAt;
    }

    public void setDeviceCodeIssuedAt(Instant deviceCodeIssuedAt) {
        this.deviceCodeIssuedAt = deviceCodeIssuedAt;
    }

    public Instant getDeviceCodeExpiresAt() {
        return deviceCodeExpiresAt;
    }

    public void setDeviceCodeExpiresAt(Instant deviceCodeExpiresAt) {
        this.deviceCodeExpiresAt = deviceCodeExpiresAt;
    }

    public String getDeviceCodeMetadata() {
        return deviceCodeMetadata;
    }

    public void setDeviceCodeMetadata(String deviceCodeMetadata) {
        this.deviceCodeMetadata = deviceCodeMetadata;
    }
}
