package com.kolokolnin.kteLabs.service.impl;

import com.kolokolnin.kteLabs.exceptions.ConflictException;
import com.kolokolnin.kteLabs.exceptions.NotFoundException;
import com.kolokolnin.kteLabs.model.Patient;
import com.kolokolnin.kteLabs.model.Ticket;
import com.kolokolnin.kteLabs.repo.DoctorRepository;
import com.kolokolnin.kteLabs.repo.PatientRepository;
import com.kolokolnin.kteLabs.repo.TicketRepository;
import com.kolokolnin.kteLabs.service.TicketService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Override
    public void deleteTicket(long id) {
        if (!ticketRepository.existsById(id)) {
            throw new NotFoundException("Ticket not found");
        }
        log.info("Delete by ID Ticket success");
        ticketRepository.deleteById(id);
    }

    @Override
    public Ticket getTicketById(long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ticket not found"));
    }

    @Override
    public List<Ticket> getAllTickets() {
        log.info("Find ALL Ticket success");
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> getAllTicketsByPatientId(long id) {
        if (!patientRepository.existsById(id)) {
            throw new NotFoundException("Patient not found");
        }
        log.info("Find ALL Ticket By Patient Id success");
        return ticketRepository.findByPatientId(id);
    }

    @Override
    public List<Ticket> getAllFreeTicketsByDoctorId(long id) {
        if (!doctorRepository.existsById(id)) {
            throw new NotFoundException("Doctor not found");
        }
        log.info("Find ALL free Ticket By Doctor Id success");
        return ticketRepository.findByDoctorIdAndPatientIsNull(id);
    }

    @Override
    public List<Ticket> getAllTicketsByReceptionTimeIsBetween(LocalDateTime start, LocalDateTime end) {
        log.info("get All Tickets By Reception Time Is Between {}, {}", start, end);
        return ticketRepository.findByReceptionStartTimeIsAfterAndReceptionEndTimeIsBefore(start.minusSeconds(1),
                end.plusSeconds(1));
    }

    @Override
    public void createSchedule(long doctorId, LocalDateTime date) {
        if (date.getHour() < 8 || date.getHour() > 17) {
            throw new ConflictException("Working hours from 8:00 to 17:00");
        }
        List<Ticket> tickets = new ArrayList<>();
        while (date.getHour() < 18) {
            tickets.add(Ticket.builder()
                    .doctor(doctorRepository.findById(doctorId)
                            .orElseThrow(() -> new NotFoundException("Doctor not found")))
                    .patient(null)
                    .receptionStartTime(date)
                    .receptionEndTime(date.plusMinutes(59))
                    .build());
            date = date.plusHours(1);
        }
        ticketRepository.saveAll(tickets);
    }

    @Override
    public Ticket takeTicketByPatient(long patientId, long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new NotFoundException("Ticket not found"));
        if (ticket.getPatient() != null) {
            throw new ConflictException("Ticket already taken");
        }
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new NotFoundException("Patient not found"));
        ticket.setPatient(patient);
        return ticketRepository.save(ticket);
    }
}
