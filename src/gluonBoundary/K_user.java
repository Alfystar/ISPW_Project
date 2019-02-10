package gluonBoundary;

import bean.BasicUserInfo;
import bean.RestrictUserInfo;
import control.FacadeSubSystem;
import entity.*;
import exceptions.UserBannedEx;
import exceptions.UserNotExistEx;
import exceptions.WrongParameters;
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
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

public class K_user implements Initializable{


    //=================================================================
    //Top object
    @FXML
    private Button backHome;
    //=================================================================
    //information view sector
    @FXML
    private ImageView avatar;
    @FXML
    private Button getRole, getPubD, getPrD;
    @FXML
    private CheckBox tenant, renter;
    @FXML
    private RadioButton man, woman;
    @FXML
    private TextField nick, email, tc, socStat, name, surname;
    @FXML
    private DatePicker birthday;
    @FXML
    private TextField cel, address, cityBirth, nat;
    //=================================================================
    //change sector
    @FXML
    private ChoiceBox paramChange;
    @FXML
    private TextField strChange;
    @FXML
    private Button sendUpdate;
    @FXML
    private RadioButton av1, av2, av3, av4, av5, av6;
    //=================================================================
    //password sector
    @FXML
    private PasswordField oldPw, newPw, confPw;
    @FXML
    private Button changePw;
    @FXML
    private ImageView iconCheck;
    //=================================================================
    //bottom bar
    @FXML
    private Label outLabel;
    @FXML
    private ProgressBar progress;

    /*** Class Attribute ***/
    private SystemInterface sysInt = new FacadeSubSystem();
    private RoleStatus rolStatInt = new FacadeSubSystem();
    private UserProfileService usInt = new FacadeSubSystem();
    private Bean2User bean;
    private BasicUserInfo basic;
    private RestrictUserInfo restrict;
    private Roles roles;
    private Avatar iconAvatar = new Avatar();
    private DigitalIcon pwCheck = new DigitalIcon();

    @Override
    public void initialize(URL url, ResourceBundle rb){
        Node[] disableVector = {
                tenant, renter, man, woman,
                nick, email, tc, socStat, name, surname, birthday,
                cel, address, cityBirth, nat};

        //information view sector setup
        for(Node e : disableVector){    //struttura foreach in java
            e.setDisable(true);
            e.setStyle("-fx-opacity: 1");
        }
        //to restore white shape
        birthday.getEditor().setStyle("-fx-opacity: 1");


        //change sector setup
        paramChange.getItems().addAll("Social Status", "Email", "Phone Number", "Nationality", "Surface Address");
        paramChange.setValue("Social Status");

        //password sector setup

    }

