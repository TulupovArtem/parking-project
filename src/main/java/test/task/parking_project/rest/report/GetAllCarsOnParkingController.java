package test.task.parking_project.rest.report;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.task.parking_project.parking.ParkingMeter;

import java.util.List;

@RestController
public class GetAllCarsOnParkingController {
    @RequestMapping("/get_all_cars_on_parking")
    public List<String> getAllCarsOnParking() {
        ParkingMeter.initParkingMeter();
        return ParkingMeter.getParkingMeterForReporting().carsOnParkingReport();
    }
}
