package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

public class MessagePopup extends Application {
    private static String message;
    private static Stage primaryStage;

    public MessagePopup(Parent root, String message) {
        this.message = message;

//        launch(new String[2]);
    }

    public static String getMessage() {
        return message;
    }

    public static void closeStage(){
        primaryStage.close();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage=primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(MessagePopup.class.getResource("layout/fxml/message_popup.fxml"));
        Parent root = fxmlLoader.load();

        primaryStage.setTitle("Message!");

        int width=400;
        int height=200;

        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.setResizable(false);
        primaryStage.show();

//        primaryStage.setOnCloseRequest(event -> {
//            setLive(false);
//        });
    }
}
