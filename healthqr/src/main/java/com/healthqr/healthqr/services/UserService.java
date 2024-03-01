package com.healthqr.healthqr.services;

import com.healthqr.healthqr.dto.RegistrationDto;
import com.healthqr.healthqr.models.Role;
import com.healthqr.healthqr.models.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);
    Role getSmallestRoleIdForUser(Long userId);
    Role getHighestRole();
}
