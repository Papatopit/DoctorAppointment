package com.kolokolnin.kteLabs.mappers;

import com.kolokolnin.kteLabs.dto.PatientDto;
import com.kolokolnin.kteLabs.model.Patient;
import org.springframework.stereotype.Component;


@Component
public class PatientMapper {

    public Patient toPatient(PatientDto patientDto) {
        return Patient.builder()
                .firstName(patientDto.getFirstName())
                .lastName(patientDto.getLastName())
                .dateOfBirth(patientDto.getDateOfBirth())
                .build();
    }
}
