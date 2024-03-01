package com.healthqr.healthqr.services;

import com.healthqr.healthqr.dto.PersonDto;
import com.healthqr.healthqr.dto.RegistrationDto;
import com.healthqr.healthqr.models.Person;
import com.healthqr.healthqr.models.UserEntity;

import java.util.List;

public interface PersonService {
    List<PersonDto> findAllPerson();
    Person savePerson(PersonDto personDto);
    Person savePersonReg(RegistrationDto registrationDto, UserEntity user);
    PersonDto findPersonById(Long personId);
    void updatePerson(PersonDto personDto);
    void deletePerson(Long personId);
    List<PersonDto> searchPersons(String query);
}
