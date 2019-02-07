package gluonBoundary;

import bean.BasicUserInfo;
import bean.RestrictUserInfo;
import control.FacadeSubSystem;
import entity.*;
import exceptions.UserNotExistEx;
import gluonBoundary.utilityClass.Bean2User;
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
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

public class K_user implements Initializable {

    /*******************************************************************/
    /**                       Class Attribute                         **/

    private SystemInterface sysInt = new FacadeSubSystem();
    private RoleStatus rolStatInt = new FacadeSubSystem();
    private UserProfileService usInt = new FacadeSubSystem();

    private Bean2User bean;
    private BasicUserInfo basic;
    private RestrictUserInfo restrict;
    private Roles roles;
    /*******************************************************************/

    //=================================================================
    //Top object
    @FXML
    Button backHome;

    //=================================================================
    //information view sector
    @FXML
    ImageView avatar;

    @FXML
    Button getRole, getPubD, getPrD;

    @FXML
    CheckBox tenant, renter;
    @FXML
    RadioButton man, woman;

    @FXML
    TextField nick, email, tc, socStat, name, surname;

    @FXML
    DatePicker birthday;

    @FXML
    TextField cel, address, cityBirth, nat;

    //=================================================================
    //change sector
    @FXML
    ChoiceBox paramChange;

    @FXML
    Button sendUpdate;

    @FXML
    RadioButton av1,av2,av3,av4,av5,av6;

    //=================================================================
    //password sector
    @FXML
    PasswordField oldPw, newPw, confPw;

    @FXML
    Button changePw;

    //=================================================================
    //bottom bar
    @FXML
    Label outLabel;

    @FXML
    ProgressBar progress;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Node[] disableVector = {
                tenant, renter, man, woman,
                nick, email, tc, socStat, name, surname, birthday,
                cel, address, cityBirth, nat};

        //information view sector setup
        for (Node e:disableVector) {    //struttura foreach in java
            e.setDisable(true);
            e.setStyle("-fx-opacity: 1");
        }
        //to restore white shape
        birthday.getEditor().setStyle("-fx-opacity: 1");



        //change sector setup
        paramChange.getItems().addAll("Social Status","Email","Phone Number","Nationality","Surface Address");
        paramChange.setValue("Social Status");

        //password sector setup

    }

    @FXML
    public void homeScene(ActionEvent event) throws IOException {
        //prepare new scene to replace
        Parent homeParent = FXMLLoader.load(getClass().getResource("fxmlSrc/homeStandAlone.fxml"));
        Scene homeScene = new Scene(homeParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        //set new scene
        window.setScene(homeScene);
    }
    @FXML
    public void updateUser(ActionEvent actionEvent) {
        RadioButton[] radioNode={av1,av2,av3,av4,av5,av6};
        outLabel.setText("updateUser click");
    }
    @FXML
    public void refreshRole(ActionEvent actionEvent) {

        outLabel.setText("refreshRole click");
        loadRole();
    }
    @FXML
    public void refreshPubD(ActionEvent actionEvent) {

        outLabel.setText("refreshPubD click");
        loadPublic();
    }
    @FXML
    public void refreshPrD(ActionEvent actionEvent) {

        outLabel.setText("refreshPrD click");
        loadPrivate();
    }
    @FXML
    public void changePw(ActionEvent actionEvent) {

        outLabel.setText("changePw click");
    }


    public void setBean(Bean2User bean)
    {
        this.bean=bean;

        nick.setText(bean.getNick().get());
    }


    private void loadPublic(){

        try {
            basic = usInt.getBasicUserInfo(bean.getNick());
        }catch (UserNotExistEx e) {
            outLabel.setText("PROBLEMI CON IL NICKNAME, non più trovato");
        }
        nick.setText(basic.getNickname().get());
        email.setText(basic.getEmail().get());
        tc.setText(basic.getTaxCode().get());
        socStat.setText(basic.getsocialStatus().get());
        name.setText(basic.getName().get());
        surname.setText(basic.getSurname().get());
        if(basic.getGender().equals(Gender.MAN))
        {
            man.setSelected(true);
        }else {
            woman.setSelected(true);
        }
        avatar.setImage(basic.getAvatar().getMyIcon());
        //todo capire come mettere birtday
        /*
    private GregorianCalendar birthday= new GregorianCalendar();

        */
    }

    private void loadPrivate() {
        try {
            restrict = usInt.getRestrictedUserInfo(bean.getNick());
        } catch (UserNotExistEx e) {
            outLabel.setText("PROBLEMI CON IL NICKNAME, non più trovato");
        }
        cel.setText(restrict.getPhoneNumber().get());
        cityBirth.setText(restrict.getCityOfBirth().get());
        address.setText(restrict.getAddress().get());
        nat.setText(restrict.getNationality().get());
        }

    private void loadRole(){
        try {
            roles= rolStatInt.getRoles(bean.getNick());
        }catch (UserNotExistEx e){
            outLabel.setText("PROBLEMI CON IL NICKNAME, non più trovato");
        }
        if(roles.isTenant()) {
            tenant.setSelected(true);
        }
        if(roles.isRenter()){
            renter.setSelected(true);
        }
    }

}
