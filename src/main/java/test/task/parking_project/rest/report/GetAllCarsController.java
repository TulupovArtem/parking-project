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
public class GetAllCarsController {
    private static final Logger logger = LoggerFactory.getLogger(GetAllCarsController.class);

    @RequestMapping(value = "/get_all_cars",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<String> getAllCars() {
        logger.info("process report about all cars");
        ParkingMeter.initParkingMeter();
        return ParkingMeter.getParkingMeterForReporting().allCarsReport();
    }
}
