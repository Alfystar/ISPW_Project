package DAO;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class DaemonDAO implements Runnable{

    private static DaemonDAO ourInstance = new DaemonDAO();
    private DAOClass dao;

    private DaemonDAO(){
        try{
            this.dao = new DAOClass();
            new Thread(this).start();

        }catch(ClassNotFoundException e){
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static DaemonDAO getInstance(){
        return ourInstance;
    }

    private static long getDateDiff(Date future, Date past, TimeUnit timeUnit){
        long diffInMillies = future.getTime() - past.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    @Override
    public void run(){
        while(true){
            //codice ripetuto sempre finchè con delta che diminuiscono via via, finchè non si supera la data prevista
            restartSleep:
            {
                try{
                    Thread.sleep(1000);
                    Date todayDay = GregorianCalendar.getInstance().getTime();
                    GregorianCalendar gcFutureDate = this.dao.nextDeleteSession();
                    System.out.println("DaemonDAO next delete is: " + gregCalToString(gcFutureDate));

                    GregorianCalendar gc = new GregorianCalendar(2000, 01, 01);    //serve solo a generare un oggetto
                    gc.setTime(GregorianCalendar.getInstance().getTime());
                    System.out.println("DaemonDAO today is is: " + gregCalToString(gc));

                    long deltaTime = getDateDiff(gcFutureDate.getTime(), todayDay, TimeUnit.MILLISECONDS);
                    System.out.println("DaemonDAO go to sleap for: " + deltaTime);
                    if(deltaTime <= 0) break restartSleep;
                    Thread.sleep(deltaTime);
                    break restartSleep;
                }catch(InterruptedException e){
                    e.printStackTrace();
                }catch(java.sql.SQLNonTransientConnectionException e){
                    System.err.println("Impossible connect to db, retry again");
                }catch(SQLException se){
                    se.printStackTrace();
                    System.err.println("problemi di accesso al DB; risolvere;");
                }
            }

            GregorianCalendar gc = new GregorianCalendar(2000, 01, 01);    //serve solo a generare un oggetto
            gc.setTime(GregorianCalendar.getInstance().getTime());
            try{
                this.dao.deleteByDeamon(gc);
                gc.add(Calendar.MONTH, 1);
                this.dao.updateNextDelS(gc);
            }catch(java.sql.SQLNonTransientConnectionException e){
                System.err.println("Impossible connect to db, retry again");
            }catch(SQLException se){
                se.printStackTrace();
                System.err.println("clear abort!!! retry next month");
            }
        }
    }

    private GregorianCalendar stringToGregCal(String s){
        String[] splitDate = s.split("-");
        int year = Integer.parseInt(splitDate[0]);
        int month = Integer.parseInt(splitDate[1]);
        int days = Integer.parseInt(splitDate[2]);
        GregorianCalendar gc = new GregorianCalendar(year, month - 1, days);
        return gc;

    }

    private String gregCalToString(GregorianCalendar gc){
        int anno = gc.get(GregorianCalendar.YEAR);
        int mese = gc.get(GregorianCalendar.MONTH) + 1;
        int giorno = gc.get(GregorianCalendar.DATE);
        String s = anno + "-" + mese + "-" + giorno;
        return s;
    }
}
