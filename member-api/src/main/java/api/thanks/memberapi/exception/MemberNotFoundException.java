package api.thanks.memberapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(String s){
        super(s);
    }
}
