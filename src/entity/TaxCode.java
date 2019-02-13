package entity;

public class TaxCode{
    private String cf;

    public TaxCode(String cf){ this.cf = cf.toUpperCase().replaceAll("\\s+","");}

    public TaxCode(){
        this.cf = "";
    }

    public TaxCode(TaxCode cf){
        this.cf = cf.get();
    }

    public String get(){
        return this.cf;
    }

    public void set(String newCf){
        this.cf = newCf;
    }
}
