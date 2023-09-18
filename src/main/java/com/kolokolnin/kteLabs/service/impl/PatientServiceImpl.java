package com.kolokolnin.kteLabs.service.impl;

import com.kolokolnin.kteLabs.exceptions.NotFoundException;
import com.kolokolnin.kteLabs.model.Patient;
import com.kolokolnin.kteLabs.repo.PatientRepository;
import com.kolokolnin.kteLabs.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient updatePatient(long id, Patient patient) {
        Patient oldPatient = getPatientById(id);
        if (patient.getFirstName() != null) {
            oldPatient.setFirstName(patient.getFirstName());
        }
        if (patient.getLastName() != null) {
            oldPatient.setLastName(patient.getLastName());
        }
        if (patient.getDateOfBirth() != null) {
            oldPatient.setDateOfBirth(patient.getDateOfBirth());
        }
        return patientRepository.save(oldPatient);
    }

    @Override
    public void deletePatient(long id) {
        if (!patientRepository.existsById(id)) {
            throw new NotFoundException("Patient not found");
        }
        patientRepository.deleteById(id);
    }

    @Override
    public Patient getPatientById(long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Patient not found"));
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
}
