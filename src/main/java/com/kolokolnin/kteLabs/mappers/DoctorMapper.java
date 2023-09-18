package com.kolokolnin.kteLabs.mappers;

import com.kolokolnin.kteLabs.dto.DoctorDto;
import com.kolokolnin.kteLabs.model.Doctor;
import org.springframework.stereotype.Component;


@Component
public class DoctorMapper {

    public Doctor toDoctor(DoctorDto doctorDto) {
        return Doctor.builder()
                .firstName(doctorDto.getFirstName())
                .lastName(doctorDto.getLastName())
                .build();
    }
}
