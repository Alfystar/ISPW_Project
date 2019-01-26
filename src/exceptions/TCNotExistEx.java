package exceptions;

public class TCNotExistEx extends Exception{

    public TCNotExistEx(String message ){ super(message); }
    public TCNotExistEx(Throwable cause ){super(cause);}
    public TCNotExistEx(String message, Throwable cause ){ super ("+++" + message + "+++" + cause); }

}
