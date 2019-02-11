package gluonBoundary.utilityClass;

import javafx.scene.image.Image;

public class DigitalIcon{


    private Image checkIco = new Image(getClass().getResourceAsStream("/ImageFile/Icon/confirm.png"));
    private Image rejectIco = new Image(getClass().getResourceAsStream("/ImageFile/Icon/reject.png"));

    private boolean state = false;
    private Image stateImage = rejectIco;

    public DigitalIcon(){
    }

    public Image getIcon(){return stateImage;}

    public boolean getState(){return state;}

    public void setState(boolean state){
        this.state = state;
        if(state) this.stateImage = checkIco;
        else this.stateImage = rejectIco;
    }

}
