package com.healthqr.healthqr.models;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TreatmentSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long treatmentScheduleId;
    private Long nurseId;
    private Long doctorId;
    private String treatmentText;
    private String status;
    @Timestamp
    private LocalDateTime executionDate;
    @CreationTimestamp
    private LocalDateTime creationDate;
    @UpdateTimestamp
    private LocalDateTime updatedOn;

//    @OneToMany(mappedBy = "treatmentplans", cascade = CascadeType.REMOVE)
//    //@JoinColumn(name="treatment_id", nullable = false)
//    private Set<Treatment> treatment = new HashSet<>();
    @ManyToOne
    @JoinColumn(name="treatment_id", nullable = false)
    private Treatment treatment;
}
