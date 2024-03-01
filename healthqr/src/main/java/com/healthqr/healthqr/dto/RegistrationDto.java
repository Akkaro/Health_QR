package com.healthqr.healthqr.dto;

import com.healthqr.healthqr.models.BloodTypes;
import com.healthqr.healthqr.models.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class RegistrationDto {
    private Long id;
    @NotEmpty(message = "Password should not be null")
    private String password;

    @NotEmpty(message = "Role should not be null")
    private String role;

    @NotEmpty(message = "First name should not be null")
    private String firstName;

    @NotEmpty(message = "Last name should not be null")
    private String lastName;

    @Email(message = "Please add a valid email address")
    @NotEmpty(message = "Email should not be null")
    private String email;


    private String mobilePhoneNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime dateOfBirth;
    private String personalIdentificationNumber;
    private UserEntity user;
    private BloodTypes bloodType;
    private String medicalStatus;
    private LocalDateTime entryDate;
    private String qrCodeLink;


}
