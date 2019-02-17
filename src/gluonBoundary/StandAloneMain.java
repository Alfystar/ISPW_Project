package gluonBoundary;

import gluonBoundary.utilityClass.FakeUser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class StandAloneMain extends Application{

    public static void main(String[] args) throws Exception{
        if(args.length > 0){
            int nThread = 10;
            Thread t;
            FakeUser f;
            for(int fakeType = 0; fakeType < 4; fakeType++){
                for(int i = 0; i < nThread; i++){
                    f = new FakeUser(fakeType);
                    t = new Thread(f);
                    t.start();
                }
            }
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
