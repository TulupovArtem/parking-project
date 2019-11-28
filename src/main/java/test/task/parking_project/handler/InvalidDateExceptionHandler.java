package test.task.parking_project.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import test.task.parking_project.exception.InvalidDateException;

@ControllerAdvice
public class InvalidDateExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidDateException.class)
    protected ResponseEntity<InvalidDateEx> handleInvalidDateException() {
        return new ResponseEntity<InvalidDateEx>(new InvalidDateEx("Error: Invalid data"), HttpStatus.NOT_FOUND);
    }

    private static class InvalidDateEx {
        private String message;

        public InvalidDateEx() {
        }

        public InvalidDateEx(String message) {
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
