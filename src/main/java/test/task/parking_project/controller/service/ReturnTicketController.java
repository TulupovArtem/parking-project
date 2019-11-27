package test.task.parking_project.controller.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.task.parking_project.parking.Parking;
import test.task.parking_project.parking.ParkingMeter;
import test.task.parking_project.parking.ticket.Ticket;

@RestController
public class ReturnTicketController {
    @RequestMapping("/return_ticket")
    public Ticket returnTicket(@RequestParam(value = "id") String id) {
        ParkingMeter.initParkingMeter(Parking.getParking());
        ParkingMeter.getParkingMeter().ticketReturn(id);
        return ParkingMeter.getParkingMeter().searchTicket(id);
    }
}
