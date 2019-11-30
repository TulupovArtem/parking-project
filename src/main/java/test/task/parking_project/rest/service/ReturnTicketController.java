package test.task.parking_project.rest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import test.task.parking_project.domain.Parking;
import test.task.parking_project.domain.ParkingMeter;
import test.task.parking_project.domain.ticket.Ticket;
import test.task.parking_project.rest.service.value.ReturnTicketRequest;
import test.task.parking_project.validate.ReturnTicketValidator;

@RestController
public class ReturnTicketController {

    private Ticket ticket;

    private static final Logger logger = LoggerFactory.getLogger(ReturnTicketController.class);

    @RequestMapping(value = "/return_ticket",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Ticket returnTicket() {
        logger.info("return ticket");
        return ticket;
    }

    @RequestMapping(value = "/return_ticket",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Ticket returnTicket(@RequestBody ReturnTicketRequest request) {
        logger.info("ticket return process");
        Parking.initParking();
        ReturnTicketValidator.checkData(request, Parking.getParking());
        ParkingMeter.initParkingMeter(Parking.getParking());
        ticket = ParkingMeter.getParkingMeter().ticketReturn(request.getId());
        ReturnTicketValidator.checkData(ticket);
        return ticket;
    }
}
