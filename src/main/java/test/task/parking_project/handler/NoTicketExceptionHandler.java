package test.task.parking_project.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import test.task.parking_project.exception.NoTicketException;

@ControllerAdvice
public class NoTicketExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoTicketException.class)
    protected ResponseEntity<NoSuchTicketException> handleNoTicketException() {
        return new ResponseEntity<>(new NoSuchTicketException("No such ticket"), HttpStatus.NOT_FOUND);
    }

    private static class NoSuchTicketException {
        private String message;

        public NoSuchTicketException() {
        }

        public NoSuchTicketException(String message) {
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