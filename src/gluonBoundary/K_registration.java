package gluonBoundary;

import entity.Avatar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class K_registration implements Initializable {

    /*******************************************************************/
    /**                       Class Attribute                         **/
    public Avatar iconAvatar=new Avatar();


    /*******************************************************************/

    //=================================================================
    //General information Tab
    @FXML
    RadioButton man, woman;

    @FXML
    TextField nick, email;

    @FXML
    PasswordField newPw, confPw;

    @FXML
    DatePicker birthday;

    //=================================================================
    //Personal Information tab

    @FXML
    TextField name, surname, tc;

    @FXML
    RadioButton av1,av2,av3,av4,av5,av6;

    @FXML
    ImageView avatar;

    //=================================================================
    //Questions tab
        @FXML
    TextField answ1,answ2,answ3,answ4;

    //=================================================================
    //bottom bar
    @FXML
    Label outLabel;

    @FXML
    ProgressBar progress;

    @FXML
    ImageView statusIco;

    @FXML
    Button register;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    public void registerPush(ActionEvent event) throws IOException {
        //todo:should be a validation, but for now...

        Parent userParent = FXMLLoader.load(getClass().getResource("fxmlSrc/userPane.fxml"));
        Scene userScene = new Scene(userParent);

        Stage windows = (Stage)((Node)event.getSource()).getScene().getWindow();
        windows.setScene(userScene);
    }

    @FXML
    public void avatarChange(ActionEvent event) {
        RadioButton[] radioNode={av1,av2,av3,av4,av5,av6};
        for (int i = 0; i < radioNode.length; i++) {
            if(radioNode[i].isArmed())
            {
                iconAvatar.setMyIcon(i);
                avatar.setImage(iconAvatar.getMyIcon());
                outLabel.setText(iconAvatar.getAvatarName());
                break;
            }
        }
    }
}
