package DAO;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.abs;

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

    private static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit){
        long diffInMillies = abs(date1.getTime() - date2.getTime());
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    @Override
    public void run(){
        while(true){
            //codice ripetuto sempre finchè con delta che diminuiscono via via, finchè non si supera la data prevista
            restartSleep:
            {
                try{
                    Thread.sleep(10000);
                    Date todayDay = GregorianCalendar.getInstance().getTime();
                    GregorianCalendar gcFutureDate = this.dao.nextDeleteSession();
                    long deltaTime = getDateDiff(todayDay, gcFutureDate.getTime(), TimeUnit.MILLISECONDS);
                    System.out.println(deltaTime);
                    if(deltaTime <= 0) break restartSleep;
                    Thread.sleep(deltaTime);
                    break restartSleep;
                }catch(InterruptedException e){
                    e.printStackTrace();
                }catch (java.sql.SQLNonTransientConnectionException e) {
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
                gc.add(Calendar.MONTH,1);
                this.dao.updateNextDelS(gc);
            }catch (java.sql.SQLNonTransientConnectionException e) {
                System.err.println("Impossible connect to db, retry again");
            }catch(SQLException se){
                se.printStackTrace();
                System.err.println("clear abort!!! retry next month");
            }
        }
    }
}
