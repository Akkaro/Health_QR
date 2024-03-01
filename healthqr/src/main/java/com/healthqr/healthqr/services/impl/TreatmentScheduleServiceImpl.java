package com.healthqr.healthqr.services.impl;

import com.healthqr.healthqr.dto.TreatmentScheduleDto;
import com.healthqr.healthqr.models.Treatment;
import com.healthqr.healthqr.models.TreatmentSchedule;
import com.healthqr.healthqr.repository.TreatmentRepository;
import com.healthqr.healthqr.repository.TreatmentScheduleRepository;
import com.healthqr.healthqr.services.TreatmentScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.healthqr.healthqr.mapper.TreatmentScheduleMapper.mapToTreatmentSchedule;
import static com.healthqr.healthqr.mapper.TreatmentScheduleMapper.mapToTreatmentScheduleDto;

@Service
public class TreatmentScheduleServiceImpl implements TreatmentScheduleService {
    private TreatmentScheduleRepository treatmentScheduleRepository;
    private TreatmentRepository treatmentRepository;

    @Autowired
    public TreatmentScheduleServiceImpl(TreatmentScheduleRepository treatmentScheduleRepository, TreatmentRepository treatmentRepository) {
        this.treatmentScheduleRepository = treatmentScheduleRepository;
        this.treatmentRepository = treatmentRepository;
    }

    @Override
    public void createTreatmentSchedule(Long treatmentId, TreatmentScheduleDto treatmentScheduleDto) {
        Treatment treatment = treatmentRepository.findById(treatmentId).get();
        TreatmentSchedule treatmentSchedule = mapToTreatmentSchedule(treatmentScheduleDto);
        treatmentSchedule.setTreatment(treatment);
        treatmentScheduleRepository.save(treatmentSchedule);
    }

    @Override
    public List<TreatmentScheduleDto> findAllTreatmentSchedules() {
        List<TreatmentSchedule> treatments = treatmentScheduleRepository.findAll();
        return treatments.stream().map(treatment -> mapToTreatmentScheduleDto(treatment)).collect(Collectors.toList());
    }

    @Override
    public TreatmentScheduleDto findByTreatmentScheduleId(Long treatmentScheduleId) {
        TreatmentSchedule treatmentSchedule = treatmentScheduleRepository.findById(treatmentScheduleId).get();
        return mapToTreatmentScheduleDto(treatmentSchedule);
    }

    @Override
    public void updateTreatmentSchedule(TreatmentScheduleDto treatmentScheduleDto) {
        TreatmentSchedule treatmentSchedule = mapToTreatmentSchedule(treatmentScheduleDto);
        treatmentScheduleRepository.save(treatmentSchedule);
    }

    @Override
    public void deleteTreatmentSchedule(long treatmentScheduleId) {
        treatmentScheduleRepository.deleteById(treatmentScheduleId);
    }
}
