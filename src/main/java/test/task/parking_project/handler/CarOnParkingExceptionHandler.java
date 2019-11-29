package test.task.parking_project.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import test.task.parking_project.exception.CarOnParkingException;

@ControllerAdvice
public class CarOnParkingExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CarOnParkingException.class)
    protected ResponseEntity<CarOnParkingEx> handleCarOnParkingException() {
        return new ResponseEntity<>(new CarOnParkingEx("The car have already become on the parking"),
                HttpStatus.NOT_FOUND);
    }

    private static class CarOnParkingEx {
        private String message;

        public CarOnParkingEx() {
        }

        public CarOnParkingEx(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
