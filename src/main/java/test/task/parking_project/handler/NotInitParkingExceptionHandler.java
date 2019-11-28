package test.task.parking_project.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import test.task.parking_project.exception.NotInitParkingException;

@ControllerAdvice
public class NotInitParkingExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotInitParkingException.class)
    protected ResponseEntity<NotInitParkingEx> handleNotInitParkingException() {
        return new ResponseEntity<>(new NotInitParkingEx("Error: Parking does not exist"), HttpStatus.NOT_FOUND);
    }

    private static class NotInitParkingEx {
        private String message;

        public NotInitParkingEx() {
        }

        public NotInitParkingEx(String message) {
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
