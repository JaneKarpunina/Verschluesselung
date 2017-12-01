package pis.hue1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


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
        Scene scene  = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(700);
        scene.getStylesheets().add("stylesheet.css");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
