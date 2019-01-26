package exceptions;

public class NickNotQEx extends Exception{

    public NickNotQEx(String message ){ super(message); }
    public NickNotQEx(Throwable cause ){super(cause);}
    public NickNotQEx(String message, Throwable cause ){ super ("+++" + message + "+++" + cause); }

}
