/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import classes.Message;
import client.App;
import javafx.collections.ObservableList;
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
public class End_user_cartController implements Initializable {

    @FXML
    private Pane logOutPane;
    @FXML
    private Pane walletPane;
    @FXML
    private Pane checkOutPane;
    @FXML
    private ListView<?> productListView;
    @FXML
    private Label cartLabel;
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
        Message serverResponse = App.sendMessage(new Message("enduser_get_cart",null));
        ArrayList<String> items = (ArrayList<String>) serverResponse.getObjects().get(0);
        try {
            addToListView(items,productListView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    

    @FXML
    private void showItem(MouseEvent event) {
    }

    @FXML
    private void showSelectStorePanel(MouseEvent event) {
    }

    public void addToListView(ArrayList<String> productsDetails, ListView listView) throws java.io.IOException
    {
        ObservableList observableList = listView.getItems();
        for (String item : productsDetails) {
            observableList.add(item);
        }
    }

    @FXML
    public void checkOut()
    {
        App.sendMessage(new Message("enduser_checkout",null));
        //ToDo popup that items checkedout.
    }

    @FXML
    public void showWalletPanel()
    {
        App.loadScreen("end_user_wallet","Wallet");
    }
    
}
