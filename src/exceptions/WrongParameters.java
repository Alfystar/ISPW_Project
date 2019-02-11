package exceptions;

public class WrongParameters extends Exception{
    private static final long serialVersionUID = 1000004L;

    public WrongParameters(String message){ super(message); }

    public WrongParameters(Throwable cause){super(cause);}

    public WrongParameters(String message, Throwable cause){ super("++MEX: " + message + "++CAUSE: " + cause); }
}
