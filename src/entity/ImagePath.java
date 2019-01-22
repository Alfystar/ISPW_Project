package entity;

public class ImagePath extends ModifyDataString {
    private String path;


    public ImagePath(String path)
    {
        this.path=path;
    }

    public ImagePath()
    {
        this.path="/";
    }

    public ImagePath(ImagePath path){
        this.path= path.get();
    }

    @Override
    public String get()
    {
        return this.path;
    }
    @Override
    public void set(String dataString)
    {
        this.path=dataString;
    }

}
