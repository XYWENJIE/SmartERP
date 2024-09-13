package com.benjamin.smarterp.repository.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.benjamin.smarterp.domain.entity.oauth2.Client;

public interface ClientRepository extends JpaRepositoryImplementation<Client, String> {
	
	Optional<Client> findByClientId(String clientId);

}
