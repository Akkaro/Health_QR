package com.healthqr.healthqr.repository;

import com.healthqr.healthqr.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
