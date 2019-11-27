package test.task.parking_project.controller.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.task.parking_project.parking.Parking;
import test.task.parking_project.parking.ParkingMeter;
import test.task.parking_project.parking.car.Car;
import test.task.parking_project.parking.ticket.Ticket;

@RestController
public class GetTicketController {

    @RequestMapping("/get_ticket")
    public Ticket getTicket(@RequestParam(value = "size", required=false, defaultValue="10") int size,
                            @RequestParam(value = "number") String number,
                            @RequestParam(value = "model") String model) {
        Parking.initParking(size);
        ParkingMeter.initParkingMeter(Parking.getParking());
        if (ParkingMeter.getParkingMeter().freeParkingPlaceReport() == 0) {
            return null;
        }
        return ParkingMeter.getParkingMeter().gettingTicket(Parking.getParking(), new Car(model, number));
    }
}