package gluonBoundary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import gluonBoundary.utilityClass.*;


public class StandAloneMain extends Application {



    public static void main(String[] args) throws Exception{
        int nThread =10;
        StandAloneMain stand = new StandAloneMain();
        //todo Inserire inizializzazione e tread vari
        Thread t;
        FakeUser f;
        for(int fakeType = 0; fakeType <2; fakeType++){
            for(int i = 0; i <nThread ; i++){
                f = new FakeUser(fakeType);
                t = new Thread(f);
                t.start();
            }
            System.out.println("Thread type "+ fakeType+" created");
        }


        launch(args);
        System.exit(0);
    }

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxmlSrc/homeStandAlone.fxml"));
        Scene scene = new Scene(root);

        stage.setTitle("FERSA StandAlone");
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/ImageFile/Icon/Fersa-logo.png")));
        stage.show();
    }





}
