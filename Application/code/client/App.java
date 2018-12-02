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
import java.util.Arrays;
import java.util.List;

import static java.io.File.separator;

public class App extends Application {

//    private static RegisteredUser registeredUser;
    private static Controller controller;
    private static Session session;
    private static Stage primaryStage;
    private static App app;
    private static String userType;
    private static Stage popupStage;
    private static String message;
    private static ArrayList<Object> objects = new ArrayList<>();

    public static ArrayList<Object> getObjects() {
        return objects;
    }
    public static void resetObjects() {
        objects = new ArrayList<>();
    }

    public static String getMessage() {
        return message;
    }

    public static void setController(Controller controller) {
        App.controller = controller;
    }

    public static Stage getPopupStage() {
        return popupStage;
    }

    public static Controller getController() {
        return controller;
    }

    public static void loadScreen(String fileName, String title){

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
//            controller = null;
//            switch (controllerType)
//            {
//                case "login":
//                    controller = new LoginController();
//                    fxmlLoader.setController(controller);
//                    break;
//                case "end_user":
//                    System.out.println("end user dahsboard case");
//                    controller = new EndUserController();
//                    fxmlLoader.setController(controller);
//                    break;
//                case "guest_user":
//                    System.out.println("end user dahsboard case");
//                    controller = new GuestUserController();
//                    fxmlLoader.setController(controller);
//                    break;
//                case "super_user":
//                    System.out.println("end user dahsboard case");
//                    controller = new SuperuserController();
//                    fxmlLoader.setController(controller);
//                    break;
//                case "warehouse_admin":
//                    System.out.println("end user dahsboard case");
//                    controller = new WarehouseAdminController();
//                    fxmlLoader.setController(controller);
//                    break;
//                case "store_admin":
//                    System.out.println("end user dahsboard case");
//                    controller = new StoreAdminController();
//                    fxmlLoader.setController(controller);
//                    break;
//
//            }
////            System.out.println(controller);
//            return controller;
//            controller.setApp(app);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Message sendMessage(Message message){

        session.sendData(message);
        return session.getData();
    }
    public static void login(Credential credential, String userType1)
    {
        ArrayList objects = new ArrayList();
        objects.add(credential);
        objects.add(userType1);
        loadScreen("end_user_dashboard","Dashboard"); //todo remove this line
        Message dataFromServer=sendMessage(new Message("login",objects));

        boolean login = (boolean) dataFromServer.getObjects().get(0);
        if(login)
        {
            String userType= (String) dataFromServer.getObjects().get(1);
            switch (userType)
            {
                case "enduser":
                    loadScreen("end_user_dashboard","Dashboard");
                    userType="enduser";
                    break;
                case "superuser":
                    loadScreen("superuser_dashboard","Dashboard");
                    userType="superuser";
                    break;
                case "storeadmin":
                    loadScreen("store_admin_dashboard","Dashboard");
                    userType="storeadmin";
                    break;
                case "warehouseadmin":
                    loadScreen("warehouse_admin_dashboard","Dashboard");
                    userType="warehouseadmin";
                    break;
            }
        }
        else
        {
            // TODO popup for no login
        }

    }

    public static void register(Credential credential, String name)
    {
        ArrayList objects = new ArrayList();
        objects.add(credential);
        objects.add(name);
        Message dataFromServer=sendMessage(new Message("register",objects));
        //ToDo popup for already existing username
//        registeredUser=(RegisteredUser)dataFromServer.getObjects().get(0);
//        loadScreen("login","Login", "login");
    }

    public static void logout()
    {
        session.sendData(new Message("logout",new ArrayList()));
//        loadScreen("login","Login", "login");
    }

    public static ArrayList<String> decode(String string, String s) {
        List<String> list = (List<String>) Arrays.asList(string.split(s));
//            System.out.println(list.get(0)+" "+list.get(1));
        return (ArrayList<String>) list;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage=primaryStage;
        primaryStage.setOnCloseRequest(event -> {
//            System.out.println("works");
            session.sendData(new Message("exit",new ArrayList()));
        });
//        session = new Session("192.168.1.101",1400);
        loadScreen("login","InfinityStore");
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
