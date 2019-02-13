package entity;

public class Nationality extends ModifyDataString{
    private String nat;

    public Nationality(String nat){
        this.nat = nat.substring(0,1).toUpperCase()+nat.substring(1).toLowerCase();
    }

    public Nationality(){ this.nat = "";}

    public Nationality(Nationality nat){
        this.nat = nat.get();
    }

    @Override
    public String get(){
        return this.nat;
    }

    @Override
    public void set(String newNat){
        this.nat = newNat;
    }
}
