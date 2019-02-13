package entity;

public class PhoneNumber extends ModifyDataString{
    private String phone;

    public PhoneNumber(String phone){
        this.phone = phone.replaceAll("\\s+","");
    }

    public PhoneNumber(){ this.phone = "";}

    public PhoneNumber(PhoneNumber phone){
        this.phone = phone.get();
    }

    @Override
    public String get(){
        return this.phone;
    }

    @Override
    public void set(String newPhone){
        this.phone = newPhone;
    }
}
