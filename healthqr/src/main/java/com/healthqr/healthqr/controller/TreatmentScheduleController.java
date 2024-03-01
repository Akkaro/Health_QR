package com.healthqr.healthqr.controller;

import com.healthqr.healthqr.dto.TreatmentScheduleDto;
import com.healthqr.healthqr.models.TreatmentSchedule;
import com.healthqr.healthqr.models.UserEntity;
import com.healthqr.healthqr.security.SecurityUtil;
import com.healthqr.healthqr.services.TreatmentScheduleService;
import com.healthqr.healthqr.services.TreatmentService;
import com.healthqr.healthqr.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TreatmentScheduleController {

    private UserService userService;
    private TreatmentScheduleService treatmentScheduleService;
    private TreatmentService treatmentService;

    @Autowired
    public TreatmentScheduleController(TreatmentScheduleService treatmentScheduleService, TreatmentService treatmentService, UserService userService) {
        this.treatmentScheduleService = treatmentScheduleService;
        this.treatmentService = treatmentService;
        this.userService = userService;
    }


    @GetMapping("/treatmentSchedules")
    public String treatmentScheduleList(Model model) {
        UserEntity user = new UserEntity();
        List<TreatmentScheduleDto> treatmentSchedules = treatmentScheduleService.findAllTreatmentSchedules();
        String email = SecurityUtil.getSessionUser();
        if(email != null) {
            user = userService.findByEmail(email);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("treatmentSchedules", treatmentSchedules);
        return "treatmentSchedules-list";
    }

    @GetMapping("/treatmentSchedules/{treatmentScheduleId}/details")
    public String viewTreatmentSchedule(@PathVariable("treatmentScheduleId")Long treatmentScheduleId, Model model) {
        UserEntity user = new UserEntity();
        TreatmentScheduleDto treatmentScheduleDto = treatmentScheduleService.findByTreatmentScheduleId(treatmentScheduleId);
        String email = SecurityUtil.getSessionUser();
        if(email != null) {
            user = userService.findByEmail(email);
            model.addAttribute("user", user);
        }
        model.addAttribute("treatmentSchedules", treatmentScheduleDto.getTreatment());
        model.addAttribute("user", user);
        model.addAttribute("treatmentSchedule", treatmentScheduleDto);
        return "treatmentSchedules-detail";
    }

    @GetMapping("/treatmentSchedules/{treatmentId}/new")
    public String createTreatmentScheduleForm(@PathVariable("treatmentId") Long treatmentId, Model model) {
        TreatmentSchedule treatmentSchedule = new TreatmentSchedule();
        model.addAttribute("treatmentId", treatmentId);
        model.addAttribute("treatmentSchedule", treatmentSchedule);
        return "treatmentSchedules-create";
    }

    @GetMapping("/treatmentSchedules/{treatmentScheduleId}/edit")
    public String editEventForm(@PathVariable("treatmentScheduleId") Long treatmentScheduleId, Model model) {
        TreatmentScheduleDto treatmentSchedule = treatmentScheduleService.findByTreatmentScheduleId(treatmentScheduleId);
        model.addAttribute("treatmentSchedule", treatmentSchedule);
        return "treatmentSchedules-edit";
    }

    @PostMapping("/treatmentSchedules/{treatmentId}")
    public String createTreatmentSchedule(@PathVariable("treatmentId") Long treatmentId, @ModelAttribute("treatmentSchedule")
                        TreatmentScheduleDto treatmentScheduleDto,
                                          BindingResult result,
                                          Model model) {
        if(result.hasErrors()) {
            model.addAttribute("treatmentSchedule", treatmentScheduleDto);
            return "treatments-create";
        }
        treatmentScheduleService.createTreatmentSchedule(treatmentId, treatmentScheduleDto);
        return "redirect:/treatments/" + treatmentId + "/details";

    }

    @PostMapping("/treatmentSchedules/{treatmentScheduleId}/edit")
    public String updatetreatmentSchedule(@PathVariable("treatmentScheduleId") Long treatmentScheduleId,
                              @Valid @ModelAttribute("treatmentSchedule") TreatmentScheduleDto treatmentSchedule,
                              BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("treatmentSchedule", treatmentSchedule);
            return "treatmentSchedules-edit";
        }
        TreatmentScheduleDto treatmentScheduleDto = treatmentScheduleService.findByTreatmentScheduleId(treatmentScheduleId);
        treatmentSchedule.setTreatmentScheduleId(treatmentScheduleId);
        treatmentSchedule.setTreatment(treatmentScheduleDto.getTreatment());
        treatmentScheduleService.updateTreatmentSchedule(treatmentSchedule);
        return "redirect:/treatments/" + treatmentSchedule.getTreatment().getTreatmentId() + "/details";
    }

    @GetMapping("/treatmentSchedules/{treatmentScheduleId}/delete")
    public String deletetreatmentSchedule(@PathVariable("treatmentScheduleId") long treatmentScheduleId) {
        treatmentScheduleService.deleteTreatmentSchedule(treatmentScheduleId);
        return "redirect:/treatmentSchedules";
    }
}
