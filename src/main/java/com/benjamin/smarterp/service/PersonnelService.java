package com.benjamin.smarterp.service;

import com.benjamin.smarterp.domain.entity.Personnel;
import com.benjamin.smarterp.repository.jpa.PersonnelRepository;
import com.github.javaparser.quality.NotNull;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import dev.hilla.BrowserCallable;

import java.util.List;

@AnonymousAllowed
@BrowserCallable
public class PersonnelService {

    private PersonnelRepository personnelRepository;

    public PersonnelService(PersonnelRepository personnelRepository) {
        this.personnelRepository = personnelRepository;
    }

    public List<PersonnelRecord> findPersonnelAll(){
        return this.personnelRepository.findAll().stream().map(personnel -> new PersonnelRecord(personnel.getEmail())).toList();
    }

    public record PersonnelRecord(@NotNull String email){}
}
