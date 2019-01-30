package DAO;

public class DaemonDAO implements Runnable{

    private static DaemonDAO ourInstance = new DaemonDAO();

    public static DaemonDAO getInstance() {
        return ourInstance;
    }

    private DaemonDAO() {
    }

    @Override
    public void run(){

    }
}
