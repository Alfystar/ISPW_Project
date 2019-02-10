package gluonBoundary;

import DAO.DAOClass;
import DAO.DaemonDAO;
import control.FacadeSubSystem;
import exceptions.DBConnectionEx;
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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class K_home implements Initializable{


    //=================================================================
    //simulation chose sector
    @FXML
    private Button userBut, adminBut, subSystemBut;
    //=================================================================
    //Network controll sector
    @FXML
    private TextField ipField;
    @FXML
    private Button defIp;
    @FXML
    private Label outLabel;
    @FXML
    private ImageView testIcon;

    /*** Class Attribute ***/
    private SystemInterface sysInt = new FacadeSubSystem();
    private RoleStatus rolStatInt = new FacadeSubSystem();
    private UserProfileService usInt = new FacadeSubSystem();
    private DigitalIcon testConnImg = new DigitalIcon();


    @Override
    public void initialize(URL url, ResourceBundle rb){
        try{
            ipField.setText(sysInt.getLastHost());
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }


    @FXML
    public void adminSimulation(ActionEvent event) throws IOException{
        //Avvio dei tread
        try{
            this.treadStart();
        }catch(Exception e){
            e.printStackTrace();
            return;
        }


        //prepare new scene to replace
        Parent adminParent = FXMLLoader.load(getClass().getResource("fxmlSrc/adminPane.fxml"));
        Scene adminScene = new Scene(adminParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //set new scene
        window.setScene(adminScene);
    }

    @FXML
    public void userSimulation(ActionEvent event) throws IOException{
        //Avvio dei tread
        try{
            this.treadStart();
        }catch(Exception e){
            e.printStackTrace();
            return;
        }

        //prepare new scene to replace
        Parent adminParent = FXMLLoader.load(getClass().getResource("fxmlSrc/login.fxml"));
        Scene adminScene = new Scene(adminParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //set new scene
        window.setScene(adminScene);
    }

    @FXML
    public void otherSimulation(ActionEvent event) throws IOException{
        //Avvio dei tread
        try{
            this.treadStart();
        }catch(Exception e){
            e.printStackTrace();
            return;
        }

        //prepare new scene to replace
        Parent adminParent = FXMLLoader.load(getClass().getResource("fxmlSrc/otherPane.fxml"));
        Scene adminScene = new Scene(adminParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //set new scene
        window.setScene(adminScene);
    }

    @FXML
    public void testConnection(ActionEvent actionEvent){
        this.testDBConn();
    }

    private Boolean testDBConn(){
        try{
            DAOClass daoClass = new DAOClass(ipField.getText());
            if(daoClass.testNet()){
                testConnImg.setState(true);
                testIcon.setImage(testConnImg.getIcon());
                outLabel.setText("DB connected");
                return true;
            }else{
                testConnImg.setState(false);
                testIcon.setImage(testConnImg.getIcon());
                outLabel.setText("DB not connected");
                return false;

            }
        }catch(ClassNotFoundException e){
            outLabel.setText("Driver DB not found");
        }catch(SQLException e){
            e.printStackTrace();
            outLabel.setText("DB Problem");
        }
        return false;
    }

    @FXML
    public void restoreIp(ActionEvent actionEvent){
        ipField.setText("localHost");
    }


    private void treadStart() throws DBConnectionEx{
        try{
            sysInt.changeHost(ipField.getText());
            DaemonDAO daemonDAO = DaemonDAO.getInstance();
            if(!this.testDBConn()){
                throw new DBConnectionEx("DB Connection Problem");
            }
        }catch(ClassNotFoundException e){
            outLabel.setText("Driver DB not found");
        }
    }
}
