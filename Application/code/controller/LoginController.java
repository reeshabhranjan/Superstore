package controller;

import classes.Credential;
import client.App;
import client.Client;
import database.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
        App.loadScreen("end_user_register","Register");
    }

    @FXML
    public void showGuestUserDashboardPanel(MouseEvent event) throws java.io.IOException
    {
        App.loadScreen("guest_user_dashboard","Dashboard");
    }
}
