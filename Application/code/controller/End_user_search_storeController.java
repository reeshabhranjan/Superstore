/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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
        try {
            addToListView((ArrayList<String>) App.getObjects().get(0),storeListView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addToListView(ArrayList<String> productsDetails, ListView listView) throws java.io.IOException
    {
        ObservableList observableList = listView.getItems();
        for (String item : productsDetails) {
            observableList.add(item);
        }
    }

    @FXML
    public void showBrowseStorePanel() {
        String selectedStoreDetails = (String) storeListView.getSelectionModel().getSelectedItem();
        String selectedStoreId = decodeString(selectedStoreDetails," | ").get(1);
        App.getObjects().add(selectedStoreId);
        App.loadScreen("end_user_browse_store","Browse Store");
//            ArrayList objects = new ArrayList();
//            objects.add(Integer.parseInt(selectedStoreId));
//            Message productlistServerResponse = App.sendMessage(new Message("enduser_get_product_list",objects));
//            Message categoryTreeViewServerResponse = App.sendMessage(new Message("enduser_get_treeview",objects));
////            ArrayList<String> productsDetails = (ArrayList<String>) productlistServerResponse.getObjects().get(0);
//            TreeView treeView = (TreeView) categoryTreeViewServerResponse.getObjects().get(0);
////            addProductsToListView(productsDetails);
//            App.loadScreen("end_user_browse_store","Browse Store");
        //ToDO fill products details in tableview.
    }
    public ArrayList<String> decodeString(String input, String separator)
    {
        return (ArrayList<String>) Arrays.asList(input.split(separator));
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
