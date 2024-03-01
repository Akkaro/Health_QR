package com.healthqr.healthqr.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
public class Patient extends Person {
    private String bloodType;
    private String medicalStatus;
    @CreationTimestamp
    private LocalDateTime entryDate;
    private String qrCodeLink;
}