/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;

import classes.Credential;
import client.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author reesh
 */
public class LoginController implements Initializable {

    @FXML
    private ComboBox<?> loginComboBox;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private Button guestLoginButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void showRegisterPanel(MouseEvent event) {
    }

    @FXML
    private void showGuestUserDashboardPanel(MouseEvent event) {
    }

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
            String userType = (String) loginComboBox.getValue();
            credential=new Credential(usernameTextField.getText(),passwordTextField.getText());
//            System.out.println(app);
            App.login(credential,userType);
        }
        else
        {
            //TODO Invalid input popup.
        }

    }
    @FXML
    public void register(MouseEvent event) throws java.io.IOException
    {
        App.loadScreen("end_user_register","Register");
    }
}
