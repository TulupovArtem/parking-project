package test.task.parking_project.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import test.task.parking_project.exception.NoFreePlaceException;

@ControllerAdvice
public class NoFreePlaceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoFreePlaceException.class)
    protected ResponseEntity<NonFreePlaceException> handleNoFreePlaceException() {
        return new ResponseEntity<>(new NonFreePlaceException("There are no places!"), HttpStatus.NOT_FOUND);
    }

    private static class NonFreePlaceException {
        private String message;

        public NonFreePlaceException() {
        }

        public NonFreePlaceException(String message) {
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
