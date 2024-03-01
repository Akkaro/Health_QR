package com.healthqr.healthqr.controller;

//import com.healthqr.healthqr.dto.TreatmentDto;
//import com.healthqr.healthqr.models.Treatment;
//import com.healthqr.healthqr.services.TreatmentService;
//import jakarta.validation.Valid;
//import jakarta.validation.constraints.NotNull;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import java.util.List;

import com.healthqr.healthqr.dto.TreatmentDto;
import com.healthqr.healthqr.models.Treatment;
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
public class TreatmentController {
    private TreatmentService treatmentService;
    private UserService userService;
    private PatientService patientService;
    @Autowired
    public TreatmentController(TreatmentService treatmentService, UserService userService, PatientService patientService) {

        this.treatmentService = treatmentService;
        this.userService = userService;
        this.patientService = patientService;
    }

    @GetMapping("/treatments")
    public String listTreatments(Model model){
        UserEntity user = new UserEntity();
        List<TreatmentDto> treatments = treatmentService.findAllTreatment();
        String email = SecurityUtil.getSessionUser();
        if(email != null) {
            user = userService.findByEmail(email);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("treatments", treatments);
        return "treatments-list";
    }

    @GetMapping("/treatments/new")
    public String createTreatmentForm(Model model) {
        Treatment treatment = new Treatment();
        model.addAttribute("treatment", treatment);
        return "treatments-create";
    }


    @PostMapping("/treatments/new")
    public String saveTreatment(@Valid @ModelAttribute("treatment") TreatmentDto treatmentDto, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("treatment", treatmentDto);
            return "treatments-create";
        }
        treatmentService.saveTreatment(treatmentDto);
        return "redirect:/treatments";
    }

    @GetMapping("/treatments/{treatmentId}/update")
    public String updateTreatmentForm(@PathVariable("treatmentId") Long treatmentId, Model model) {
        TreatmentDto treatment = treatmentService.findTreatmentById(treatmentId);
        model.addAttribute("treatment", treatment);
        return "treatments-update";
    }

    @PostMapping("/treatments/{treatmentId}/update")
    public String updateTreatment(@PathVariable("treatmentId") Long treatmentId,
                                  @Valid @ModelAttribute("treatment") TreatmentDto treatment,
                                  @NotNull BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("treatment", treatment);
            return "treatments-update";
        }
        treatment.setTreatmentId(treatmentId);
        treatmentService.updateTreatment(treatment);
        return "redirect:/treatments";
    }

    @GetMapping("/treatments/{treatmentId}/details")
    public String treatmentDetail(@PathVariable("treatmentId") long treatmentId, Model model) {
        UserEntity user = new UserEntity();
        TreatmentDto treatmentDto = treatmentService.findTreatmentById(treatmentId);
        String email = SecurityUtil.getSessionUser();
        if(email != null) {
            user = userService.findByEmail(email);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("treatment", treatmentDto);
        return "treatments-details";
    }

    @GetMapping("/treatments/{treatmentId}/delete")
    public String deleteTreatment(@PathVariable("treatmentId")Long treatmentId) {
        treatmentService.deleteTreatment(treatmentId);
        return "redirect:/treatments";
    }

    @GetMapping("/treatments/search")
    public String searchTreatment(@RequestParam(value = "query") String query, Model model) {
        UserEntity user = new UserEntity();
        List<TreatmentDto> treatments = treatmentService.searchTreatments(query);
        String email = SecurityUtil.getSessionUser();
        if(email != null) {
            user = userService.findByEmail(email);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("treatments", treatments);
        return "treatments-list";
    }
}
