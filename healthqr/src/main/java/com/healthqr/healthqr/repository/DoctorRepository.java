package com.healthqr.healthqr.repository;

import com.healthqr.healthqr.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
