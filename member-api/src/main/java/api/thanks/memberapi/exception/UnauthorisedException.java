package api.thanks.memberapi.exception;

public class UnauthorisedException extends RuntimeException {
    public UnauthorisedException(String msg){
        super(msg);
    }
}
