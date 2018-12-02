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
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author reesh
 */
public class Warehouse_admin_handle_ordersController implements Initializable {

    @FXML
    private Pane logoutPane;
    @FXML
    private Button handleButton;
    @FXML
    private ComboBox<?> warehouseCombobox;
    @FXML
    private Button passButton;
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
