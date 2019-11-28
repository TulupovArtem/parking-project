package test.task.parking_project.rest.service;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import test.task.parking_project.parking.Parking;
import test.task.parking_project.parking.ParkingMeter;
import test.task.parking_project.parking.ticket.Ticket;
import test.task.parking_project.rest.service.value.ReturnTicketRequest;
import test.task.parking_project.validate.ReturnTicketValidator;

@RestController
public class ReturnTicketController {

    private Ticket ticket;

    @RequestMapping(value = "/return_ticket",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Ticket getTicket() {
        return ticket;
    }

    @RequestMapping(value = "/return_ticket",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Ticket returnTicket(@RequestBody ReturnTicketRequest request) {
        Parking.initParking();
        ReturnTicketValidator.checkData(request, Parking.getParking());
        ParkingMeter.initParkingMeter(Parking.getParking());
        ticket = ParkingMeter.getParkingMeter().ticketReturn(request.getId());
        ReturnTicketValidator.checkData(ticket);
        return ticket;
    }
}
