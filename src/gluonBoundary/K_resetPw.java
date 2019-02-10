package gluonBoundary;

import control.FacadeSubSystem;
import entity.Nickname;
import entity.PW;
import entity.Questions;
import exceptions.UserNotExistEx;
import gluonBoundary.utilityClass.Bean2User;
import gluonBoundary.utilityClass.DigitalIcon;
import interfaces.RoleStatus;
import interfaces.SystemInterface;
import interfaces.UserProfileService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class K_resetPw implements Initializable{


    //=================================================================
    //Questions tab
    @FXML
    private TextField nick, answ1, answ2, answ3, answ4;
    @FXML
    private Button checkBut;
    @FXML
    private ImageView icoCheckAnsw;
    @FXML
    private Label outLabel;
    //=================================================================
    //password sector
    @FXML
    private PasswordField newPw, confPw;
    @FXML
    private Button resetPwBut;
    @FXML
    private ImageView icoCheckPw;

    /*** Class Attribute ***/
    private DigitalIcon answCheck = new DigitalIcon();
    private DigitalIcon pwCheck = new DigitalIcon();
    private SystemInterface sysInt = new FacadeSubSystem();
    private RoleStatus rolStatInt = new FacadeSubSystem();
    private UserProfileService usInt = new FacadeSubSystem();

    @Override
    public void initialize(URL url, ResourceBundle rb){
        resetPwBut.setDisable(true);    //attivato quando le pw sono uguali

        newPw.setDisable(true);         //attivato da checkQuestion
        confPw.setDisable(true);        //attivato da checkQuestion
    }


    @FXML
    public void chechQuestion(ActionEvent actionEvent){

        Nickname nickSearch = new Nickname(nick.getText());
        String[] answs = {answ1.getText(), answ2.getText(), answ3.getText(), answ4.getText()};
        Questions q = new Questions(answs);
        try{
            if(sysInt.checkQuestion(nickSearch, q)){
                outLabel.setText("Questionario corretto");
                answCheck.setState(true);
                icoCheckAnsw.setImage(answCheck.getIcon());

                newPw.setDisable(false);
                confPw.setDisable(false);

            }else{
                outLabel.setText("Questionario non corretto");
                answCheck.setState(false);
                icoCheckAnsw.setImage(answCheck.getIcon());
            }
        }catch(UserNotExistEx e){
            outLabel.setText("Utente Non trovato");
            answCheck.setState(false);
            icoCheckAnsw.setImage(answCheck.getIcon());
        }

    }

    @FXML
    public void checkPW(KeyEvent event){
        if(newPw.getText().equals(confPw.getText()) && !newPw.getText().equals("")){
            pwCheck.setState(true);
            resetPwBut.setDisable(false);
        }else pwCheck.setState(false);
        icoCheckPw.setImage(pwCheck.getIcon());
        return;
    }

    @FXML
    public void resetPush(ActionEvent event) throws IOException{


        try{
            if(newPw.getText().equals(confPw.getText()) && !newPw.getText().equals("")){
                Nickname nickSearch = new Nickname(nick.getText());
                String[] answs = {answ1.getText(), answ2.getText(), answ3.getText(), answ4.getText()};
                Questions q = new Questions(answs);

                sysInt.forgottenPassword(nickSearch, q, new PW(newPw.getText()));
            }else{
                return;
            }
        }catch(UserNotExistEx e){
            outLabel.setText("Utente Non trovato");
            answCheck.setState(false);
            icoCheckAnsw.setImage(answCheck.getIcon());
        }catch(SQLException e){
        }catch(ClassNotFoundException e){
        }


        //todo: should be a validation, but for now...


        Bean2User bean = new Bean2User();
        bean.setNick(new Nickname(nick.getText()));

        Parent userParent = null;
        K_user kUser;

        FXMLLoader userLoader = new FXMLLoader(getClass().getResource("fxmlSrc/userPane.fxml"));
        try{
            userParent = (Parent) userLoader.load();
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

}
