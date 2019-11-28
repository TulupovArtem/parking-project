package test.task.parking_project.rest.service;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import test.task.parking_project.exception.InvalidDateException;
import test.task.parking_project.exception.NoFreePlaceException;
import test.task.parking_project.parking.Parking;
import test.task.parking_project.parking.ParkingMeter;
import test.task.parking_project.parking.car.Car;
import test.task.parking_project.parking.ticket.Ticket;
import test.task.parking_project.rest.service.value.AddTicketRequest;
import test.task.parking_project.validate.GetTicketValidate;

@RestController
public class GetTicketController {

    private Ticket ticket;

    @RequestMapping(value = "/get_ticket",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Ticket getTicket() {
        return ticket;
    }

    @RequestMapping(value = "/get_ticket",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Ticket addTicket(@RequestBody AddTicketRequest request) {
        Parking.initParking(request.getSize());
        GetTicketValidate.checkAddTicketRequest(request);
        ParkingMeter.initParkingMeter(Parking.getParking());
        GetTicketValidate.checkFreeParkingPlaces(ParkingMeter.getParkingMeter().freeParkingPlaceReport());
        ticket = ParkingMeter.getParkingMeter().gettingTicket(Parking.getParking(),
                new Car(request.getModel(), request.getNumber()));
        return ticket;
    }
}