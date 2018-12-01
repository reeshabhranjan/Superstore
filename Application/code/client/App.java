package client;

import classes.Credential;
import classes.Message;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import controller.*;

import java.io.IOException;
import java.util.ArrayList;

public class App extends Application {

//    private static RegisteredUser registeredUser;
    private static Controller controller;
    private static Session session;
    private static Stage primaryStage;
    private static App app;
    private static String userType;

    public static void setController(Controller controller) {
        App.controller = controller;
    }

    public static Controller getController() {
        return controller;
    }

    public static Controller loadScreen(String fileName, String title){

        Parent root;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/layout/fxml/"+fileName+".fxml"));
            root = fxmlLoader.load();

            primaryStage.setTitle(title);

            int width=800;
            int height=500;

            if(fileName=="login"){
                width=721;
                height=465;
            }

            primaryStage.setScene(new Scene(root, width, height));
            primaryStage.setResizable(false);
            primaryStage.show();

//            FXMLLoader fxmlLoader = new FXMLLoader();
//            fxmlLoader.load(App.class.getResource("/layout/fxml/"+fileName+".fxml").openStream());
            Controller controller=fxmlLoader.getController();
//            System.out.println(controller);
            return controller;
//            controller.setApp(app);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Message sendMessage(Message message){

        session.sendData(message);
        return session.getData();
    }
    public static void login(Credential credential)
    {
        ArrayList objects = new ArrayList();
        objects.add(credential);
        loadScreen("end_user_dashboard","Dashboard");
//        Message dataFromServer=sendMessage(new Message("login",objects));
//        String userType= (String) dataFromServer.getObjects().get(0);
//        switch (userType)
//        {
//            case "enduser":
//                loadScreen("end_user_dashboard","Dashboard");
//                userType="enduser";
//                break;
//            case "superuser":
//                loadScreen("superuser_dashboard","Dashboard");
//                userType="superuser";
//                break;
//            case "storeadmin":
//                loadScreen("store_admin_dashboard","Dashboard");
//                userType="storeadmin";
//                break;
//            case "warehouseadmin":
//                loadScreen("warehouse_admin_dashboard","Dashboard");
//                userType="warehouseadmin";
//                break;
//        }

    }

    public static void register(Credential credential, String name)
    {
        ArrayList objects = new ArrayList();
        objects.add(credential);
        objects.add(name);
        Message dataFromServer=sendMessage(new Message("register",objects));
        //ToDo popup for already existing username
//        registeredUser=(RegisteredUser)dataFromServer.getObjects().get(0);
        loadScreen("login","Login");
    }

    public static void logout()
    {
        session.sendData(new Message("logout",new ArrayList()));
        loadScreen("login","Login");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage=primaryStage;
        primaryStage.setOnCloseRequest(event -> {
//            System.out.println("works");
            session.sendData(new Message("exit",new ArrayList()));
        });
        loadScreen("login","InfinityStore");

//        session = new Session("192.168.43.55",1400);
//        session = new Session("localhost",1400);
//        System.out.println("request sent");
//        Message messageResponse = sendMessage(new Message("debugging",null));
//        System.out.println(messageResponse.getObjects().get(0));

    }

    public static void main(String[] args){
        //TODO initialize session
        launch(args);
        //192.168.43.55
    }
}
