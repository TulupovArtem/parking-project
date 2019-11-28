package test.task.parking_project.rest.report;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import test.task.parking_project.parking.ParkingMeter;

import java.util.List;

@RestController
public class GetAllCarsController {
    @RequestMapping(value = "/get_all_cars",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<String> getAllCars() {
        ParkingMeter.initParkingMeter();
        return ParkingMeter.getParkingMeterForReporting().allCarsReport();
    }
}
