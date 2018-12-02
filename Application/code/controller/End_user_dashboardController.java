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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author reesh
 */
public class End_user_dashboardController implements Initializable {

    @FXML
    private Pane logOutPane;
    @FXML
    private Pane walletPane;
    @FXML
    private Label welcomeLabel;
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
    private void logout(MouseEvent event) {
    }

    @FXML
    private void showWallePanel(MouseEvent event) {
    }

    @FXML
    public void showSearchStorePanel(MouseEvent event) throws java.io.IOException
    {
        Message storelistServerResponse = App.sendMessage(new Message("enduser_get_store_list",new ArrayList()));
        ArrayList<String> storeDetailsList = (ArrayList<String>) storelistServerResponse.getObjects().get(0);
        App.getObjects().add(storeDetailsList);
        App.loadScreen("end_user_search_store","Search Store");
//        EndUserController endUserController = (EndUserController) App.loadScreen("end_user_search_store","Select Store");
//        ListView<String> storelistview = endUserController.storeListView;
//        storelistview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//            Message storelistServerResponse = App.sendMessage(new Message("enduser_get_store_list",new ArrayList()));
//
//        ArrayList<String> storeDetailsList = new ArrayList<String>();
//        storeDetailsList.add("store0 | 0");
//        storeDetailsList.add("store1 | 1");
//        storeDetailsList.add("store2 | 2");
//        storeDetailsList.add("store3 | 3");
//        addToListView(storeDetailsList, storelistview);
    }

    @FXML
    private void showOrderHistoryPanel(MouseEvent event) {
    }

    @FXML
    private void showWishlistPanel(MouseEvent event) {
    }

    @FXML
    public void showCartPanel(MouseEvent event) throws java.io.IOException
    {
        Message serverResponse = App.sendMessage(new Message("enduser_get_cart",new ArrayList()));
        App.getObjects().add(serverResponse.getObjects().get(0));
        App.loadScreen("end_user_cart","Cart");
    }

    @FXML
    private void showProfilePanel(MouseEvent event) {
        Message serverResponse = App.sendMessage(new Message("enduser_profiel",new ArrayList()));
        App.getObjects().add(serverResponse.getObjects().get(0));
        App.loadScreen("end_user_profile","Profile");
    }
    
}
