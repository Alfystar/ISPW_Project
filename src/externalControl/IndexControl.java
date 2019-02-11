package externalControl;

import DAO.Config;
import DAO.DAOClass;
import DAO.DaemonDAO;
import control.FacadeSubSystem;
import interfaces.SystemInterface;

public class IndexControl{

    private static IndexControl instance;

    private SystemInterface sysInt = new FacadeSubSystem();

    private IndexControl(){
    }

    public static IndexControl getInstance(){
        if(instance == null)
            instance = new IndexControl();
        return instance;
    }

    public String startDBAndIP(){
        try {

            Config.setConfFilePath("/unnamed/config.properties");

            DaemonDAO daemonDAO = DaemonDAO.getInstance();

            String IPtest = sysInt.getLastHost();
            System.out.println("Controller: " + IPtest);
            return IPtest;
        } catch (Exception e){
            return e.getMessage();
        }
    }

    public String changeIPDAO(String IP){
        try {
            //sysInt.changeHost(IP);
            DAOClass daoClass = new DAOClass(IP);
            if (daoClass.testNet()) return "Successo";
            else return "Fallito";
        } catch(Exception e){
            return e.getMessage();
        }
    }
}
