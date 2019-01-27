package exceptions;

public class UserNotExistEx extends Exception{

    private static final long serialVersionUID = 1000003L;

    public UserNotExistEx(String message ){ super(message); }
    public UserNotExistEx(Throwable cause ){super(cause);}
    public UserNotExistEx(String message, Throwable cause ){ super ("++MEX: " + message + "++CAUSE: " + cause); }

}
