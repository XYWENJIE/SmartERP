package com.benjamin.smarterp.domain.entity.oauth2;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TAB_OAUTH2_CLIENT")
public class Client {
	
	@Id
	private String id;
	
	@Column(name = "CLIENT_ID")
	private String clientId;
	
	@Column(name = "CLIENT_ID_ISSUED_AT")
	private Instant clientIdIssuedAt;
	
	@Column(name = "CLIENT_SECRET")
	private String clientSecret;
	
	@Column(name = "CLIENT_SECRET_EXPIRES_AT")
	private Instant clientSecretExpiresAt;
	
	@Column(name = "CLIENT_NAME")
	private String clientName;
	
	@Column(name = "CLIENT_AUTHENICATION_METHODS")
	private String clientAuthenticationMethods;
	
	@Column(name = "AUTHORIZATION_GRANT_TYPE",length = 1000)
	private String  authorizationGrantType;
	
	@Column(name = "REDIRECT_URIS")
	private String redirectUris;
	
	@Column(name = "POST_LOGOUT_REDIRECT_URIS")
	private String postLogoutRedirectUris;
	
	@Column(name = "SCOPES")
	private String scopes;
	
	@Column(name = "CLIENT_SETTINGS")
	private String clientSettings;
	
	@Column(name = "TOKEN_SETTINGS")
	private String tokenSettings;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Instant getClientIdIssuedAt() {
		return clientIdIssuedAt;
	}

	public void setClientIdIssuedAt(Instant clientIdIssuedAt) {
		this.clientIdIssuedAt = clientIdIssuedAt;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public Instant getClientSecretExpiresAt() {
		return clientSecretExpiresAt;
	}

	public void setClientSecretExpiresAt(Instant clientSecretExpiresAt) {
		this.clientSecretExpiresAt = clientSecretExpiresAt;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientAuthenticationMethods() {
		return clientAuthenticationMethods;
	}

	public void setClientAuthenticationMethods(String clientAuthenticationMethods) {
		this.clientAuthenticationMethods = clientAuthenticationMethods;
	}

	public String getAuthorizationGrantType() {
		return authorizationGrantType;
	}

	public void setAuthorizationGrantType(String authorizationGrantType) {
		this.authorizationGrantType = authorizationGrantType;
	}

	public String getRedirectUris() {
		return redirectUris;
	}

	public void setRedirectUris(String redirectUris) {
		this.redirectUris = redirectUris;
	}

	public String getPostLogoutRedirectUris() {
		return postLogoutRedirectUris;
	}

	public void setPostLogoutRedirectUris(String postLogoutRedirectUris) {
		this.postLogoutRedirectUris = postLogoutRedirectUris;
	}

	public String getScopes() {
		return scopes;
	}

	public void setScopes(String scopes) {
		this.scopes = scopes;
	}

	public String getClientSettings() {
		return clientSettings;
	}

	public void setClientSettings(String clientSettings) {
		this.clientSettings = clientSettings;
	}

	public String getTokenSettings() {
		return tokenSettings;
	}

	public void setTokenSettings(String tokenSettings) {
		this.tokenSettings = tokenSettings;
	}
	
	

}
