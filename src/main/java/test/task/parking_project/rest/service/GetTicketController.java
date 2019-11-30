package test.task.parking_project.rest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import test.task.parking_project.database.save.Saver;
import test.task.parking_project.domain.Parking;
import test.task.parking_project.domain.ParkingMeter;
import test.task.parking_project.domain.car.Car;
import test.task.parking_project.domain.ticket.Ticket;
import test.task.parking_project.rest.service.value.AddTicketRequest;
import test.task.parking_project.validate.GetTicketValidate;

@RestController
public class GetTicketController {

    private Ticket ticket;

    private static final Logger logger = LoggerFactory.getLogger(GetTicketController.class);

    @RequestMapping(value = "/get_ticket",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Ticket getTicket() {
        logger.info("get ticket");
        return ticket;
    }

    @RequestMapping(value = "/get_ticket",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Ticket addTicket(@RequestBody AddTicketRequest request) {
        logger.info("ticket process");
        Parking.initParking(request.getSize());
        GetTicketValidate.checkAddTicketRequest(request);
        ParkingMeter.initParkingMeter(Parking.getParking());
        GetTicketValidate.checkFreeParkingPlaces(ParkingMeter.getParkingMeter().freeParkingPlaceReport());
        ticket = ParkingMeter.getParkingMeter().gettingTicket(Parking.getParking(),
                new Car(request.getModel(), request.getNumber()));
        return ticket;
    }
}