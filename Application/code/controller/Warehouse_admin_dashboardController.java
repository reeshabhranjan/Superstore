/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

//import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

import client.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author reesh
 */
public class Warehouse_admin_dashboardController implements Initializable {

    @FXML
    private Pane myDatabasePane;
    @FXML
    private Pane browseWarehousePane;
    @FXML
    private Pane profilePane;
    @FXML
    private Pane logoutButton;
    @FXML
    private Label welcomeLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void openDatabase(){
        App.loadScreen("warehouse_admin_database","Database");
    }

    @FXML
    public void browseWarehouse(){

    }

    @FXML
    public void myProfile(){

    }
    
}
