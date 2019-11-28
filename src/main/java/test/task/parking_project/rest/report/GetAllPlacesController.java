package test.task.parking_project.rest.report;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import test.task.parking_project.parking.ParkingMeter;

@RestController
public class GetAllPlacesController {
    @RequestMapping(value = "/get_all_place",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Integer getAllPlace() {
        ParkingMeter.initParkingMeter();
        System.out.println(ParkingMeter.getParkingMeterForReporting());
        return ParkingMeter.getParkingMeterForReporting().freeParkingPlaceReport();
    }
}
