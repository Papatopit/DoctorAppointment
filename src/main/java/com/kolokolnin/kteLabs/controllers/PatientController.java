package com.kolokolnin.kteLabs.controllers;

import com.kolokolnin.kteLabs.dto.PatientDto;
import com.kolokolnin.kteLabs.mappers.PatientMapper;
import com.kolokolnin.kteLabs.model.Patient;
import com.kolokolnin.kteLabs.service.PatientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/patients")
@Api(description = "Patient Controller")
public class PatientController {

    private final PatientService service;
    private final PatientMapper mapper;

    @PostMapping
    @ApiOperation("Добавление нового пациента")
    public Patient createPatient(@RequestBody PatientDto patientDto) {
        return service.createPatient(mapper.toPatient(patientDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Удаление существующего пациента по id")
    public void deletePatient(@PathVariable long id) {
        service.deletePatient(id);
    }

    @PatchMapping("/{id}")
    @ApiOperation("Обновление существующего пациента по id")
    public Patient updatePatient(@PathVariable long id, @RequestBody PatientDto patientDto) {
        return service.updatePatient(id, mapper.toPatient(patientDto));
    }

    @GetMapping("/{id}")
    @ApiOperation("Получение пациента по id")
    public Patient getPatientById(@PathVariable long id) {
        return service.getPatientById(id);
    }

    @GetMapping
    @ApiOperation("Получение всех пациентов")
    public List<Patient> getAllPatients() {
        return service.getAllPatients();
    }
}
