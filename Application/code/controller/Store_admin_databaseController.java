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
import database.Product;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author reesh
 */
public class Store_admin_databaseController implements Initializable {

    @FXML
    private Pane logoutPane;
    @FXML
    private TreeView<String> categoryTreeView;

    @FXML
    private ListView<String> productListView;
    @FXML
    private Button submitButton;
    @FXML
    private Pane databasePane;
    @FXML
    private Pane profilePane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Message categoryTreeViewServerResponse = App.sendMessage(new Message("store_admin_get_treeview",null));
        TreeView<String> categorytreeview = (TreeView) categoryTreeViewServerResponse.getObjects().get(0);
        categoryTreeView.setRoot(categorytreeview.getRoot());
        categorytreeview.setShowRoot(false);
//        Message productlistServerResponse = App.sendMessage(new Message("enduser_get_product_list",objects));
//
////            ArrayList<String> productsDetails = (ArrayList<String>) productlistServerResponse.getObjects().get(0);
//
////            addProductsToListView(productsDetails);
//        App.loadScreen("end_user_browse_store","Browse Store");
        App.resetObjects();
    }

    @FXML
    private void showProductListView() {
        TreeItem<String> selectedCategory = (TreeItem<String>) categoryTreeView.getSelectionModel().getSelectedItem();

        if(selectedCategory!=null)
        {
            String selectedCategoryPath = generateCategoryPath(selectedCategory);
            ArrayList objects = new ArrayList();
            objects.add(selectedCategoryPath);
            Message productListServerResponse = App.sendMessage(new Message("store_admin_get_product_list",objects));
//            Message productListServerResponse = App.sendMessage(new Message("debugging",objects));
            ArrayList<String> productDetailsList = (ArrayList<String>) productListServerResponse.getObjects().get(0);
//            selectedProductDetails = productDetailsList; //only name and id
            try {
                addToListView(productDetailsList,productListView);
            } catch (IOException e) {
                e.printStackTrace();
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
    public void showProductPage()
    {
        String selectedProductDetails = (String) productListView.getSelectionModel().getSelectedItem();
        ArrayList<String> productDetails = App.decode(selectedProductDetails," \\| ");
        if(selectedProductDetails!=null)
        {
            String selectedProductName = productDetails.get(0);
            ArrayList objects = new ArrayList();
            objects.add(selectedProductName);
            Message productDetailsServerResponse = App.sendMessage(new Message("store_admin_get_product",objects));
//            Message productDetailsServerResponse = App.sendMessage(new Message("debugging",objects));
            objects = productDetailsServerResponse.getObjects();
            boolean productExists = (boolean) objects.get(0); //for the case when the product is removed from database

            if(productExists)
            {
                Product product = (Product) objects.get(1);
                App.getObjects().add(product);
                App.loadScreen("store_admin_product_page",product.getName());
            }
            else
            {
                //Todo popup to say product is unavailable
            }
        }

    }
}
