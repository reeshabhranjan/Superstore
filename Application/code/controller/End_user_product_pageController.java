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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author reesh
 */
public class End_user_product_pageController implements Initializable {

    @FXML
    private Label productPageLabel;
    @FXML
    private Label productNameLabel;
    @FXML
    private Label productIdLabel;
    @FXML
    private Label productCategoryPathLabel;
    @FXML
    private Label productPriceLabel;
    @FXML
    private Label productStockCountLabel;
    @FXML
    private Button addToCartButton;
    @FXML
    private TextField quantityTextField;
    @FXML
    private Button addToWishlistButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> productDetails= (ArrayList<String>) App.getObjects().get(0);
        productNameLabel.setText(productDetails.get(0));
        productIdLabel.setText(productDetails.get(1));
        productPriceLabel.setText(productDetails.get(2));
        productStockCountLabel.setText(productDetails.get(3));
    }

    @FXML
    public void addToCart()
    {
        String quantityString = quantityTextField.getText();
        if(!quantityString.equals(""))
        {
            int quantity = Integer.parseInt(quantityString);
            ArrayList objects = new ArrayList();
            objects.add(quantity);
            App.sendMessage(new Message("enduser_add_to_cart",objects));
        }


        //ToDO pop up for added to product confirmation
    }
    @FXML
    public void showBrowseStorePanel()
    {
        ArrayList objects = new ArrayList();
        Message ServerResponse = App.sendMessage(new Message("end_user_recent_store_id",objects));
        App.getObjects().add(ServerResponse.getObjects().get(0));
        App.loadScreen("end_user_browse_store","Browse Store");
    }
}
