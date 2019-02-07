package gluonBoundary;

import control.FacadeSubSystem;
import entity.Nickname;
import entity.PW;
import exceptions.UserNotExistEx;
import gluonBoundary.utilityClass.BeanLogUs;
import interfaces.SystemInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class K_login implements Initializable {

    /*******************************************************************/
    /**                       Class Attribute                         **/
    SystemInterface sysInt;

    /*******************************************************************/

    public K_login ()
    {
        sysInt = new FacadeSubSystem();
    }


    //=================================================================
    //Top object
    @FXML
    private TextField nickField;
    @FXML
    private PasswordField pwField;

    //=================================================================
    //Bottom object
    @FXML
    Hyperlink register, forgottenPw;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    public void loginPush(ActionEvent event) throws IOException {
        System.out.println(nickField.getText());
        System.out.println(pwField.getText());
        try {
            sysInt.login(new Nickname(nickField.getText()),new PW(pwField.getText()));
        }catch (UserNotExistEx e)
        {
            System.out.println("Utente non presente RIPROVARE");
            return;
        }

        //todo: should be a validation, but for now...

        //Parent userParent = FXMLLoader.load(getClass().getResource("fxmlSrc/userPane.fxml"));

        BeanLogUs bean = new BeanLogUs();
        bean.setNick(new Nickname(nickField.getText()));

        Parent userParent=null;
        K_user kUser;

        FXMLLoader userLoader = new FXMLLoader(getClass().getResource("fxmlSrc/userPane.fxml"));
        try {
            userParent = (Parent)userLoader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if(userLoader!=null){
            //we create a custom controller
            kUser = userLoader.getController();
            //here we pass the reference to the  other controller
            kUser.setBean(bean);
            System.out.println("userLoader not null");
        }

        Scene userScene = new Scene(userParent);

        Stage windows = (Stage)((Node)event.getSource()).getScene().getWindow();
        windows.setScene(userScene);
    }

    @FXML
    public void registerPush(ActionEvent event) throws IOException{

        Parent registerParent = FXMLLoader.load(getClass().getResource("fxmlSrc/registration.fxml"));
        Scene registerScene = new Scene(registerParent);

        Stage windows = (Stage)((Node)event.getSource()).getScene().getWindow();
        windows.setScene(registerScene);
    }

    @FXML
    public void forgottenPush(ActionEvent event) throws IOException{

        Parent resetPwParent = FXMLLoader.load(getClass().getResource("fxmlSrc/resetPw.fxml"));
        Scene resetPwScene = new Scene(resetPwParent);

        Stage windows = (Stage)((Node)event.getSource()).getScene().getWindow();
        windows.setScene(resetPwScene);
    }
}
