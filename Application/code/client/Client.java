package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application {

    private static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage=primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/layout/fxml/login.fxml"));
        this.primaryStage.setTitle("Login");
        this.primaryStage.setScene(new Scene(root, 800, 500));
        this.primaryStage.setResizable(false);
        this.primaryStage.show();
    }

    private class Session{

    }
}
