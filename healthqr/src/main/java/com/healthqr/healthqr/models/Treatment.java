package com.healthqr.healthqr.models;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "treatmentplans")
public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long treatmentId;
    private Long doctorId;
    private Long patientId;


    @CreationTimestamp
    private LocalDateTime creationDate;

    @Timestamp
    private LocalDateTime completionDate;

    private String currentStatus;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private UserEntity createdBy;

    @OneToMany(mappedBy = "treatment", cascade = CascadeType.REMOVE)
    private List<TreatmentSchedule> treatmentSchedules = new ArrayList<>();

}
