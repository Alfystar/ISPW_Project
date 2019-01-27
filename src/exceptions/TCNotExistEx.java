package exceptions;

public class TCNotExistEx extends Exception{

    private static final long serialVersionUID = 1000002L;

    public TCNotExistEx(String message ){ super(message); }
    public TCNotExistEx(Throwable cause ){super(cause);}
    public TCNotExistEx(String message, Throwable cause ){ super ("++MEX: " + message + "++CAUSE: " + cause); }

}
