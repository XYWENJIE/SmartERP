package com.benjamin.smarterp.repository.jpa;

import com.benjamin.smarterp.domain.entity.oauth2.Authorization;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;

public interface AuthorizationRepository extends JpaRepositoryImplementation<Authorization,String> {
	
	@Query("select a from Authorization a where a.state = :token"
			+ " or a.authorizationCodeValue = :token"
			+ " or a.accessTokenValue = :token"
			+ " or a.refreshTokenValue = :token"
			+ " or a.oidcIdTokenValue = :token"
			+ " or a.userCodeValue = :token"
			+ " or a.deviceCodeValue = :token")
	Optional<Authorization> findByStateOrAuthorizationCodeValueOrAccessTokenValueOrRefreshTokenValueOrOidcIdTokenValueOrUserCodeValueOrDeviceCodeValue(@Param("token") String token);

	Optional<Authorization> findByState(String token);

	Optional<Authorization> findByAuthorizationCodeValue(String token);

	Optional<Authorization> findByAccessTokenValue(String token);

	Optional<Authorization> findByRefreshTokenValue(String token);

	Optional<Authorization> findByUserCodeValue(String token);

	Optional<Authorization> findByOidcIdTokenValue(String token);

	Optional<Authorization> findByDeviceCodeValue(String token);
}
