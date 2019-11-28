package test.task.parking_project.rest.service;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.task.parking_project.parking.Parking;
import test.task.parking_project.parking.ParkingMeter;
import test.task.parking_project.parking.ticket.Ticket;

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
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Ticket returnTicket(@RequestParam(value = "id") String id) {
        ParkingMeter.initParkingMeter(Parking.getParking());
        ParkingMeter.getParkingMeter().ticketReturn(id);
        ticket = ParkingMeter.getParkingMeter().searchTicket(id);
        return ticket;
    }
}
