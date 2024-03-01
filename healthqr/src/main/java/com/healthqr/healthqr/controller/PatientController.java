package com.healthqr.healthqr.controller;

import com.healthqr.healthqr.dto.PatientDto;
import com.healthqr.healthqr.models.UserEntity;
import com.healthqr.healthqr.security.SecurityUtil;
import com.healthqr.healthqr.services.PatientService;
import com.healthqr.healthqr.services.TreatmentService;
import com.healthqr.healthqr.services.UserService;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PatientController {
    private TreatmentService treatmentService;
    private UserService userService;

    private PatientService patientService;
    @Autowired
    public PatientController(TreatmentService treatmentService, UserService userService,PatientService patientService) {

        this.treatmentService = treatmentService;
        this.userService = userService;
        this.patientService = patientService;
    }

    @GetMapping("/patients")
    public String listPatients(Model model){
        UserEntity user = new UserEntity();
        List<PatientDto> patients = patientService.findAllPatient();
        String email = SecurityUtil.getSessionUser();
        if(email != null) {
            user = userService.findByEmail(email);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("patients", patients);
        return "patients-list";
    }

    @GetMapping("/patients/{patientId}/update")
    public String updatePatientForm(@PathVariable("patientId") Long patientId, Model model) {
        PatientDto patient = patientService.findPatientById(patientId);
        model.addAttribute("patient", patient);
        return "patients-update";
    }

    @PostMapping("/patients/{patientId}/update")
    public String updatePatient(@PathVariable("patientId") Long patientId,
                                  @Valid @ModelAttribute("patient") PatientDto patient,
                                  @NotNull BindingResult result, Model model) {
        if(result.hasErrors()) {
            patient.setPersonId(patientId);
            model.addAttribute("patient", patient);
            return "patients-update";
        }
        patient.setPersonId(patientId);
        patientService.updatePatient(patient);
        return "redirect:/patients";
    }

    @GetMapping("/patients/{patientId}/details")
    public String patientDetail(@PathVariable("patientId") long patientId, Model model) {

        PatientDto patientDto = patientService.findPatientById(patientId);
        UserEntity user = new UserEntity();
        String email = SecurityUtil.getSessionUser();
        if(email != null) {
            user = userService.findByEmail(email);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("patient", patientDto);
        return "patients-details";
    }

    @GetMapping("/patients/{patientId}/delete")
    public String deletePatient(@PathVariable("patientId")Long patientId) {
        patientService.deletePatient(patientId);
        return "redirect:/patients";
    }

    @GetMapping("/patients/search")
    public String searchTreatment(@RequestParam(value = "query") String query, Model model) {
        UserEntity user = new UserEntity();
        List<PatientDto> patients = patientService.searchPatients(query);
        String email = SecurityUtil.getSessionUser();
        if(email != null) {
            user = userService.findByEmail(email);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("patients", patients);
        return "patients-list";
    }
}
