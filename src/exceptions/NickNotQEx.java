package exceptions;

public class NickNotQEx extends Exception{

    private static final long serialVersionUID = 1000001L;

    public NickNotQEx(String message){ super(message); }

    public NickNotQEx(Throwable cause){super(cause);}

    public NickNotQEx(String message, Throwable cause){ super("++MEX: " + message + "++CAUSE: " + cause); }

}
