package exceptions;

public class NickNotDBEx extends Exception{

    private static final long serialVersionUID = 1000000L;

    public NickNotDBEx(String message ){ super(message); }
    public NickNotDBEx(Throwable cause ){super(cause);}
    public NickNotDBEx(String message, Throwable cause ){ super ("++MEX: " + message + "++CAUSE: " + cause); }

}
