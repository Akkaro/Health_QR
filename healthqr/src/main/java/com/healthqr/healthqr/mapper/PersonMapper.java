package com.healthqr.healthqr.mapper;

import com.healthqr.healthqr.dto.PersonDto;
import com.healthqr.healthqr.models.Person;

public class PersonMapper {

    public static PersonDto mapToPersonDto(Person person){
        PersonDto personDto = PersonDto.builder()
                .personId(person.getPersonId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .email(person.getEmail())
                .mobilePhoneNumber(person.getMobilePhoneNumber())
                .dateOfBirth(person.getDateOfBirth())
                .personalIdentificationNumber(person.getPersonalIdentificationNumber())
                .user(person.getUser())
                .updatedBy(person.getUpdatedBy())
                .build();
        return personDto;
    }

    public static Person mapToPerson(PersonDto person){
        Person personDto = Person.builder()
                .personId(person.getPersonId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .email(person.getEmail())
                .mobilePhoneNumber(person.getMobilePhoneNumber())
                .dateOfBirth(person.getDateOfBirth())
                .personalIdentificationNumber(person.getPersonalIdentificationNumber())
                .user(person.getUser())
                .updatedBy(person.getUpdatedBy())
                .build();
        return personDto;
    }
}
