package com.healthqr.healthqr.services.impl;

import com.healthqr.healthqr.dto.PersonDto;
import com.healthqr.healthqr.dto.RegistrationDto;
import com.healthqr.healthqr.models.Person;
import com.healthqr.healthqr.models.UserEntity;
import com.healthqr.healthqr.repository.PersonRepository;
import com.healthqr.healthqr.repository.TreatmentRepository;
import com.healthqr.healthqr.repository.UserRepository;
import com.healthqr.healthqr.security.SecurityUtil;
import com.healthqr.healthqr.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.healthqr.healthqr.mapper.PersonMapper.mapToPerson;
import static com.healthqr.healthqr.mapper.PersonMapper.mapToPersonDto;

@Service
public class PersonServiceImpl implements PersonService {

    private TreatmentRepository treatmentRepository;
    private UserRepository userRepository;
    private PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(TreatmentRepository treatmentRepository, UserRepository userRepository, PersonRepository personRepository) {
        this.treatmentRepository = treatmentRepository;
        this.userRepository = userRepository;
        this.personRepository = personRepository;
    }

    @Override
    public List<PersonDto> findAllPerson() {
        List<Person> persons = personRepository.findAll();
        return persons.stream().map((person) -> mapToPersonDto(person)).collect(Collectors.toList());
    }

    @Override
    public Person savePerson(PersonDto personDto) {
        String email = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByEmail(email);
        Person person = mapToPerson(personDto);
        person.setUser(user);
        return personRepository.save(person);
    }

    @Override
    public Person savePersonReg(RegistrationDto registrationDto, UserEntity user)
    {
        Person person = new Person();
        person.setUser(user);
        person.setEmail(registrationDto.getEmail());
        person.setLastName(registrationDto.getLastName());
        person.setFirstName(registrationDto.getFirstName());
        person.setPersonalIdentificationNumber(registrationDto.getPersonalIdentificationNumber());
        person.setDateOfBirth(registrationDto.getDateOfBirth());
        person.setMobilePhoneNumber(registrationDto.getMobilePhoneNumber());
        person.setUpdatedBy(user);
        return personRepository.save(person);
    }

    @Override
    public PersonDto findPersonById(Long personId) {
        Person person = personRepository.findById(personId).get();
        return mapToPersonDto(person);
    }

    @Override
    public void updatePerson(PersonDto personDto) {
        String email = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByEmail(email);
        Person person = mapToPerson(personDto);
        person.setUser(user);
        personRepository.save(person);
    }

    @Override
    public void deletePerson(Long personId) {
        personRepository.deleteById(personId);
    }

    @Override
    public List<PersonDto> searchPersons(String query) {
        List<Person> persons = personRepository.searchPersons(query);
        return persons.stream().map(person -> mapToPersonDto(person)).collect(Collectors.toList());

    }
}
