package test.task.parking_project.rest.report;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import test.task.parking_project.domain.ParkingMeter;
import test.task.parking_project.rest.service.GetTicketController;

@RestController
public class GetAllPlacesController {
    private static final Logger logger = LoggerFactory.getLogger(GetAllPlacesController.class);

    @RequestMapping(value = "/get_all_place",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Integer getAllPlace() {
        logger.info("process report about all free places");
        ParkingMeter.initParkingMeter();
        return ParkingMeter.getParkingMeterForReporting().freeParkingPlaceReport();
    }
}
