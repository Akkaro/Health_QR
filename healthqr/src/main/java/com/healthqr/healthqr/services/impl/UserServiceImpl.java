package com.healthqr.healthqr.services.impl;

import com.healthqr.healthqr.dto.RegistrationDto;
import com.healthqr.healthqr.models.Role;
import com.healthqr.healthqr.models.UserEntity;
import com.healthqr.healthqr.repository.RoleRepository;
import com.healthqr.healthqr.repository.UserRepository;
import com.healthqr.healthqr.security.SecurityUtil;
import com.healthqr.healthqr.services.PatientService;
import com.healthqr.healthqr.services.PersonService;
import com.healthqr.healthqr.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private PersonService personService;
    private PatientService patientService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, PersonService personService, PatientService patientService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.personService = personService;
        this.patientService = patientService;
    }
    @Override
    public void saveUser(RegistrationDto registrationDto) {
        UserEntity user = new UserEntity();
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        Role role = roleRepository.findByName(registrationDto.getRole());
        user.setRoles(Arrays.asList(role));



        userRepository.save(user);

        UserEntity madeUser = findByEmail(user.getEmail());
        if(registrationDto.getRole().equals("PATIENT"))
            patientService.savePatientReg(registrationDto, madeUser);
        else
            personService.savePersonReg(registrationDto, madeUser);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public Role getSmallestRoleIdForUser(Long userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found for id: " + userId));

        List<Role> roles = userEntity.getRoles();

        // Use Comparator to find the role with the smallest ID
        return roles.stream()
                .min(Comparator.comparing(Role::getId))
                .orElseThrow(() -> new IllegalStateException("User has no roles"));
    }

    @Override
    public Role getHighestRole(){
        UserEntity user = new UserEntity();
        String email = SecurityUtil.getSessionUser();
        if(email != null) {
            user = findByEmail(email);
            List<Role> roles = user.getRoles();

            if (!roles.isEmpty()) {
                // Sort the roles based on their IDs in ascending order
                roles.sort(Comparator.comparingLong(Role::getId));

                // Return the role with the smallest ID (highest role)
                return roles.get(0);
            }
        }
        return null;
    }
}
