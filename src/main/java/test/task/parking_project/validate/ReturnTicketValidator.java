package test.task.parking_project.validate;

import test.task.parking_project.exception.InvalidDateException;
import test.task.parking_project.exception.NoTicketException;
import test.task.parking_project.exception.NotInitParkingException;
import test.task.parking_project.parking.Parking;
import test.task.parking_project.parking.ticket.Ticket;
import test.task.parking_project.rest.service.value.ReturnTicketRequest;

public class ReturnTicketValidator {
    public static void checkData(ReturnTicketRequest request, Parking parking) {
        if (parking == null) {
            throw new NotInitParkingException();
        }
        if (request == null || request.getId().isEmpty() || request.getId() == null) {
            throw new InvalidDateException();
        }
    }

    public static void checkData(Ticket ticket) {
        if (ticket == null) {
            throw new NoTicketException();
        }
    }
}
