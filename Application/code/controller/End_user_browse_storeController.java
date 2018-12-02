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
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import jdk.nashorn.api.tree.Tree;

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
    private TreeView<String> categoryTreeView;
    @FXML
    private ListView<String> productsListView;
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
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(App.getObjects().get(0));
        Message categoryTreeViewServerResponse = App.sendMessage(new Message("enduser_get_treeview",objects));
        TreeView<String>categorytreeview = (TreeView) categoryTreeViewServerResponse.getObjects().get(0);
        categoryTreeView.setRoot(categorytreeview.getRoot());
//        Message productlistServerResponse = App.sendMessage(new Message("enduser_get_product_list",objects));
//
////            ArrayList<String> productsDetails = (ArrayList<String>) productlistServerResponse.getObjects().get(0);
//
////            addProductsToListView(productsDetails);
//        App.loadScreen("end_user_browse_store","Browse Store");
        App.resetObjects();
    }

    @FXML
    private void showWallePanel(MouseEvent event) {
    }

    @FXML
    private void showDashboardPanel(MouseEvent event) {
    }

    @FXML
    private void showProductListView(MouseEvent event) {
        TreeItem<String> selectedCategory = (TreeItem<String>) categoryTreeView.getSelectionModel().getSelectedItem();

        if(selectedCategory!=null)
        {
            String selectedCategoryPath = generateCategoryPath(selectedCategory);
            ArrayList objects = new ArrayList();
            objects.add(selectedCategoryPath);
                Message productListServerResponse = App.sendMessage(new Message("enduser_get_product_list",objects));
//            Message productListServerResponse = App.sendMessage(new Message("debugging",objects));
            ArrayList<String> productDetailsList = (ArrayList<String>) productListServerResponse.getObjects().get(0);
//            selectedProductDetails = productDetailsList; //only name and id
            try {
                addToListView(productDetailsList,productsListView);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void showProductPage()
    {
        String selectedProductDetails = (String) productsListView.getSelectionModel().getSelectedItem();
        ArrayList<String> productDetails = App.decode(selectedProductDetails," \\| ");
        if(selectedProductDetails!=null)
        {
            String selectedProductName = productDetails.get(0);
            ArrayList objects = new ArrayList();
            objects.add(selectedProductName);
                Message productDetailsServerResponse = App.sendMessage(new Message("enduser_get_product",objects));
//            Message productDetailsServerResponse = App.sendMessage(new Message("debugging",objects));
            objects = productDetailsServerResponse.getObjects();
            boolean productExists = (boolean) objects.get(0); //for the case when the product is removed from database

            if(productExists)
            {
                String productDetailsString = (String) objects.get(1);
                ArrayList<String> productDetailsList = (ArrayList<String>) App.decode(productDetailsString," \\| ");
                App.getObjects().add(productDetailsList);
                App.loadScreen("end_user_product_page",productDetailsList.get(0));
            }
            else
            {
                //Todo popup to say product is unavailable
            }
        }

    }
    public void addToListView(ArrayList<String> productsDetails, ListView listView) throws java.io.IOException
    {
        ObservableList observableList = listView.getItems();
        for (String item : productsDetails) {
            observableList.add(item);
        }
    }

    public String generateCategoryPath(TreeItem<String> item)
    {
        String path="";
        while(item!=null)
        {
            path=(">"+item.getValue())+path;
            item=item.getParent();
        }
        path = path.substring(1);
        return path;
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
