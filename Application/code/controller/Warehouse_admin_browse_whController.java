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
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author reesh
 */
public class Warehouse_admin_browse_whController implements Initializable {

    @FXML
    private Pane logoutButton;
    @FXML
    private ListView<?> warehouseList;
    @FXML
    private Pane myDatabasePane;
    @FXML
    private Pane browseWarehousePane;
    @FXML
    private Pane handleOrdersPane;
    @FXML
    private Pane outOfStockPane;
    @FXML
    private Pane statsPane;
    @FXML
    private Pane myProfilePane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
