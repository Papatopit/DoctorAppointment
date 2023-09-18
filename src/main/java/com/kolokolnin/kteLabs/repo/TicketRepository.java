package com.kolokolnin.kteLabs.repo;

import com.kolokolnin.kteLabs.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {
    List<Ticket> findByPatientId(long patientId);

    List<Ticket> findByDoctorIdAndPatientIsNull(long doctorId);

    List<Ticket> findByReceptionStartTimeIsAfterAndReceptionEndTimeIsBefore(LocalDateTime start, LocalDateTime end);
}
