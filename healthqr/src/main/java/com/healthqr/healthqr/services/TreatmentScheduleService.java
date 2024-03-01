package com.healthqr.healthqr.services;

import com.healthqr.healthqr.dto.TreatmentScheduleDto;

import java.util.List;

public interface TreatmentScheduleService {

    void createTreatmentSchedule(Long treatmentId, TreatmentScheduleDto treatmentScheduleDto);
    List<TreatmentScheduleDto> findAllTreatmentSchedules();
    TreatmentScheduleDto findByTreatmentScheduleId(Long treatmentScheduleId);
    void updateTreatmentSchedule(TreatmentScheduleDto treatmentScheduleDto);
    void deleteTreatmentSchedule(long treatmentScheduleId);
}
