package entity;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Roles{
    private Boolean renter;
    private Boolean tenant;

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    //Costruttore di default
    public Roles(){
        this.tenant = false;
        this.renter = false;
    }

    //Costruttore con tutti i valori possibili di Roles
    public Roles(Boolean renter, Boolean tenant){
        this.renter = renter;
        this.tenant = tenant;
    }

    public Roles(Roles roles){
        this.tenant = roles.isTenant();
        this.renter = roles.isRenter();
    }

    public Boolean isRenter(){

        try{
            lock.readLock().lock();
            return this.renter;
        }finally{
            lock.readLock().unlock();
        }
    }

    public Boolean isTenant(){

        try{
            lock.readLock().lock();
            return this.tenant;
        }finally{
            lock.readLock().unlock();
        }
    }

    public Boolean isRegistered(){

        try{
            lock.readLock().lock();
            return !(this.tenant || this.renter);
        }finally{
            lock.readLock().unlock();
        }
    }

    public void setRenter(){
        try{
            lock.writeLock().lock();
            this.renter = true;
        }finally{
            lock.writeLock().unlock();
        }
    }

    public void resetRenter(){

        try{
            lock.writeLock().lock();
            this.renter = false;
        }finally{
            lock.writeLock().unlock();
        }
    }

    public void setTenant(){

        try{
            lock.writeLock().lock();
            this.tenant = true;
        }finally{
            lock.writeLock().unlock();
        }
    }

    public void resetTenant(){

        try{
            lock.writeLock().lock();
            this.tenant = false;
        }finally{
            lock.writeLock().unlock();
        }
    }

    public String rlBIN(){
        String out;
        if(tenant) out = "1";
        else out = "0";
        if(renter) out += "1";
        else out += "0";
        return out;
    }

    @Override
    public String toString(){
        return "isRenter:" + this.isRenter() + ", isTenant:" + isTenant() + "\n";
    }

    @Override
    public boolean equals(Object o){
        try{
            Roles r = (Roles) o;
            return (this.renter == r.renter && this.tenant == r.tenant);
        }catch(ClassCastException e){
            return false;
        }
    }

}
