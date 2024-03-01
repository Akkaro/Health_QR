package com.healthqr.healthqr.mapper;

import com.healthqr.healthqr.dto.TreatmentScheduleDto;
import com.healthqr.healthqr.models.TreatmentSchedule;

public class TreatmentScheduleMapper {

    public static TreatmentSchedule mapToTreatmentSchedule(TreatmentScheduleDto treatmentScheduleDto) {
        return TreatmentSchedule.builder()
                .treatmentScheduleId(treatmentScheduleDto.getTreatmentScheduleId())
                .nurseId(treatmentScheduleDto.getNurseId())
                .doctorId(treatmentScheduleDto.getDoctorId())
                .treatmentText(treatmentScheduleDto.getTreatmentText())
                .status(treatmentScheduleDto.getStatus())
                .executionDate(treatmentScheduleDto.getExecutionDate())
                .creationDate(treatmentScheduleDto.getCreationDate())
                .updatedOn(treatmentScheduleDto.getUpdatedOn())
                .treatment(treatmentScheduleDto.getTreatment())
                .build();
    }

    public static  TreatmentScheduleDto mapToTreatmentScheduleDto(TreatmentSchedule treatmentSchedule) {
        return TreatmentScheduleDto.builder()
                .treatmentScheduleId(treatmentSchedule.getTreatmentScheduleId())
                .nurseId(treatmentSchedule.getNurseId())
                .doctorId(treatmentSchedule.getDoctorId())
                .treatmentText(treatmentSchedule.getTreatmentText())
                .status(treatmentSchedule.getStatus())
                .executionDate(treatmentSchedule.getExecutionDate())
                .creationDate(treatmentSchedule.getCreationDate())
                .updatedOn(treatmentSchedule.getUpdatedOn())
                .treatment(treatmentSchedule.getTreatment())
                .build();
    }
}
