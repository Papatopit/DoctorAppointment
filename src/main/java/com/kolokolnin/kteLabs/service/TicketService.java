package com.kolokolnin.kteLabs.service;



import com.kolokolnin.kteLabs.model.Ticket;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketService {

    void deleteTicket(long id);

    Ticket getTicketById(long id);

    List<Ticket> getAllTickets();

    List<Ticket> getAllTicketsByPatientId(long id);

    List<Ticket> getAllFreeTicketsByDoctorId(long id);

    List<Ticket> getAllTicketsByReceptionTimeIsBetween(LocalDateTime start, LocalDateTime end);

    void createSchedule(long doctorId, LocalDateTime date);

    Ticket takeTicketByPatient(long patientId, long couponId);
}
