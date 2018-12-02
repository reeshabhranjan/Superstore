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
public class Superuser_dashboardController implements Initializable {

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
    private Label welcomeLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    public void showCreateWarehousePanel()
    {
        App.loadScreen("superuser_create_warehouse","Create Warehouse");
    }

    @FXML
    public void showCreateStorePanel()
    {
        App.loadScreen("superuser_create_store","Create Store");
    }

    @FXML
    public void showLinkWarehouseStorePanel()
    {
        App.loadScreen("superuser_link_wh_st","Link Warehouse-Store");
    }

    @FXML
    public void createAdmin()
    {
        App.loadScreen("superuser_create_admin","Create Admin");
    }

    @FXML
    public void browseWarehouse()
    {
        App.loadScreen("superuser_browse_warehouse","Browse Warehouse");
    }

    @FXML
    public void browseStore()
    {
        App.loadScreen("superuser_browse_store","Browse Store");
    }
    @FXML
    public void profile()
    {
        App.loadScreen("superuser_profile","Profile");
    }
    @FXML
    public void assignWarehouseAdmins()
    {
        App.loadScreen("superuser_assign_warehouse_admins","Assign Warehouse Admins");
    }

    @FXML
    public void assignStoreAdmins()
    {
        App.loadScreen("superuser_assign_store_admins","Assign Store Admins");
    }


}
