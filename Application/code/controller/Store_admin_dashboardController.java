/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
public class Store_admin_dashboardController implements Initializable {

    @FXML
    private Pane logoutPane;
    @FXML
    private Label welcomeLabel;
    @FXML
    private Pane databasePane;
    @FXML
    private Pane profilePane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    public void showDatabasePanel()
    {
        App.loadScreen("store_admin_database","Database");
    }

    @FXML
    public void showOrderPanel()
    {
        App.loadScreen("store_admin_order","Order");
    }

    @FXML
    public void showOrderHistoryPanel()
    {
        App.loadScreen("store_admin_order_history","Order History");
    }

    @FXML
    public void showProfilePanel()
    {
        App.loadScreen("store_admin_profile","Profile");
    }
    
}
