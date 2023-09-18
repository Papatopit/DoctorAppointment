package com.kolokolnin.kteLabs.service;


import com.kolokolnin.kteLabs.model.Patient;

import java.util.List;

public interface PatientService {

    Patient createPatient(Patient patient);

    Patient updatePatient(long id, Patient patient);

    void deletePatient(long id);

    Patient getPatientById(long id);

    List<Patient> getAllPatients();
}
