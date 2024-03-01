package com.healthqr.healthqr.services;

import com.healthqr.healthqr.dto.TreatmentDto;
import com.healthqr.healthqr.models.Treatment;

import java.util.List;
public interface TreatmentService {
    List<TreatmentDto> findAllTreatment();
    Treatment saveTreatment(TreatmentDto treatmentDto);
    TreatmentDto findTreatmentById(Long treatmentId);
    void updateTreatment(TreatmentDto treatmentDto);
    void deleteTreatment(Long treatmentId);
    TreatmentDto findTreatmentByPatientId(Long patientId);
    List<TreatmentDto> searchTreatments(String query);
}
