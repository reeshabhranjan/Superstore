package client;

import classes.Credential;
import classes.Message;
import classes.RegisteredUser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import controller.*;

import java.io.IOException;
import java.util.ArrayList;

public class App extends Application {

    private RegisteredUser registeredUser;
    private Session session;
    private Stage primaryStage;

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
            fxmlLoader.load(App.class.getResource("/layout/fxml/"+fileName+".fxml").openStream());
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
    public void login(Credential credential)
    {
        ArrayList objects = new ArrayList();
        objects.add(credential);
        Message dataFromServer=sendMessage(new Message("login",objects));
        registeredUser=(RegisteredUser)dataFromServer.getObjects().get(0);
        loadScreen("end_user_dashboard","Dashboard");
    }

    public void logout()
    {
        session.sendData(new Message("logout",new ArrayList()));
        loadScreen("login","Login");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage=primaryStage;
        primaryStage.setOnCloseRequest(event -> {
            System.out.println("chalbe");
            session.sendData(new Message("exit",new ArrayList()));
        });
        loadScreen("login","InfinityStore");

        session = new Session("server",1234);
    }

    public static void main(String[] args){
        //TODO initialise session
        launch(args);
    }
}
