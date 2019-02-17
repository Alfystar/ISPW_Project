package entity;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class Name{
    private String name;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public Name(String name){
        if(name.length() >= 2) this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        else this.name = name.toUpperCase();
    }

    public Name(){
        this.name = "";
    }

    public Name(Name name){
        this.name = name.get();
    }

    public String get(){
        try{
            lock.readLock().lock();
            return this.name;
        }finally{
            lock.readLock().unlock();
        }
    }

    public void set(String newName){

        try{
            lock.writeLock().lock();
            if(name.length() >= 2)
                this.name = newName.substring(0, 1).toUpperCase() + newName.substring(1).toLowerCase();
            else this.name = newName.toUpperCase();
        }finally{
            lock.writeLock().unlock();
        }
    }

    @Override
    public boolean equals(Object o){
        try{
            return this.name.equals(((Name) o).name);
        }catch(ClassCastException e){
            return false;
        }
    }
}
