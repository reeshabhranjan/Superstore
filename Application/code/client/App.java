package client;

import classes.Message;
import classes.RegisteredUser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import controller.*;

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

            FXMLLoader fxmlLoader = new FXMLLoader();
            AnchorPane anchorPane=fxmlLoader.load(App.class.getResource("/layout/fxml/"+fileName+".fxml").openStream());
            Controller controller=fxmlLoader.getController();
            controller.setApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Message sendMessage(Message message){

        session.sendData(message);
        return session.getData();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage=primaryStage;
        loadScreen("login","InfinityStore");
    }

    public static void main(String[] args){
        //TODO initialise session
        launch(args);
    }
}
