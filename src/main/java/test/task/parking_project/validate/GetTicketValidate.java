package test.task.parking_project.validate;

import test.task.parking_project.exception.InvalidDateException;
import test.task.parking_project.exception.NoFreePlaceException;
import test.task.parking_project.rest.service.value.AddTicketRequest;

public class GetTicketValidate {
    public static void checkFreeParkingPlaces(int countPlaces) {
        if (countPlaces == 0) {
            throw new NoFreePlaceException();
        }
    }

    public static void checkAddTicketRequest(AddTicketRequest request) {
        if (request == null || request.getModel() == null
                || request.getModel().isEmpty() || request.getNumber() == null
                || request.getNumber().isEmpty() || request.getSize() <= 0) {
            throw new InvalidDateException();
        }
    }
}
