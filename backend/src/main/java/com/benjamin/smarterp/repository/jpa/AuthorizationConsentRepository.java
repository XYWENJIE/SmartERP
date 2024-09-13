package com.benjamin.smarterp.repository.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.benjamin.smarterp.domain.entity.oauth2.AuthorizationConsent;

public interface AuthorizationConsentRepository extends JpaRepositoryImplementation<AuthorizationConsent, AuthorizationConsent.AuthorizationConsentId>{
	
	Optional<AuthorizationConsent> findByRegisteredClientIdAndPrincipalName(String registeredClientId,String principalName);
	
	void deleteByRegisteredClientIdAndPrincipalName(String registeredClientId,String principalNae);

}
