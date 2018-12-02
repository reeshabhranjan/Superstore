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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author reesh
 */
public class End_user_profileController implements Initializable {

    @FXML
    private Pane logOutPane;
    @FXML
    private Pane walletPane;
    @FXML
    private Label profileLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField currentPasswordTextField;
    @FXML
    private PasswordField newPasswordTextField;
    @FXML
    private Button submitButton;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private Label nameLabel;
    @FXML
    private Label phoneNumberLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label fundsLabel;
    @FXML
    private Label purchasesLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private Pane browseStorePane;
    @FXML
    private Pane orderHistoryPane;
    @FXML
    private Pane wishlistPane;
    @FXML
    private Pane cartPane;
    @FXML
    private Pane profilePane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
