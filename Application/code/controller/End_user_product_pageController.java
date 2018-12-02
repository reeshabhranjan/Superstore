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
        // TODO
    }    
    
}
