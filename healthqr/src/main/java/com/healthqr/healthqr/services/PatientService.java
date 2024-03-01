package com.healthqr.healthqr.services;

import com.healthqr.healthqr.dto.PatientDto;
import com.healthqr.healthqr.dto.RegistrationDto;
import com.healthqr.healthqr.models.Patient;
import com.healthqr.healthqr.models.UserEntity;

import java.util.List;

public interface PatientService {
    List<PatientDto> findAllPatient();
    Patient savePatient(PatientDto patientDto);
    Patient savePatientReg(RegistrationDto registrationDto, UserEntity user);
    PatientDto findPatientByEmail(String email);
    PatientDto findPatientById(Long patientId);
    void updatePatient(PatientDto patientDto);
    void deletePatient(Long patientId);
    List<PatientDto> searchPatients(String query);
}
