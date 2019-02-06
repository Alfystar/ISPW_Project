package exceptions;

public class TCNotExistQEx extends Exception{

    private static final long serialVersionUID = 1000002L;

    public TCNotExistQEx(String message ){ super(message); }
    public TCNotExistQEx(Throwable cause ){super(cause);}
    public TCNotExistQEx(String message, Throwable cause ){ super ("++MEX: " + message + "++CAUSE: " + cause); }

}
