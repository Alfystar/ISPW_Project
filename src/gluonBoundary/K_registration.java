package gluonBoundary;

import bean.UserInfoRegister;
import control.FacadeSubSystem;
import entity.*;
import exceptions.UserNotExistEx;
import exceptions.WrongParameters;
import gluonBoundary.utilityClass.Bean2User;
import gluonBoundary.utilityClass.DigitalIcon;
import interfaces.RoleStatus;
import interfaces.SystemInterface;
import interfaces.UserProfileService;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

public class K_registration implements Initializable{

    //=================================================================
    //General information Tab
    @FXML
    private RadioButton man, woman;
    @FXML
    private TextField nick, email;
    @FXML
    private PasswordField newPw, confPw;
    @FXML
    private DatePicker birthday;
    @FXML
    private TextField name, surname, tc, cityBirth;
    @FXML
    private RadioButton av1, av2, av3, av4, av5, av6;
    @FXML
    private ImageView avatar;
    //=================================================================
    //Questionary tab
    @FXML
    private TextField answ1, answ2, answ3, answ4;
    //=================================================================
    //bottom bar
    @FXML
    private Label outLabel;

    //=================================================================
    //Personal Information tab
    @FXML
    private ProgressBar progress;
    @FXML
    private ImageView statusIco;
    @FXML
    private Button register;


    /*** Class Attribute ***/
    private Avatar iconAvatar = new Avatar();
    private DigitalIcon compleateIcon = new DigitalIcon();
    private SystemInterface sysInt = new FacadeSubSystem();
    private RoleStatus rolStatInt = new FacadeSubSystem();
    private UserProfileService usInt = new FacadeSubSystem();

    @Override
    public void initialize(URL url, ResourceBundle rb){
        register.setDisable(true);  //si attiva in stepCheck, quando si ragiunge il 100%
    }

    @FXML
    public void registerPush(ActionEvent event) throws IOException{

        Date bDate = java.sql.Date.valueOf(birthday.getValue());
        GregorianCalendar bGc = stringToGregCal(bDate.toString());

        Gender g;
        if(man.isSelected()) g = Gender.MAN;
        else g = Gender.WOMAN;

        String[] answs = {answ1.getText(), answ2.getText(), answ3.getText(), answ4.getText()};
        Questionary q = new Questionary(answs);

        //(Name name, Name surname, TaxCode cf, Nickname nickname, Email email, GregorianCalendar birthday, Gender gender, Questionary answers, PW pw){
        UserInfoRegister infoReg = new UserInfoRegister(new Name(name.getText()), new Name(surname.getText()), new TaxCode(tc.getText()), new Nickname(nick.getText()), new Email(email.getText()), bGc, g, q, new PW(newPw.getText()), new SurfaceAddress(cityBirth.getText()));

        try{
            usInt.createUser(new Nickname(nick.getText()), infoReg);
        }catch(WrongParameters|ClassNotFoundException e){
            outLabel.setText("Un parametro non è unico!");
            e.printStackTrace();
            return;
        }

        try{
            sysInt.setAvatar(new Nickname(nick.getText()), this.radioSelect());
        }catch(UserNotExistEx e){
            e.printStackTrace();
            return;
        }


        //Parent userParent = FXMLLoader.load(getClass().getResource("fxmlSrc/userPane.fxml"));

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

    @FXML
    public void avatarChange(ActionEvent event){
        iconAvatar.setMyIcon(this.radioSelect());
        avatar.setImage(iconAvatar.getMyIcon());
        outLabel.setText(iconAvatar.getAvatarName());
    }

    private int radioSelect(){
        RadioButton[] radioNode = {av1, av2, av3, av4, av5, av6};
        for(int i = 0; i < radioNode.length; i++){
            if(radioNode[i].isSelected()) return i;
        }
        return 0;
    }

    @FXML
    public Boolean stepCheck(Event event){

        TextField[] TxT = {name, surname, tc, nick, email, cityBirth};
        //RadioButton[] Gender = {man, woman};
        TextField[] answ = {answ1, answ2, answ3, answ4};
        //PasswordField[] PW = {newPw, confPw};
        // DatePicker birthday

        //calcolo lunghezza totale
        int len = TxT.length + 1 + answ.length + 1 + 1;

        //calcolo attuale stato
        int complete = 0;

        /**     Text field check write    **/
        for(TextField f : TxT){
            if(!f.getText().equals("")) complete++;
        }

        /**     Gender check     **/
        if(man.isSelected() || woman.isSelected()) complete++;


        /**     Answare check     **/
        for(TextField f : answ){
            if(!f.getText().equals("")) complete++;
        }

        /**     PW check     **/
        if(newPw.getText().equals(confPw.getText()) && !newPw.getText().equals("")) complete++;


        /**     Validy date Check     **/
        try{
            Date bDate = java.sql.Date.valueOf(birthday.getValue());
            GregorianCalendar bGc = stringToGregCal(bDate.toString());
            Calendar tenYMore = GregorianCalendar.getInstance();
            tenYMore.add(Calendar.YEAR, 10);

            if(bGc.get(Calendar.YEAR) > 1900 && bGc.get(Calendar.YEAR) < tenYMore.get(Calendar.YEAR)) complete++;
        }catch(Exception e){
            //e.printStackTrace();
        }

        /**        Grafica stato e abilita pulsante registrazione         **/
        float percent = ((float) complete) / ((float) len);
        progress.setProgress(percent);

        if(percent == 1){
            compleateIcon.setState(true);
            register.setDisable(false);
        }else compleateIcon.setState(false);
        statusIco.setImage(compleateIcon.getIcon());

        //System.out.println("stepCheck "+percent+ " len = "+ len + " complete= "+complete);

        if(percent == 1) return true;
        else return false;
    }


    private GregorianCalendar stringToGregCal(String s){
        String[] splitDate = s.split("-");
        int year = Integer.parseInt(splitDate[0]);
        int month = Integer.parseInt(splitDate[1]);
        int days = Integer.parseInt(splitDate[2]);
        GregorianCalendar gc = new GregorianCalendar(year, month - 1, days);
        return gc;

    }

    private String gregCalToString(GregorianCalendar gc){
        int anno = gc.get(GregorianCalendar.YEAR);
        int mese = gc.get(GregorianCalendar.MONTH) + 1;
        int giorno = gc.get(GregorianCalendar.DATE);
        String s = anno + "-" + mese + "-" + giorno;
        return s;
    }
}
