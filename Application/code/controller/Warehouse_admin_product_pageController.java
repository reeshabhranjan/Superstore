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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author reesh
 */
public class Warehouse_admin_product_pageController implements Initializable {

    @FXML
    private Label titleField;
    @FXML
    private Label nameField;
    @FXML
    private Label idField;
    @FXML
    private Label categoryPathField;
    @FXML
    private Label priceField;
    @FXML
    private Label stockCountField;
    @FXML
    private Label dField;
    @FXML
    private Label hField;
    @FXML
    private Label kField;
    @FXML
    private Button submitButton;
    @FXML
    private TextField dTextField;
    @FXML
    private TextField productNameTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextField hTextField;
    @FXML
    private TextField kTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void hello(MouseEvent event) {
    }
    
}
