package entity;
public class PhoneNumber extends ModifyDataString{
    private String phone;

    public PhoneNumber(String phone){
        this.phone= phone;
    }

    @Override
    public String get(){
        return this.phone;
    }
    @Override
    public void set(String newPhone){
        this.phone= newPhone;
    }
}
