package com.kolokolnin.kteLabs.endpoint;

import com.kolokolnin.kteLabs.exceptions.ScheduleNotCreatedException;
import com.kolokolnin.kteLabs.service.TicketService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ws.ktelabs.schedule.CreateScheduleRequest;
import ws.ktelabs.schedule.CreateScheduleResponse;

@Endpoint
public class ScheduleEndpoint {
    private static final String NAMESPACE_URI = "http://ktelabs.ws/schedule";

    private final TicketService ticketService;

    @Autowired
    public ScheduleEndpoint(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createScheduleRequest")
    @ResponsePayload
    public CreateScheduleResponse createScheduleResponse(@RequestPayload @NotNull @NotNull CreateScheduleRequest createScheduleRequest) {
        CreateScheduleResponse createScheduleResponse = new CreateScheduleResponse();
        try {
            ticketService.createSchedule(createScheduleRequest.getDoctorId(), createScheduleRequest
                    .getScheduleStartTime()
                    .toGregorianCalendar()
                    .toZonedDateTime()
                    .toLocalDateTime());
        } catch (ScheduleNotCreatedException scheduleNotCreatedException) {
            createScheduleResponse.setStatus(scheduleNotCreatedException.getMessage());
            return createScheduleResponse;
        }
        createScheduleResponse.setStatus("Успешно.");
        return createScheduleResponse;
    }
}
