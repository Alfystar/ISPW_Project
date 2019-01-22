package entity;
public class Nationality extends ModifyDataString{
    private String nat;

    public Nationality(String nat){
        this.nat= nat;
    }

    @Override
    public String get(){
        return this.nat;
    }
    @Override
    public void set(String newNat){
        this.nat= newNat;
    }
}