    @FXML
    public void homeScene(ActionEvent event) throws IOException{
        //prepare new scene to replace
        Parent homeParent = FXMLLoader.load(getClass().getResource("fxmlSrc/homeStandAlone.fxml"));
        Scene homeScene = new Scene(homeParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //set new scene
        window.setScene(homeScene);
    }

    @FXML
    public void updateUser(ActionEvent actionEvent){
        RadioButton[] radioNode = {av1, av2, av3, av4, av5, av6};
        outLabel.setText("updateUser click");
        try{
            if(!strChange.getText().equals("")) //ho inserito testo, modifico un parametro
            {
                sysInt.changeNotAnagraphicData(bean.getNick(), fillDataMod());

            }else{     //non ho inserito testo, modifico l'immagine
                sysInt.setAvatar(bean.getNick(), this.radioSelect());
            }
        }catch(UserNotExistEx e){
            outLabel.setText("PROBLEMI CON IL NICKNAME, non più trovato");
            return;
        }catch(WrongParameters e){
            e.printStackTrace();
        }
    }

    private ModifyDataString fillDataMod() throws WrongParameters{
        String param = (String) paramChange.getValue();
        if(param.equals("Social Status")){
            return new SocialStatus(strChange.getText());

        }else if(param.equals("Email")){
            return new Email(strChange.getText());

        }else if(param.equals("Phone Number")){
            return new PhoneNumber(strChange.getText());

        }else if(param.equals("Nationality")){
            return new Nationality(strChange.getText());

        }else if(param.equals("Surface Address")){
            return new SurfaceAddress(strChange.getText());
        }
        throw new WrongParameters("Selezionato male!!!");
    }

    @FXML
    public void changeTyped(KeyEvent event){
        RadioButton[] radioNode = {av1, av2, av3, av4, av5, av6};

        if(!strChange.getText().equals("")){
            for(RadioButton r : radioNode) r.setDisable(true);
        }else{
            for(RadioButton r : radioNode) r.setDisable(false);
        }
    }

    @FXML
    public void avatarView(ActionEvent actionEvent){
        iconAvatar.setMyIcon(this.radioSelect());
        avatar.setImage(iconAvatar.getMyIcon());
        outLabel.setText(iconAvatar.getAvatarName());
    }


    @FXML
    public void refreshRole(ActionEvent actionEvent){

        outLabel.setText("refreshRole click");
        loadRole();
    }

    @FXML
    public void refreshPubD(ActionEvent actionEvent){

        outLabel.setText("refreshPubD click");
        loadPublic();
    }

    @FXML
    public void refreshPrD(ActionEvent actionEvent){

        outLabel.setText("refreshPrD click");
        loadPrivate();
    }

    public void delMyself(ActionEvent actionEvent) throws IOException{
        try{
            usInt.cancelUser(bean.getNick());
        }catch(UserNotExistEx us){
            outLabel.setText("PROBLEMI CON IL NICKNAME, non più trovato");
            return;
        }

        homeScene(actionEvent);
    }

    @FXML
    public void changePwEvent(ActionEvent actionEvent){

        outLabel.setText("changePw click");
        changePw();
    }

    private void changePw(){
        try{
            Nickname nick = new Nickname(bean.getNick());
            PW oldP = new PW(oldPw.getText());
            PW newP = new PW(newPw.getText());

            if(sysInt.login(nick, oldP)){
                if(newP.getPw().equals(confPw.getText()) && !newP.getPw().equals("")){
                    sysInt.changePassword(nick, newP, oldP);
                }else outLabel.setText("LA CONFERMA DELLA NUOVA PASSWORD NON E' CORRETTA");

            }else outLabel.setText("LA PASSWORD INSERITA NON E' CORRETTA");

        }catch(UserNotExistEx e){
            outLabel.setText("NICKNAME NON TROVATO");
        }catch(UserBannedEx e){
            outLabel.setText("NICKNAME ORA BANNATO");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void checkPW(KeyEvent event){
        if(newPw.getText().equals(confPw.getText()) && !newPw.getText().equals("")) pwCheck.setState(true);
        else pwCheck.setState(false);
        iconCheck.setImage(pwCheck.getIcon());
    }

    public void setBean(Bean2User bean){
        this.bean = bean;

        nick.setText(bean.getNick().get());
    }


    private void loadPublic(){

        try{
            basic = usInt.getBasicUserInfo(bean.getNick());
        }catch(UserNotExistEx e){
            outLabel.setText("PROBLEMI CON IL NICKNAME, non più trovato");
        }
        nick.setText(basic.getNickname().get());
        email.setText(basic.getEmail().get());
        tc.setText(basic.getTaxCode().get());
        socStat.setText(basic.getsocialStatus().get());
        name.setText(basic.getName().get());
        surname.setText(basic.getSurname().get());
        if(basic.getGender().equals(Gender.MAN)){
            man.setSelected(true);
        }else{
            woman.setSelected(true);
        }
        iconAvatar.setMyIcon(basic.getAvatar().getMyIconIndex());
        avatar.setImage(basic.getAvatar().getMyIcon());
        this.setRadioSelect(basic.getAvatar().getMyIconIndex());

        //gestione birthday: GregorianCalendar -> Date -> LocalDate ->DatePicker

        setDatePicker(basic.getBirthday(), birthday);

    }

    private void loadPrivate(){
        try{
            restrict = usInt.getRestrictedUserInfo(bean.getNick());
        }catch(UserNotExistEx e){
            outLabel.setText("PROBLEMI CON IL NICKNAME, non più trovato");
        }
        cel.setText(restrict.getPhoneNumber().get());
        cityBirth.setText(restrict.getCityOfBirth().get());
        address.setText(restrict.getAddress().get());
        nat.setText(restrict.getNationality().get());
    }

    private void loadRole(){
        try{
            roles = rolStatInt.getRoles(bean.getNick());
        }catch(UserNotExistEx e){
            outLabel.setText("PROBLEMI CON IL NICKNAME, non più trovato");
        }
        if(roles.isTenant()){
            tenant.setSelected(true);
        }
        if(roles.isRenter()){
            renter.setSelected(true);
        }
    }

    private String gregCalToString(GregorianCalendar gc){
        int anno = gc.get(GregorianCalendar.YEAR);
        int mese = gc.get(GregorianCalendar.MONTH) + 1;
        int giorno = gc.get(GregorianCalendar.DATE);
        String s = anno + "-" + mese + "-" + giorno;
        return s;
    }

    private int radioSelect(){
        RadioButton[] radioNode = {av1, av2, av3, av4, av5, av6};
        for(int i = 0; i < radioNode.length; i++){
            if(radioNode[i].isSelected()) return i;
        }
        return 0;
    }

    private void setRadioSelect(int i){
        RadioButton[] radioNode = {av1, av2, av3, av4, av5, av6};
        radioNode[i].setSelected(true);
    }


    //todo: inserire l'eliminazione volontaria del profilo! Deve attivare cancelUser!

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert){
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault())
                .toLocalDate();
    }


    public void setDatePicker(GregorianCalendar gc, DatePicker dp){
        java.util.Date date = (gc.getTime());
        LocalDate newDate = convertToLocalDateViaInstant(date);
        dp.setValue(newDate);
    }
}
