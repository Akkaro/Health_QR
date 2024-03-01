package com.healthqr.healthqr.mapper;

import com.healthqr.healthqr.dto.PatientDto;
import com.healthqr.healthqr.models.Patient;

public class PatientMapper {

    public static Patient mapToPatient(PatientDto patient){
        Patient patientDto = Patient.builder()
                .personId(patient.getPersonId())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .email(patient.getEmail())
                .mobilePhoneNumber(patient.getMobilePhoneNumber())
                .dateOfBirth(patient.getDateOfBirth())
                .personalIdentificationNumber(patient.getPersonalIdentificationNumber())
                .bloodType(patient.getBloodType())
                .medicalStatus(patient.getMedicalStatus())
                .entryDate(patient.getEntryDate())
                .qrCodeLink(patient.getQrCodeLink())
                .user(patient.getUser())
                .updatedBy(patient.getUpdatedBy())
                .build();
        return patientDto;
    }
    public static PatientDto mapToPatientDto(Patient patient){
        PatientDto patientDto = PatientDto.builder()
                .personId(patient.getPersonId())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .email(patient.getEmail())
                .mobilePhoneNumber(patient.getMobilePhoneNumber())
                .dateOfBirth(patient.getDateOfBirth())
                .personalIdentificationNumber(patient.getPersonalIdentificationNumber())
                .bloodType(patient.getBloodType())
                .medicalStatus(patient.getMedicalStatus())
                .entryDate(patient.getEntryDate())
                .qrCodeLink(patient.getQrCodeLink())
                .user(patient.getUser())
                .updatedBy(patient.getUpdatedBy())
                .build();
        return patientDto;
    }
}
