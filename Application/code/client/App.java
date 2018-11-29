package client;

import classes.Message;
import classes.RegisteredUser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    RegisteredUser registeredUser;
    Session session;
    Stage primaryStage;

    public void loadScreen(String fileName,String title){

        Parent root;
        try {
            root = FXMLLoader.load(App.class.getResource("/layout/fxml/"+fileName+".fxml"));
            this.primaryStage.setTitle(title);

            int width=800;
            int height=500;

            if(fileName=="login"){
                width=721;
                height=465;
            }

            this.primaryStage.setScene(new Scene(root, width, height));
            this.primaryStage.setResizable(false);
            this.primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendMessage(Message message){

        //session.sendData(message);
    }

//    public App(){
//
//        //TODO initialise session
//        launch();
//    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage=primaryStage;
        loadScreen("login","InfinityStore");
    }

    public static void main(String[] args){
        launch(args);
    }
}
