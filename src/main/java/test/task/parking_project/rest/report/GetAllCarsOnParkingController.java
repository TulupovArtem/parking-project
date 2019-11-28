package test.task.parking_project.rest.report;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import test.task.parking_project.parking.ParkingMeter;

import java.util.List;

@RestController
public class GetAllCarsOnParkingController {
    @RequestMapping(value = "/get_all_cars_on_parking",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<String> getAllCarsOnParking() {
        ParkingMeter.initParkingMeter();
        return ParkingMeter.getParkingMeterForReporting().carsOnParkingReport();
    }
}
