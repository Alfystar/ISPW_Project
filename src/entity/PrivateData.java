package entity;
public class PrivateData {
    private SurfaceAddress address;
    private SurfaceAddress cityOfBirth;
    private Nationality nat;
    private PhoneNumber phone;

    public PrivateData(SurfaceAddress address, SurfaceAddress cityOfBirth, Nationality nat, PhoneNumber phone){
        this.address= address;
        this.cityOfBirth= cityOfBirth;
        this.nat= nat;
        this.phone= phone;
    }


    public SurfaceAddress getLocalAddress(){
        return this.address;
    }

    public SurfaceAddress getCityOfBirth(){
        return this.cityOfBirth;
    }

    public Nationality getNationality(){
        return this.nat;
    }

    public PhoneNumber getPhone(){
        return this.phone;
    }

}
