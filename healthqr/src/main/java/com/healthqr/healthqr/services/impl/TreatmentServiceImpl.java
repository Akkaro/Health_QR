package com.healthqr.healthqr.services.impl;

import com.healthqr.healthqr.dto.TreatmentDto;
import com.healthqr.healthqr.models.Treatment;
import com.healthqr.healthqr.models.UserEntity;
import com.healthqr.healthqr.repository.TreatmentRepository;
import com.healthqr.healthqr.repository.UserRepository;
import com.healthqr.healthqr.security.SecurityUtil;
import com.healthqr.healthqr.services.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.healthqr.healthqr.mapper.TreatmentMapper.mapToTreatment;
import static com.healthqr.healthqr.mapper.TreatmentMapper.mapToTreatmentDto;

@Service
public class TreatmentServiceImpl implements TreatmentService {

    private TreatmentRepository treatmentRepository;
    private UserRepository userRepository;

    @Autowired
    public TreatmentServiceImpl(TreatmentRepository treatmentRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.treatmentRepository = treatmentRepository;
    }

    @Override
    public List<TreatmentDto> findAllTreatment() {
        List<Treatment> treatments = treatmentRepository.findAll();
        return treatments.stream().map((treatment) -> mapToTreatmentDto(treatment)).collect(Collectors.toList());
    }

    @Override
    public Treatment saveTreatment(TreatmentDto treatmentDto) {
        String email = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByEmail(email);
          Treatment treatment = mapToTreatment(treatmentDto);
          treatment.setCreatedBy(user);
        return treatmentRepository.save(treatment);
    }

    @Override
    public TreatmentDto findTreatmentById(Long treatmentId) {
        Treatment treatment = treatmentRepository.findById(treatmentId).get();
        return mapToTreatmentDto(treatment);
    }

    @Override
    public TreatmentDto findTreatmentByPatientId(Long patientId) {
        Optional<Treatment> optionalTreatment = treatmentRepository.findByPatientId(patientId);

        if (optionalTreatment.isPresent()) {
            Treatment treatment = optionalTreatment.get();
            return mapToTreatmentDto(treatment);
        } else {
            // Handle the case where treatment is not found (e.g., return null or throw an exception)
            return null;
        }
    }


    @Override
    public void updateTreatment(TreatmentDto treatmentDto) {
        String email = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByEmail(email);
        Treatment treatment = mapToTreatment(treatmentDto);
        treatment.setCreatedBy(user);
        treatmentRepository.save(treatment);
    }

    @Override
    public void deleteTreatment(Long treatmentId) {
        treatmentRepository.deleteById(treatmentId);
    }

    @Override
    public List<TreatmentDto> searchTreatments(String query) {
        List<Treatment> treatments = treatmentRepository.searchTreatments(query);
        return treatments.stream().map(treatment -> mapToTreatmentDto(treatment)).collect(Collectors.toList());
    }
}
