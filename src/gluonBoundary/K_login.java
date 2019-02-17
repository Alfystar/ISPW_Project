package gluonBoundary;

import control.FacadeSubSystem;
import entity.Nickname;
import entity.PW;
import exceptions.UserBannedEx;
import exceptions.UserNotExistEx;
import gluonBoundary.utilityClass.Bean2User;
import interfaces.SystemInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class K_login implements Initializable{

    //=================================================================
    //Bottom object
    @FXML
    private Hyperlink register, forgottenPw;
    //=================================================================
    //Top object
    @FXML
    private TextField nickField;
    @FXML
    private PasswordField pwField;
    @FXML
    private Label outLabel;

    /*** Class Attribute ***/
    private SystemInterface sysInt = new FacadeSubSystem();


    @Override
    public void initialize(URL url, ResourceBundle rb){
    }

    @FXML
    public void loginPush(ActionEvent event) throws IOException{
        System.out.println(nickField.getText());
        System.out.println(pwField.getText());
        try{
            if(!sysInt.validate(new Nickname(nickField.getText()), new PW(pwField.getText()))) return;
        }catch(UserNotExistEx e){
            outLabel.setText("nick errato");
            return;
        }catch(UserBannedEx e){
            outLabel.setText("Utente Bannato");
        }catch(SQLException e){
            e.printStackTrace();
        }


        //Parent userParent = FXMLLoader.load(getClass().getResource("fxmlSrc/userPane.fxml"));

        Bean2User bean = new Bean2User();
        bean.setNick(new Nickname(nickField.getText()));

        Parent userParent = null;
        K_user kUser;

        FXMLLoader userLoader = new FXMLLoader(getClass().getResource("fxmlSrc/userPane.fxml"));
        try{
            userParent = userLoader.load();
        }catch(IOException ex){
            ex.printStackTrace();
        }

        if(userLoader != null){
            //we create a custom controller
            kUser = userLoader.getController();
            //here we pass the reference to the  other controller
            kUser.setBean(bean);
        }

        Scene userScene = new Scene(userParent);

        Stage windows = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windows.setScene(userScene);
    }

    @FXML
    public void registerPush(ActionEvent event) throws IOException{

        Parent registerParent = FXMLLoader.load(getClass().getResource("fxmlSrc/registration.fxml"));
        Scene registerScene = new Scene(registerParent);

        Stage windows = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windows.setScene(registerScene);
    }

    @FXML
    public void forgottenPush(ActionEvent event) throws IOException{

        Parent resetPwParent = FXMLLoader.load(getClass().getResource("fxmlSrc/resetPw.fxml"));
        Scene resetPwScene = new Scene(resetPwParent);

        Stage windows = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windows.setScene(resetPwScene);
    }
}
