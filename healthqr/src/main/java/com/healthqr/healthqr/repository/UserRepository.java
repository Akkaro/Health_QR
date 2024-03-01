package com.healthqr.healthqr.repository;

import com.healthqr.healthqr.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
    Boolean existsByEmail(String email);

    UserEntity findFirstByEmail(String email);
}
