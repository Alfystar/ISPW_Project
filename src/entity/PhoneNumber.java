package entity;

public class PhoneNumber extends ModifyDataString{
    private String phone;

    public PhoneNumber(String phone){
        this.phone = phone.replaceAll("\\s+", "").replaceAll("[^\\d-+]", "");
    }

    public PhoneNumber(){ this.phone = "";}

    public PhoneNumber(PhoneNumber phone){
        this.phone = phone.get();
    }

    @Override
    public String get(){

        try{
            lock.readLock().lock();
        }finally{
            lock.readLock().unlock();
        }
        return this.phone;
    }

    @Override
    public void set(String newPhone){

        try{
            lock.writeLock().lock();
            this.phone = newPhone.replaceAll("\\s+", "").replaceAll("[^\\d-+]", "");
        }finally{
            lock.writeLock().unlock();
        }
    }

    @Override
    public boolean equals(Object o){
        try{
            return this.phone.equals(((PhoneNumber) o).phone);
        }catch(ClassCastException e){
            return false;
        }
    }
}
