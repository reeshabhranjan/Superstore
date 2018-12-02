/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
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
    private void login(MouseEvent event) {
    }

    @FXML
    private void showRegisterPanel(MouseEvent event) {
    }

    @FXML
    private void showGuestUserDashboardPanel(MouseEvent event) {
    }
    
}
