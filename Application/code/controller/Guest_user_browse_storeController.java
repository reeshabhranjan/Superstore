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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author reesh
 */
public class Guest_user_browse_storeController implements Initializable {

    @FXML
    private TableView<?> databaseTable;
    @FXML
    private TableColumn<?, ?> productNameColumn;
    @FXML
    private TableColumn<?, ?> categoryNameColumn;
    @FXML
    private TableColumn<?, ?> productPriceColumn;
    @FXML
    private Pane browseStore;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void showBrowserStorePanel(MouseEvent event) {
    }
    
}
