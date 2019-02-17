package entity;

public class Nationality extends ModifyDataString{
    private String nat;

    public Nationality(String nat){
        if(nat.length() >= 2) this.nat = nat.substring(0, 1).toUpperCase() + nat.substring(1).toLowerCase();
        else this.nat = nat.toUpperCase();
    }

    public Nationality(){ this.nat = "";}

    public Nationality(Nationality nat){
        this.nat = nat.get();
    }

    @Override
    public String get(){
        try{
            lock.readLock().lock();
            return this.nat;
        }finally{
            lock.readLock().unlock();
        }
    }

    @Override
    public void set(String newNat){
        try{
            lock.writeLock().lock();
            if(nat.length() >= 2) this.nat = newNat.substring(0, 1).toUpperCase() + newNat.substring(1).toLowerCase();
            else this.nat = newNat.toUpperCase();
        }finally{
            lock.writeLock().unlock();
        }
    }

    @Override
    public boolean equals(Object o){
        try{
            return this.nat.equals(((Nationality) o).nat);
        }catch(ClassCastException e){
            return false;
        }
    }
}
