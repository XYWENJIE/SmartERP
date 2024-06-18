package com.benjamin.smarterp.service;

import com.benjamin.smarterp.repository.jpa.PersonnelRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
