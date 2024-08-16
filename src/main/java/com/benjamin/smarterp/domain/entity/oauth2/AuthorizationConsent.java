package com.benjamin.smarterp.domain.entity.oauth2;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "TAB_OAUTH2_AUTHORIZATION_CONSENT")
@IdClass(AuthorizationConsent.AuthorizationConsentId.class)
public class AuthorizationConsent {
	
	@Id
	@Column(name = "REGISTERED_CLIENT_ID")
	private String registeredClientId;
	
	@Id
	@Column(name = "PRINCIPAL_NAME")
	private String principalName;
	
	@Column(name = "AUTHORITIES",length = 1000)
	private String authorities;
	
	
	
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



	public String getAuthorities() {
		return authorities;
	}



	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}



	public static class AuthorizationConsentId implements Serializable{
		private String registeredClientId;
		private String principalName;
		
		@Override
		public int hashCode() {
			return Objects.hash(principalName, registeredClientId);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			AuthorizationConsentId other = (AuthorizationConsentId) obj;
			return Objects.equals(principalName, other.principalName)
					&& Objects.equals(registeredClientId, other.registeredClientId);
		}
		
	}

}
