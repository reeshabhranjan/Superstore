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

/**
 * FXML Controller class
 *
 * @author reesh
 */
public class End_user_walletController implements Initializable {

    @FXML
    private Button addToWalletButton;
    @FXML
    private TextField quantityTextField;
    @FXML
    private Label fundsLabel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Message serverResponse = App.sendMessage(new Message("enduser_get_funds",null));
        Double funds = (Double) serverResponse.getObjects().get(0);
        fundsLabel.setText(String.valueOf(funds));
        App.resetObjects();
    }

    @FXML
    public void addToWallet()
    {
        int fundsToAdd = Integer.parseInt(quantityTextField.getText());
        ArrayList objects = new ArrayList();
        objects.add(fundsToAdd);
        App.sendMessage(new Message("enduser_add_funds",objects));
    }

    @FXML
    public void showDashboard()
    {
        App.loadScreen("end_user_dashboard","Dashboard");
    }
    
}
