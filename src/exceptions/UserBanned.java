package exceptions;

public class UserBanned extends Exception {

    private static final long serialVersionUID = 1050003L;


    public UserBanned(String message ){ super(message); }
    public UserBanned(Throwable cause ){super(cause);}
    public UserBanned(String message, Throwable cause ){ super ("++MEX: " + message + "++CAUSE: " + cause); }

}
