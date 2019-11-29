package test.task.parking_project.validate;

import test.task.parking_project.exception.InvalidDateException;
import test.task.parking_project.exception.InvalidNumOrModException;
import test.task.parking_project.exception.NoFreePlaceException;
import test.task.parking_project.rest.service.value.AddTicketRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetTicketValidate {
    private static Pattern pattern;
    private static Matcher matcher;

    private static final String NUMBER_CAR_PATTERN = "^[A-Z0-9]{5,15}$";
    private static final String MODEL_CAR_PATTERN = "^[A-Za-z0-9]+\\-?[A-Za-z0-9]+\\-?[A-Za-z0-9]+$";

    private static boolean carNumberAndModelValidator(AddTicketRequest request) {
        pattern = Pattern.compile(NUMBER_CAR_PATTERN);
        matcher = pattern.matcher(request.getNumber());
        if (!matcher.matches()) {
            return false;
        }
        pattern = Pattern.compile(MODEL_CAR_PATTERN);
        matcher = pattern.matcher(request.getModel());
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }

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
        if (!carNumberAndModelValidator(request)) {
            throw new InvalidNumOrModException();
        }
    }
}
