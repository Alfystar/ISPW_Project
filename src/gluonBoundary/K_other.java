package gluonBoundary;

import bean.BasicUserInfo;
import bean.RestrictUserInfo;
import control.FacadeSubSystem;
import entity.Gender;
import entity.Nickname;
import entity.Roles;
import entity.UserStatus;
import exceptions.UserNotExistEx;
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
import java.util.ResourceBundle;

public class K_other implements Initializable {

    /*******************************************************************/
    /**                       Class Attribute                         **/

    private SystemInterface sysInt = new FacadeSubSystem();
    private RoleStatus rolStatInt = new FacadeSubSystem();
    private UserProfileService usInt = new FacadeSubSystem();

    private BasicUserInfo basic;
    private RestrictUserInfo restrict;
    private Roles roles;
    private UserStatus status;
    /*******************************************************************/

    //=================================================================
    //Top object
    @FXML
    Button backHome;

    //=================================================================
    //Left object
    @FXML
    TextField nickWork;

    @FXML
    Button makeRenter, removeRentership, makeTenant, removeTenant;

    //=================================================================
    //information view sector
    @FXML
    Button getStatus, getRole, getPubD, getPrD;

    @FXML
    ImageView avatar;

    @FXML
    CheckBox tenant, renter;
    @FXML
    RadioButton man, woman;

    @FXML
    TextField nick, email, tc, socStat, name, surname;

    @FXML
    DatePicker birthday;

    @FXML
    ChoiceBox usStat;

    @FXML
    TextField cel, address, cityBirth, nat;

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
                nick, email, tc, socStat, name, surname, birthday, usStat,
                cel, address, cityBirth, nat};

        //information view sector setup
        for (Node e:disableVector) {    //struttura foreach in java
            e.setDisable(true);
            e.setStyle("-fx-opacity: 1");
        }
        //to restore white shape
        birthday.getEditor().setStyle("-fx-opacity: 1");

        usStat.getItems().addAll("ACTIVE","INACTIVE","CANCELLED","BANNED");
        usStat.setValue("ACTIVE");
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
    public void refreshStatus(ActionEvent actionEvent) {

        outLabel.setText("refreshStatus");
        loadStatus();
    }

    @FXML
    public void refreshRole(ActionEvent actionEvent) {
        outLabel.setText("refreshRole");
        loadRole();

    }
    @FXML
    public void refreshPubD(ActionEvent actionEvent) {
        outLabel.setText("refreshPubD");
        loadPublic();
    }
    @FXML
    public void refreshPrD(ActionEvent actionEvent) {
        outLabel.setText("refreshPrD");
        loadPrivate();
    }

    @FXML
    public void setRenter(ActionEvent actionEvent) {
        outLabel.setText("setRenter");
        setRent();
    }
    @FXML
    public void removeRenter(ActionEvent actionEvent) {
        outLabel.setText("removeRenter");
        removeRent();

    }
    @FXML
    public void setTenant(ActionEvent actionEvent) {

        outLabel.setText("setTenant");
        setTen();
    }
    @FXML

    public void removeTenant(ActionEvent actionEvent) {

        outLabel.setText("removeTenant");
        removeTen();
    }

    private void loadPublic(){
        try {
            System.out.println("il nick letto è =" + nickWork.getText());
            basic = usInt.getBasicUserInfo(new Nickname(nickWork.getText()));
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
            System.out.println("il nick letto è =" + nickWork.getText());
            restrict = usInt.getRestrictedUserInfo(new Nickname(nickWork.getText()));
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
            System.out.println("il nick letto è =" + nickWork.getText());
            roles= rolStatInt.getRoles(new Nickname(nickWork.getText()));
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

    private void loadStatus(){
        try {
            status= rolStatInt.getStatus(new Nickname(nickWork.getText()));
            usStat.setValue(status.name());

        }catch (UserNotExistEx ex){
            outLabel.setText("PROBLEMI CON IL NICKNAME, non più trovato");
        }
    }

    private void setRent(){
        try{
            roles= rolStatInt.getRoles(new Nickname(nickWork.getText()));
            roles.setRenter();
            System.out.println("il ruolo attuale è =" + roles);
            renter.setSelected(true);
        }catch (UserNotExistEx ex){
            outLabel.setText("PROBLEMI CON IL NICKNAME, non più trovato");
        }
    }

    private void removeRent(){
        try {
            roles= rolStatInt.getRoles(new Nickname(nickWork.getText()));
            roles.resetRenter();
            System.out.println("il ruolo attuale è =" + roles);
            renter.setSelected(false);
        }catch (UserNotExistEx ex){
            outLabel.setText("PROBLEMI CON IL NICKNAME, non più trovato");
        }
    }

    private void setTen(){
        try {
            roles= rolStatInt.getRoles(new Nickname(nickWork.getText()));
            roles.setTenant();
            System.out.println("il ruolo attuale è =" + roles);
            tenant.setSelected(true);
        }catch (UserNotExistEx ex){
            outLabel.setText("PROBLEMI CON IL NICKNAME, non più trovato");
        }
    }

    private void removeTen(){
        try {
            roles= rolStatInt.getRoles(new Nickname(nickWork.getText()));
            roles.resetTenant();
            System.out.println("il ruolo attuale è =" + roles);
            tenant.setSelected(false);
        }catch (UserNotExistEx ex){
            outLabel.setText("PROBLEMI CON IL NICKNAME, non più trovato");
        }
    }

}

