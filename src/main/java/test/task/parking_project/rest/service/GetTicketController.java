package test.task.parking_project.rest.service;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import test.task.parking_project.parking.Parking;
import test.task.parking_project.parking.ParkingMeter;
import test.task.parking_project.parking.car.Car;
import test.task.parking_project.parking.ticket.Ticket;

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
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Ticket addTicket(@RequestParam String number,
                            @RequestParam String model,
                            @RequestParam(value = "size", required=false, defaultValue="10") int size) {
        Parking.initParking(size);
        ParkingMeter.initParkingMeter(Parking.getParking());
        if (ParkingMeter.getParkingMeter().freeParkingPlaceReport() == 0) {
            return null;
        }
        ticket = ParkingMeter.getParkingMeter().gettingTicket(Parking.getParking(), new Car(model, number));
        return ticket;
    }
}