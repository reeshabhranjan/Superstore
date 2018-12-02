package client;

import controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PopupWindow extends Application {

    private String fxmlFileName;
    private String title;
    private Stage primaryStage;
    private String popupType;
    private Controller controller;

    public Controller getController() {
        return controller;
    }

    public PopupWindow(String fxmlFileName, String title, String popupType) {
        this.fxmlFileName = fxmlFileName;
        this.title = title;
        this.popupType=popupType;
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage=primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(MessagePopup.class.getResource("layout/fxml/"+fxmlFileName+".fxml"));
        Parent root = fxmlLoader.load();

        primaryStage.setTitle(title);

        int width=800;
        int height=500;

        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.setResizable(false);
        primaryStage.show();

        controller=null;
        switch (popupType)
        {
            case "login":
                controller = new LoginController();
                fxmlLoader.setController(controller);
                break;
            case "end_user":
                System.out.println("end user dahsboard case");
                controller = new EndUserController();
                fxmlLoader.setController(controller);
                break;
            case "guest_user":
                System.out.println("end user dahsboard case");
                controller = new GuestUserController();
                fxmlLoader.setController(controller);
                break;
            case "super_user":
                System.out.println("end user dahsboard case");
                controller = new SuperuserController();
                fxmlLoader.setController(controller);
                break;
            case "warehouse_admin":
                System.out.println("end user dahsboard case");
                controller = new WarehouseAdminController();
                fxmlLoader.setController(controller);
                break;
            case "store_admin":
                System.out.println("end user dahsboard case");
                controller = new StoreAdminController();
                fxmlLoader.setController(controller);
                break;

        }
//            System.out.println(controller);

    }
}
