package exceptions;

public class UserBannedEx extends Exception {

    private static final long serialVersionUID = 1050003L;


    public UserBannedEx(String message ){ super(message); }
    public UserBannedEx(Throwable cause ){super(cause);}
    public UserBannedEx(String message, Throwable cause ){ super ("++MEX: " + message + "++CAUSE: " + cause); }

}
