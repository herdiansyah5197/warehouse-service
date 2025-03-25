package id.co.warehouse.application.exception;

import id.co.warehouse.application.dto.error.ErrorDetail;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class MyBoostExceptionHandler {

    @ExceptionHandler(ErrorBussinessException.class)
    public ResponseEntity<Object> genericExceptionHandler(ErrorBussinessException e) {
        var response =  new ErrorDetail();
        response.setMassageError(e.getMessage());
        return new ResponseEntity<>(response , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GeneralErrorException.class)
    public ResponseEntity<Object> genericExceptionHandler(GeneralErrorException e) {
        var response =  new ErrorDetail();
        response.setMassageError(e.getMessage());
        return new ResponseEntity<>(response , HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
