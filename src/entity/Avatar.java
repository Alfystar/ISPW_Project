package entity;

import javafx.scene.image.Image;

import java.util.Vector;

public class Avatar
{
    private String[] nameAvatar = {"default-Avatar","roket-Avatar", "girl-Avatar", "man-Avatar", "girlStudent-Avatar", "manStudent-Avatar"};
    private Vector<Image> icons = new Vector<Image>();
    private Image myIcon;
    private int myImageIndex;


    public Avatar(){
        Image img;
        for(String str:nameAvatar)
        {
            //ImageFile/Avatar/default-Avatar.png
            img = new Image(getClass().getResourceAsStream("/ImageFile/Avatar/"+str+".png"));
            icons.add(img);
        }
        this.myIcon=icons.get(0);
        this.myImageIndex=0;
    }

    public Avatar(String name)
    {
        this();
        setMyIcon(name);
    }


    public void setMyIcon (int index)
    {
        this.myIcon=icons.get(index);
        this.myImageIndex= index;
    }

    public void setMyIcon (String name)
    {
        for (int i = 0; i < nameAvatar.length; i++) {
            if(name==nameAvatar[i])
            {
                this.myIcon=icons.get(i);
                this.myImageIndex= i;
                return;
            }
        }
        this.myIcon=icons.get(0);
        this.myImageIndex= 0;
    }

    public Image getMyIcon ()
    {
        return this.myIcon;
    }

    public String getAvatarName()
    {
        return nameAvatar[this.myImageIndex];
    }

    public static void main (String[] argv)
    {
        Avatar iconAvatar = new Avatar();
    }

}
