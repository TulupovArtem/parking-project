package test.task.parking_project.controller.report;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.task.parking_project.parking.Parking;
import test.task.parking_project.parking.ParkingMeter;

@RestController
public class GetAllPlacesController {
    @RequestMapping("/get_all_place")
    public Integer getAllPlace() {
        if (Parking.getParking() == null) {
            return null;
        }
        ParkingMeter.initParkingMeter(Parking.getParking());
        return ParkingMeter.getParkingMeter().freeParkingPlaceReport();
    }
}
