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
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author reesh
 */
public class Superuser_create_adminController implements Initializable {

    @FXML
    private Pane createWarehouseLabel;
    @FXML
    private Pane createStoreLabel;
    @FXML
    private Pane linkWarehouseStoreLabel;
    @FXML
    private Pane createAdministratorsLabel;
    @FXML
    private Pane browseWarehouseLabel;
    @FXML
    private Pane browseStoreLabel;
    @FXML
    private Pane myProfileLabel;
    @FXML
    private Pane logoutPane;
    @FXML
    private Button submitButton;
    @FXML
    private TextField usernameTextField;
    @FXML
    private ComboBox<?> adminTypeComboBox;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private ComboBox<?> entityComboBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
