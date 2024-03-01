package com.healthqr.healthqr.repository;

import com.healthqr.healthqr.models.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TreatmentRepository extends JpaRepository<Treatment, Long>{
    @Query("SELECT t FROM Treatment t WHERE CAST(t.patientId AS string) LIKE CONCAT('%', :query, '%')")
    Optional<Treatment> findByPatientId(@Param("query") long query);

//    @Query("SELECT t from Treatment t WHERE t.treatmentId LIKE CONCAT('%', :query, '%')")
//    List<Treatment> searchTreatments(String query);

    @Query("SELECT t FROM Treatment t WHERE CAST(t.treatmentId AS string) LIKE CONCAT('%', :query, '%')")
    List<Treatment> searchTreatments(@Param("query") String query);

}
