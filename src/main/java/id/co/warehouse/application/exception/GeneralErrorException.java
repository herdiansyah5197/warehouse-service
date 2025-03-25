package id.co.warehouse.application.exception;

public class GeneralErrorException extends RuntimeException{

    public GeneralErrorException(String message){
        super(message);
    }
}
