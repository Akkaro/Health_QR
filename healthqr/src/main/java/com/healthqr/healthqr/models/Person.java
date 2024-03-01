package com.healthqr.healthqr.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jdk.jfr.Timestamp;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;

    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String mobilePhoneNumber;
    @Timestamp
    private LocalDateTime dateOfBirth;
    private String personalIdentificationNumber;

    @OneToOne(mappedBy = "person")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "updated_by", nullable = false)
    private UserEntity updatedBy;
}
