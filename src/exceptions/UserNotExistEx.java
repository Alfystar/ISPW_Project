package exceptions;

public class UserNotExistEx extends Exception{

    public UserNotExistEx(String message ){ super(message); }
    public UserNotExistEx(Throwable cause ){super(cause);}
    public UserNotExistEx(String message, Throwable cause ){ super ("+++" + message + "+++" + cause); }

}
