package test.task.parking_project.rest.report;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import test.task.parking_project.domain.ParkingMeter;
import test.task.parking_project.rest.service.GetTicketController;

import java.util.List;

@RestController
public class GetAllCarsOnParkingController {
    private static final Logger logger = LoggerFactory.getLogger(GetAllCarsOnParkingController.class);

    @RequestMapping(value = "/get_all_cars_on_parking",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<String> getAllCarsOnParking() {
        logger.info("process reporting about all cars on parking");
        ParkingMeter.initParkingMeter();
        return ParkingMeter.getParkingMeterForReporting().carsOnParkingReport();
    }
}
