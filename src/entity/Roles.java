package entity;

public class Roles{
    private Boolean renter;
    private Boolean tenant;

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
        return this.renter;
    }

    public Boolean isTenant(){
        return this.tenant;
    }

    public Boolean isRegistered(){
        return !(this.tenant || this.renter);
    }

    public void setRenter(){
        this.renter = true;
    }

    public void resetRenter(){
        this.renter = false;
    }

    public void setTenant(){
        this.tenant = true;
    }

    public void resetTenant(){
        this.tenant = false;
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
