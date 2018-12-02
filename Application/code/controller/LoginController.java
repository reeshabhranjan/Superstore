package controller;

import classes.Credential;
import client.App;
import client.Client;
import client.MessagePopup;
import client.PopupWindow;
import database.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController extends Controller{

    private Client client;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    private Button guestLoginButton;

    @FXML
    private ComboBox loginComboBox;

//    public void display(MouseEvent event){
//        label.setText("Browse store!");
//    }

    private boolean textFieldValidator(){
        boolean cond1 = usernameTextField.getText().length()>0;
        boolean cond2 = passwordTextField.getText().length()>0;
        return  cond1&&cond2;
    }

    @FXML
    public void login(MouseEvent event) throws java.io.IOException
    {
//        App.loadPopup("message_popup","error","message");
//        MessagePopupController messagePopupController= (MessagePopupController) App.getController();
//        messagePopupController.messageLabel.setText("error");
        Credential credential;
        if(textFieldValidator() && loginComboBox.getValue()!=null) {
            credential=new Credential(usernameTextField.getText(),passwordTextField.getText());
//            System.out.println(app);
            App.login(credential);
        }
        else
        {
            //TODO Invalid input popup.
        }
    }

    @FXML
    public void register(MouseEvent event) throws java.io.IOException
    {
        Credential credential;
        String name = nameTextField.getText();
        if(textFieldValidator() && !name.equals("")) {
            credential=new Credential(usernameTextField.getText(),passwordTextField.getText());
//            System.out.println(app);
            App.register(credential,name);
        }
        else
        {
            //TODO Invalid input popup.
        }
    }
    @FXML
    public void showRegisterPanel(MouseEvent event) throws java.io.IOException
    {
        App.loadScreen("end_user_register","Register", "end_user");
    }

    @FXML
    public void showGuestUserDashboardPanel(MouseEvent event) throws java.io.IOException
    {
        App.loadScreen("guest_user_dashboard","Dashboard", "guest_user");
    }

//    @FXML
//    public void demoContextMenu()
//    {
//        ContextMenu contextMenu = new ContextMenu();
//        MenuItem item1 = new MenuItem("option1");
//        MenuItem item2 = new MenuItem("option2");
//        contextMenu.getItems().addAll(item1,item2);
//        loginButton.setContextMenu(contextMenu);
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
