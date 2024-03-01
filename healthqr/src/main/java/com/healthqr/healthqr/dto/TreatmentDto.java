package com.healthqr.healthqr.dto;

import com.healthqr.healthqr.models.UserEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.Timestamp;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class TreatmentDto {
    private Long treatmentId;
    @NotNull(message = "Doctor ID should not be empty")
    private Long doctorId;
    @NotNull(message = "Patient ID should not be empty")
    private Long patientId;
    //@NotNull(message = "Creation date should not be empty")
    private LocalDateTime creationDate;
    private UserEntity createdBy;
    @NotNull(message = "Time should not be null")
    @Timestamp
    private LocalDateTime completionDate;

    @NotBlank(message = "Current status should not be empty")
    private String currentStatus;

    private List<TreatmentScheduleDto> treatmentSchedules;
}
