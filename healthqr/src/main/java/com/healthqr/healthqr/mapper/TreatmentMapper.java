package com.healthqr.healthqr.mapper;

import com.healthqr.healthqr.dto.TreatmentDto;
import com.healthqr.healthqr.models.Treatment;

import java.util.stream.Collectors;

import static com.healthqr.healthqr.mapper.TreatmentScheduleMapper.mapToTreatmentScheduleDto;

//import static com.rungroop.web.mapper.EventMapper.mapToEventDto;

public class TreatmentMapper {
    public static TreatmentDto mapToTreatmentDto(Treatment treatment){
        TreatmentDto treatmentDto = TreatmentDto.builder()
                .treatmentId(treatment.getTreatmentId())
                .doctorId(treatment.getDoctorId())
                .patientId(treatment.getPatientId())
                .creationDate(treatment.getCreationDate())
                .completionDate(treatment.getCompletionDate())
                .currentStatus(treatment.getCurrentStatus())
                .createdBy(treatment.getCreatedBy())
                .treatmentSchedules(treatment.getTreatmentSchedules().stream().map((treatmentSchedule) -> mapToTreatmentScheduleDto(treatmentSchedule)).collect(Collectors.toList()))
                .build();
        return treatmentDto;
    }

    public static Treatment mapToTreatment(TreatmentDto treatment){
        Treatment treatmentDto = Treatment.builder()
                .treatmentId(treatment.getTreatmentId())
                .doctorId(treatment.getDoctorId())
                .patientId(treatment.getPatientId())
                .creationDate(treatment.getCreationDate())
                .completionDate(treatment.getCompletionDate())
                .currentStatus(treatment.getCurrentStatus())
                .createdBy(treatment.getCreatedBy())
                .build();
        return treatmentDto;
    }
}
