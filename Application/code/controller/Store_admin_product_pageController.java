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
import database.Product;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author reesh
 */
public class Store_admin_product_pageController implements Initializable {

    @FXML
    private Label titleField;
    @FXML
    private Label nameField;
    @FXML
    private Label idField;
    @FXML
    private Label categoryPathField;
    @FXML
    private Label priceField;
    @FXML
    private Label stockCountField;
    @FXML
    private Label dField;
    @FXML
    private Label hField;
    @FXML
    private Label kField;
    @FXML
    private Pane backPane;
    @FXML
    private TextField dTextField;
    @FXML
    private TextField productNameTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextField hTextField;
    @FXML
    private TextField kTextField;

    private Product product;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        product = (Product) App.getObjects().get(0);
        nameField.setText(product.getName());
        idField.setText(String.valueOf(product.getId()));
        priceField.setText(String.valueOf(product.getPrice()));
        stockCountField.setText(String.valueOf(product.getStockCount()));
        dField.setText(String.valueOf(product.getD()));
        hField.setText(String.valueOf(product.getH()));
        kField.setText(String.valueOf(product.getK()));
    }

    @FXML
    public void updateInfo()
    {
        ArrayList objects = new ArrayList();
        product.setName(productNameTextField.getText());
        product.setPrice(Double.parseDouble(priceTextField.getText()));
        product.setK(Double.parseDouble(kTextField.getText()));
        product.setD(Double.parseDouble(dTextField.getText()));
        product.setH(Double.parseDouble(hTextField.getText()));
        objects.add(product);
        App.sendMessage(new Message("store_admin_update_product",objects));
    }

    @FXML
    public void back()
    {
        App.loadScreen("store_admin_dashboard","Dashboard");
    }
    
}
