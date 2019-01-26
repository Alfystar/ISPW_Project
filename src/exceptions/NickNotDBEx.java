package exceptions;

public class NickNotDBEx extends Exception{

    public NickNotDBEx(String message ){ super(message); }
    public NickNotDBEx(Throwable cause ){super(cause);}
    public NickNotDBEx(String message, Throwable cause ){ super ("+++" + message + "+++" + cause); }

}
