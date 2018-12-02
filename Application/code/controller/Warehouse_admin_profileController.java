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
public class Warehouse_admin_profileController implements Initializable {

    @FXML
    private Pane logoutPane;
    @FXML
    private TextField newUsernameTextField;
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
    private TextField phoneTextField;
    @FXML
    private Label nameField;
    @FXML
    private Label phoneField;
    @FXML
    private Label addressField;
    @FXML
    private Label usernameField;
    @FXML
    private Pane databasePane;
    @FXML
    private Pane browseWarehousePane;
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
