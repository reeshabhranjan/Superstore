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
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author reesh
 */
public class End_user_browse_storeController implements Initializable {

    @FXML
    private Pane logOutPane;
    @FXML
    private Pane walletPane;
    @FXML
    private Pane backPane;
    @FXML
    private TreeView<?> categoryTreeView;
    @FXML
    private ListView<?> productsListView;
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
    private void showWallePanel(MouseEvent event) {
    }

    @FXML
    private void showDashboardPanel(MouseEvent event) {
    }

    @FXML
    private void showProductListView(MouseEvent event) {
    }

    @FXML
    private void showProductPage(MouseEvent event) {
    }

    @FXML
    private void showOrderHistoryPanel(MouseEvent event) {
    }

    @FXML
    private void showWishlistPanel(MouseEvent event) {
    }

    @FXML
    private void showCartPanel(MouseEvent event) {
    }

    @FXML
    private void showProfilePanel(MouseEvent event) {
    }
    
}
