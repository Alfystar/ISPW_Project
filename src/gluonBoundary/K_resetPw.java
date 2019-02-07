package gluonBoundary;

import gluonBoundary.utilityClass.DigitalIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class K_resetPw implements Initializable {

    /*******************************************************************/
    /**                       Class Attribute                         **/
    private DigitalIcon answCheck = new DigitalIcon();


    /*******************************************************************/

    //=================================================================
    //Questions tab
    @FXML
    TextField answ1,answ2,answ3,answ4;

    @FXML
    Button checkBut;

    @FXML
    ImageView icoCheckAnsw;

    //=================================================================
    //password sector
    @FXML
    PasswordField newPw, confPw;

    @FXML
    Button resetPwBut;

    @FXML
    ImageView icoCheckPw;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }



    @FXML
    public void resetPush(ActionEvent event) throws IOException {
        //todo: should be a validation, but for now...

        Parent userParent = FXMLLoader.load(getClass().getResource("fxmlSrc/userPane.fxml"));
        Scene userScene = new Scene(userParent);

        Stage windows = (Stage)((Node)event.getSource()).getScene().getWindow();
        windows.setScene(userScene);
    }


    @FXML
    public void chechQuestion(ActionEvent actionEvent) {
        answCheck.setState(!answCheck.getState());
        icoCheckAnsw.setImage(answCheck.getIcon());
    }
}
