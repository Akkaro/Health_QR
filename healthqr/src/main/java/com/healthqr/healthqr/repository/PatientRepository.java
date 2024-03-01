package com.healthqr.healthqr.repository;

import com.healthqr.healthqr.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("SELECT p FROM Patient p WHERE " +
            "LOWER(CAST(p.personId AS STRING)) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(p.firstName) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(p.lastName) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Patient> searchPatients(@Param("query") String query);

    @Query("SELECT p FROM Patient p WHERE p.email like (:query)")
    Patient findByEmail(@Param("query") String query);

}
