package test.task.parking_project.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import test.task.parking_project.exception.InvalidNumOrModException;

@ControllerAdvice
public class InvalidNumOrModExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidNumOrModException.class)
    protected ResponseEntity<InvalidNumOrModEx> handleInvalidNumOrModException() {
        return new ResponseEntity<InvalidNumOrModEx>(new InvalidNumOrModEx("Invalid number or model"), HttpStatus.NOT_FOUND);
    }

    private static class InvalidNumOrModEx {
        private String message;

        public InvalidNumOrModEx() {
        }

        public InvalidNumOrModEx(String message) {
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
