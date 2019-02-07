package exceptions;

public class DBConnectionEx extends Exception{

    public DBConnectionEx(String message ){ super(message); }
    public DBConnectionEx(Throwable cause ){super(cause);}
    public DBConnectionEx(String message, Throwable cause ){ super ("++MEX: " + message + "++CAUSE: " + cause); }
}
