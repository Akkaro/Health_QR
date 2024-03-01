package com.healthqr.healthqr.dto;

import com.healthqr.healthqr.models.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
public class PatientDto {
    private Long personId;

    @NotEmpty(message = "First name should not be null")
    private String firstName;
    @NotEmpty(message = "Last name should not be null")
    private String lastName;
    @Email(message = "Please add a valid email address")
    private String email;

    private String mobilePhoneNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime dateOfBirth;
    private String personalIdentificationNumber;
    private UserEntity user;
    private UserEntity updatedBy;
    private String bloodType;
    private String medicalStatus;
    private LocalDateTime entryDate;
    private String qrCodeLink;
}
