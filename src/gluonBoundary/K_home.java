package gluonBoundary;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class K_home  extends Application {

    private Parent home;

    private Stage windows;

    public static void main(String[] args) {

        //todo Inserire inizializzazione e tread vari

        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        windows=primaryStage;
        home = FXMLLoader.load(getClass().getResource("fxmlSrc/homeStandAlone.fxml"));
        windows.setTitle("FERSA StandAlone");
        windows.setScene(new Scene(home));
        windows.getIcons().add(new Image(getClass().getResourceAsStream("Icon/Fersa-logo.png")));
        windows.show();
    }

    //@FXML
    public void adminScene(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
       // label.setText("Hello World!");
        //Here I want to swap the screen!

        //Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)event.getSource()).getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("fxmlSrc/adminPane.fxml"));


        windows.setScene(new Scene(root));

    }

}
