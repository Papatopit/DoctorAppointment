package com.kolokolnin.kteLabs.service.impl;

import com.kolokolnin.kteLabs.exceptions.NotFoundException;
import com.kolokolnin.kteLabs.model.Doctor;
import com.kolokolnin.kteLabs.repo.DoctorRepository;
import com.kolokolnin.kteLabs.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    @Override
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor updateDoctor(long id, Doctor doctor) {
        Doctor oldDoctor = getDoctorById(id);
        if (doctor.getFirstName() != null) {
            oldDoctor.setFirstName(doctor.getFirstName());
        }
        if (doctor.getLastName() != null) {
            oldDoctor.setLastName(doctor.getLastName());
        }
        return doctorRepository.save(oldDoctor);
    }

    @Override
    public void deleteDoctor(long id) {
        if (!doctorRepository.existsById(id)) {
            throw new NotFoundException("Doctor not found");
        }
        doctorRepository.deleteById(id);
    }

    @Override
    public Doctor getDoctorById(long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Doctor not found"));
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
}
