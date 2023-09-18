package com.kolokolnin.kteLabs.controllers;

import com.kolokolnin.kteLabs.model.Ticket;
import com.kolokolnin.kteLabs.service.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tickets")
@Api(description = "Ticket Controller")
public class TicketController {

    private final TicketService service;

    @DeleteMapping("/{id}")
    @ApiOperation("Удаление существующего талона по id")
    public void deleteTicket(@PathVariable long id) {
        service.deleteTicket(id);
    }

    @GetMapping("/{id}")
    @ApiOperation("Получение талона по id")
    public Ticket getTicketById(@PathVariable long id) {
        return service.getTicketById(id);
    }

    @GetMapping
    @ApiOperation("Получение всех талонов")
    public List<Ticket> getAllTickets() {
        return service.getAllTickets();
    }

    @GetMapping("/patients/{id}")
    @ApiOperation("Получение всех талонов занятых одним пациентом по его id")
    public List<Ticket> getAllTicketsByPatientId(@PathVariable long id) {
        return service.getAllTicketsByPatientId(id);
    }

    @GetMapping("/doctors/{id}")
    @ApiOperation("Получение всех свободных талонов у доктора по его id")
    public List<Ticket> getAllTicketsByDoctorId(@PathVariable long id) {
        return service.getAllFreeTicketsByDoctorId(id);
    }

    @GetMapping("/reception_time_is_between")
    @ApiOperation("Получение всех талонов по времени между start и end")
    public List<Ticket> getAllTicketsByTimeBetween(@RequestParam
                                                   @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime start,
                                                   @RequestParam
                                                   @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime end) {
        return service.getAllTicketsByReceptionTimeIsBetween(start, end);
    }

    @PostMapping("/schedule/{doctorId}")
    @ApiOperation("Создание расписания на переданный день")
    public void createSchedule(@PathVariable long doctorId,
                               @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime start) {
        service.createSchedule(doctorId, start);
    }

    @PostMapping("/{patientId}/{couponId}")
    @ApiOperation("Бронирование купона пациентом")
    public Ticket takeTicketByPatient(@PathVariable long patientId, @PathVariable long ticketId) {
        return service.takeTicketByPatient(patientId, ticketId);
    }

}
