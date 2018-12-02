/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import classes.Message;
import client.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author reesh
 */
public class Superuser_create_storeController implements Initializable {

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
    private TextField storeNameTextField;
    @FXML
    private TextField storeIdTextField;
    @FXML
    private Button submitButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void createStore()
    {
        String storeName = storeNameTextField.getText();
        ArrayList objects = new ArrayList();
        objects.add(storeName);
        Message serverResponse = App.sendMessage(new Message("create_store",objects));
        storeIdTextField.setText((String) serverResponse.getObjects().get(0));
    }
    
}
