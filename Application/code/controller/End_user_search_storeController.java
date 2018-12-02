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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author reesh
 */
public class End_user_search_storeController implements Initializable {

    @FXML
    private Label logOutPane;
    @FXML
    private Pane walletPane;
    @FXML
    private Pane backPane;
    @FXML
    private ListView<?> storeListView;
    @FXML
    private Label selectStoreLabel;
    @FXML
    private Pane browseStorePane;
    @FXML
    private Pane orderHistoryPane;
    @FXML
    private Pane wishlistPane;
    @FXML
    private Pane cartPane;
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
    private void showBrowseStorePanel(MouseEvent event) {
    }
    
}
