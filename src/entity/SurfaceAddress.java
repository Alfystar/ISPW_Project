package entity;

public class SurfaceAddress extends ModifyDataString{
    private String address;

    public SurfaceAddress(String address){
        this.address = address;
    }

    public SurfaceAddress(){
        this.address = "";
    }

    public SurfaceAddress(SurfaceAddress address){
        this.address = address.get();
    }


    @Override
    public String get(){

        try{
            lock.readLock().lock();
            return this.address;
        }finally{
            lock.readLock().unlock();
        }
    }

    @Override
    public void set(String newAddress){

        try{
            lock.writeLock().lock();
            this.address = newAddress;
        }finally{
            lock.writeLock().unlock();
        }
    }

    @Override
    public boolean equals(Object o){
        try{
            return this.address.equals(((SurfaceAddress) o).address);
        }catch(ClassCastException e){
            return false;
        }
    }
}
