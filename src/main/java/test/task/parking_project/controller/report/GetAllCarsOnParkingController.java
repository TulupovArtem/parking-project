package test.task.parking_project.controller.report;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.task.parking_project.parking.Parking;
import test.task.parking_project.parking.ParkingMeter;

import java.util.List;

@RestController
public class GetAllCarsOnParkingController {
    @RequestMapping("/get_all_cars_on_parking")
    public List<String> getAllCarsOnParking() {
        if (Parking.getParking() == null) {
            return null;
        }
        ParkingMeter.initParkingMeter(Parking.getParking());
        return ParkingMeter.getParkingMeter().carsOnParkingReport();
    }
}
