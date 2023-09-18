package com.kolokolnin.kteLabs.service;


import com.kolokolnin.kteLabs.model.Doctor;

import java.util.List;

public interface DoctorService {

    Doctor createDoctor(Doctor doctor);

    Doctor updateDoctor(long id, Doctor doctor);

    void deleteDoctor(long id);

    Doctor getDoctorById(long id);

    List<Doctor> getAllDoctors();
}
