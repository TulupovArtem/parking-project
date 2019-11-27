package test.task.parking_project.controller.report;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.task.parking_project.parking.ParkingMeter;

import java.util.List;

@RestController
public class GetAllCarsController {
    @RequestMapping("/get_all_cars")
    public List<String> getAllCars() {
        ParkingMeter.initParkingMeter();
        return ParkingMeter.getParkingMeterForReporting().allCarsReport();
    }
}
