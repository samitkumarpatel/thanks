package api.thanks.teamapi.exception;

public class TeamNotFoundException extends RuntimeException {

    public TeamNotFoundException(String msg){
        super(msg);
    }
}
