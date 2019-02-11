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
        return this.address;
    }

    @Override
    public void set(String newAddress){
        this.address = newAddress;
    }
}
