package com.healthqr.healthqr.services.impl;

import com.healthqr.healthqr.dto.PatientDto;
import com.healthqr.healthqr.dto.RegistrationDto;
import com.healthqr.healthqr.models.Patient;
import com.healthqr.healthqr.models.UserEntity;
import com.healthqr.healthqr.repository.PatientRepository;
import com.healthqr.healthqr.repository.PersonRepository;
import com.healthqr.healthqr.repository.UserRepository;
import com.healthqr.healthqr.security.SecurityUtil;
import com.healthqr.healthqr.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.healthqr.healthqr.mapper.PatientMapper.mapToPatient;
import static com.healthqr.healthqr.mapper.PatientMapper.mapToPatientDto;

@Service
public class PatientServiceImpl implements PatientService {

    private UserRepository userRepository;
    private PersonRepository personRepository;
    private PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(UserRepository userRepository, PersonRepository personRepository, PatientRepository patientRepository) {
        this.userRepository = userRepository;
        this.personRepository = personRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public List<PatientDto> findAllPatient() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map((patient) -> mapToPatientDto(patient)).collect(Collectors.toList());
    }

    @Override
    public Patient savePatient(PatientDto patientDto) {
        String email = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByEmail(email);
        Patient patient = mapToPatient(patientDto);
        patient.setUser(user);
        return patientRepository.save(patient);
    }

    @Override
    public Patient savePatientReg(RegistrationDto registrationDto, UserEntity user) {

        Patient patient = new Patient();
        patient.setUser(user);
        patient.setEmail(registrationDto.getEmail());
        patient.setLastName(registrationDto.getLastName());
        patient.setFirstName(registrationDto.getFirstName());
        patient.setPersonalIdentificationNumber(registrationDto.getPersonalIdentificationNumber());
        patient.setDateOfBirth(registrationDto.getDateOfBirth());
        patient.setMobilePhoneNumber(registrationDto.getMobilePhoneNumber());
        patient.setBloodType(registrationDto.getBloodType().toString());
        patient.setEntryDate(registrationDto.getEntryDate());
        patient.setQrCodeLink(registrationDto.getQrCodeLink());
        patient.setMedicalStatus(registrationDto.getMedicalStatus());
        patient.setUpdatedBy(user);
        return patientRepository.save(patient);
    }

    @Override
    public PatientDto findPatientById(Long patientId) {
        Patient patient = patientRepository.findById(patientId).get();
        if(patient == null)
            return null;
        return mapToPatientDto(patient);
    }

    @Override
    public PatientDto findPatientByEmail(String email) {
        Patient patient = patientRepository.findByEmail(email);
        if(patient == null)
            return null;
        return mapToPatientDto(patient);
    }

    @Override
    public void updatePatient(PatientDto patientDto) {
        String email = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByEmail(email);
        Patient patient = mapToPatient(patientDto);
        patient.setUpdatedBy(user);
        patientRepository.save(patient);
    }

    @Override
    public void deletePatient(Long patientId) {
        patientRepository.deleteById(patientId);
    }

    @Override
    public List<PatientDto> searchPatients(String query) {
        List<Patient> patients = patientRepository.searchPatients(query);
        return patients.stream().map(patient -> mapToPatientDto(patient)).collect(Collectors.toList());

    }
}
