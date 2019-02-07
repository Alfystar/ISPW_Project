package gluonBoundary;

import DAO.DAOClass;
import DAO.DaemonDAO;
import exceptions.DBConnectionEx;
import gluonBoundary.utilityClass.DigitalIcon;
import javafx.application.Application;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class K_home  implements Initializable {

    /*******************************************************************/
    /**                       Class Attribute                         **/
    DigitalIcon testConnImg = new DigitalIcon();

    /*******************************************************************/

    //=================================================================
    //simulation chose sector
    @FXML
    Button userBut, adminBut, subSystemBut;

    //=================================================================
    //Network controll sector
    @FXML
    TextField ipField;

    @FXML
    Button defIp;

    @FXML
    Label outLabel;

    @FXML
    ImageView testIcon;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }



    @FXML
    public void adminSimulation(ActionEvent event) throws IOException {
        //Avvio dei tread
        try {
            this.treadStart();
        }catch (Exception e)
        {
            e.printStackTrace();
            return;
        }


        //prepare new scene to replace
        Parent adminParent = FXMLLoader.load(getClass().getResource("fxmlSrc/adminPane.fxml"));
        Scene adminScene = new Scene(adminParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        //set new scene
        window.setScene(adminScene);
    }

    @FXML
    public void userSimulation(ActionEvent event) throws IOException {
        //Avvio dei tread
        try {
            this.treadStart();
        }catch (Exception e)
        {
            e.printStackTrace();
            return;
        }

        //prepare new scene to replace
        Parent adminParent = FXMLLoader.load(getClass().getResource("fxmlSrc/login.fxml"));
        Scene adminScene = new Scene(adminParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        //set new scene
        window.setScene(adminScene);
    }

    @FXML
    public void otherSimulation(ActionEvent event) throws IOException {
        //Avvio dei tread
        try {
            this.treadStart();
        }catch (Exception e)
        {
            e.printStackTrace();
            return;
        }

        //prepare new scene to replace
        Parent adminParent = FXMLLoader.load(getClass().getResource("fxmlSrc/otherPane.fxml"));
        Scene adminScene = new Scene(adminParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        //set new scene
        window.setScene(adminScene);
    }

    @FXML
    public void testConnection(ActionEvent actionEvent) {
        this.testDBConn();
    }

    private Boolean testDBConn()
    {
        try {
            DAOClass daoClass=new DAOClass(ipField.getText());
            if(daoClass.testNet())
            {
                testConnImg.setState(true);
                testIcon.setImage(testConnImg.getIcon());
                outLabel.setText("DB connected");
                return true;
            }
            else
            {
                testConnImg.setState(false);
                testIcon.setImage(testConnImg.getIcon());
                outLabel.setText("DB not connected");
                return false;

            }
        }catch (ClassNotFoundException e)
        {
            outLabel.setText("Driver DB not found");
        }catch (SQLException e)
        {
            e.printStackTrace();
            outLabel.setText("DB Problem");
        }
        return false;
    }

    @FXML
    public void restoreIp(ActionEvent actionEvent) {
        ipField.setText("localHost");
    }


    private void treadStart() throws ClassNotFoundException, DBConnectionEx
    {
        try {
            DAOClass dao= new DAOClass(ipField.getText());
            DaemonDAO daemonDAO = DaemonDAO.getInstance();
            if(!this.testDBConn()){
                throw new DBConnectionEx("DB Connection Problem");
            }
        }catch (ClassNotFoundException e)
        {
            outLabel.setText("Driver DB not found");
        }

    }
}
