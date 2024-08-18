package estate.management.com.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
<<<<<<< HEAD
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
=======
public class BadRequestException extends RuntimeException{

    public BadRequestException(String message){
>>>>>>> origin/master
        super(message);
    }
}
