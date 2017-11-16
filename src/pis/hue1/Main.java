package pis.hue1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("codec.fxml"));
        Parent root = loader.load();
        CodecGUI controller = loader.getController();
        // Set data in the controller
        controller.setCodec1(new Wuerfel());
        controller.setCodec2(new Wuerfel());
        primaryStage.setTitle("Verschluesselung");
        //TODO: set min size
        primaryStage.setScene(new Scene(root, 300, 275));
        //primaryStage.setFullScreen(true);
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(700);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
