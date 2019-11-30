package test.task.parking_project.validate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.task.parking_project.database.save.Saver;
import test.task.parking_project.exception.InvalidDateException;
import test.task.parking_project.exception.NoTicketException;
import test.task.parking_project.exception.NotInitParkingException;
import test.task.parking_project.domain.Parking;
import test.task.parking_project.domain.ticket.Ticket;
import test.task.parking_project.rest.service.value.ReturnTicketRequest;

public class ReturnTicketValidator {
    private static final Logger logger = LoggerFactory.getLogger(ReturnTicketValidator.class);

    public static void checkData(ReturnTicketRequest request, Parking parking) {
        if (parking == null) {
            logger.warn("Invalid data!");
            throw new NotInitParkingException();
        }
        if (request == null || request.getId().isEmpty() || request.getId() == null) {
            logger.warn("Invalid data!");
            throw new InvalidDateException();
        }
    }

    public static void checkData(Ticket ticket) {
        if (ticket == null) {
            logger.warn("No ticket");
            throw new NoTicketException();
        }
    }
}
