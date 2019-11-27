package test.task.parking_project.rest.report;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.task.parking_project.parking.ParkingMeter;

@RestController
public class GetAllPlacesController {
    @RequestMapping("/get_all_place")
    public Integer getAllPlace() {
        ParkingMeter.initParkingMeter();
        return ParkingMeter.getParkingMeterForReporting().freeParkingPlaceReport();
    }
}
