package gluonBoundary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class K_admin implements Initializable {

    /*******************************************************************/
    /**                       Class Attribute                         **/


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
    Button ban, deBan, destroy;

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

        usStat.getItems().addAll("Active","inActive","Cancelled","Banned");
        usStat.setValue("Active");
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
    }
    @FXML
    public void refreshRole(ActionEvent actionEvent) {
        outLabel.setText("refreshRole");

    }
    @FXML
    public void refreshPubD(ActionEvent actionEvent) {
        outLabel.setText("refreshPubD");

    }
    @FXML
    public void refreshPrD(ActionEvent actionEvent) {
        outLabel.setText("refreshPrD");

    }
    @FXML
    public void banUser(ActionEvent actionEvent) {
        outLabel.setText("banUser");

    }
    @FXML
    public void deBanUser(ActionEvent actionEvent) {
        outLabel.setText("deBanUser");

    }
    @FXML
    public void destroyUser(ActionEvent actionEvent) {
        outLabel.setText("destroyUser");

    }
}
