package entity;
public class Roles {
    private Boolean renter;
    private Boolean tenant;

    public Roles(){
        this.tenant=false;
        this.renter=false;
    }

    public Roles(Boolean renter, Boolean tenant){
        this.renter=renter;
        this.tenant= tenant;
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
        this.renter= true;
    }

    public void resetRenter(){
        this.renter=false;
    }

    public void setTenant(){
        this.tenant=true;
    }

    public void resetTenant(){
        this.tenant=false;
    }
}
