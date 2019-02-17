package entity;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TaxCode{
    private String cf;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public TaxCode(String cf){ this.cf = cf.toUpperCase().replaceAll("\\s+", "");}

    public TaxCode(){
        this.cf = "";
    }

    public TaxCode(TaxCode cf){
        this.cf = cf.get();
    }

    public String get(){

        try{
            lock.readLock().lock();
            return this.cf;
        }finally{
            lock.readLock().unlock();
        }
    }

    public void set(String newCf){

        try{
            lock.writeLock().lock();
            this.cf = newCf.toUpperCase().replaceAll("\\s+", "");;
        }finally{
            lock.writeLock().unlock();
        }
    }

    @Override
    public boolean equals(Object o){
        try{
            return this.cf.equals(((TaxCode) o).cf);
        }catch(ClassCastException e){
            return false;
        }
    }
}
