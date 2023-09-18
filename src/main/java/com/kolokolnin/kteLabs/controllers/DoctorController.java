package com.kolokolnin.kteLabs.controllers;

import com.kolokolnin.kteLabs.dto.DoctorDto;
import com.kolokolnin.kteLabs.mappers.DoctorMapper;
import com.kolokolnin.kteLabs.model.Doctor;
import com.kolokolnin.kteLabs.service.DoctorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/doctors")
@Api(description = "Doctor Controller")
public class DoctorController {

    private final DoctorService doctorService;
    private final DoctorMapper doctorMapper;

    @PostMapping
    @ApiOperation("Добавление нового доктора")
    public Doctor createDoctor(@RequestBody DoctorDto doctorDto) {
        return doctorService.createDoctor(doctorMapper.toDoctor(doctorDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Удаление существующего доктора по id")
    public void deleteDoctor(@PathVariable long id) {
        doctorService.deleteDoctor(id);
    }

    @PatchMapping("/{id}")
    @ApiOperation("Обновление существующего доктора по id")
    public Doctor updateDoctor(@PathVariable long id, @RequestBody DoctorDto doctorDto) {
        return doctorService.updateDoctor(id, doctorMapper.toDoctor(doctorDto));
    }

    @GetMapping("/{id}")
    @ApiOperation("Получение доктора по id")
    public Doctor getDoctorById(@PathVariable long id) {
        return doctorService.getDoctorById(id);
    }

    @GetMapping
    @ApiOperation("Получение всех докторов")
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }
}
