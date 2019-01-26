package exceptions;

public class UserNotInDBException extends Exception{

    private static final long serialVersionUID = 12345678L;

    public UserNotInDBException( String message ){ super(message); }
    public UserNotInDBException( Throwable cause ){}
    public UserNotInDBException( String message, Throwable cause ){}

}
