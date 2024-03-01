package com.healthqr.healthqr.controller;

import com.healthqr.healthqr.dto.RegistrationDto;
import com.healthqr.healthqr.models.Role;
import com.healthqr.healthqr.models.UserEntity;
import com.healthqr.healthqr.repository.PatientRepository;
import com.healthqr.healthqr.security.SecurityUtil;
import com.healthqr.healthqr.services.PatientService;
import com.healthqr.healthqr.services.TreatmentService;
import com.healthqr.healthqr.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private UserService userService;
    private PatientService patientService;
    private TreatmentService treatmentService;
    private PatientRepository patientRepository;

    public AuthController(UserService userService, PatientRepository patientRepository, PatientService patientService, TreatmentService treatmentService) {
        this.userService = userService;
        this.patientRepository = patientRepository;
        this.treatmentService = treatmentService;
        this.patientService = patientService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

//    @PostMapping("/login")
//    public String login() {
//        if(userService.getHighestRole().equals("PATIENT")){
//            String email = SecurityUtil.getSessionUser();
//            PatientDto patient = patientService.findPatientByEmail(email);
//            TreatmentDto treatmentDto = treatmentService.findTreatmentByPatientId(patient.getPersonId());
//            Long treatmentId = treatmentDto.getTreatmentId();
//            return "redirect:/treatments/" + treatmentId + "/details";
//        }
//        return "redirect:/treatments";
//    }

    @GetMapping("/register")
    public String getRegisterForm(Model model){
        UserEntity authUser = new UserEntity();
        String email = SecurityUtil.getSessionUser();
        if(email != null) {
            authUser = userService.findByEmail(email);
            Role smallestRole = userService.getSmallestRoleIdForUser(authUser.getId());
            if(smallestRole.getName().equals("PATIENT")) {
                return "access-denied";
            }
        }

        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user")RegistrationDto user,
                           BindingResult result, Model model) {
        UserEntity existingUserEmail = userService.findByEmail(user.getEmail());
        if(existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()) {
            return "redirect:/register?fail";
        }

        if(result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }

        userService.saveUser(user);
        return "redirect:/treatments?success";
    }

    @GetMapping("/register/doctor")
    public String getRegisterDoctorForm(Model model){
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "register-doctor";
    }

    @PostMapping("/register/doctor/save")
    public String registerDoctor(@Valid @ModelAttribute("user")RegistrationDto user,
                           BindingResult result, Model model) {
        UserEntity existingUserEmail = userService.findByEmail(user.getEmail());
        if(existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()) {
            return "redirect:/register?fail";
        }

        if(result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }

        userService.saveUser(user);
        return "redirect:/treatments?success";
    }
}
