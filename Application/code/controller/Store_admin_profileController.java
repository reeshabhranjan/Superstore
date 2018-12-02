/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import classes.Credential;
import classes.StoreAdmin;
import classes.Message;
import classes.StoreAdmin;
import client.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Store_admin_profileController implements Initializable {
    private StoreAdmin storeAdmin;
    /**
     * Initializes the controller class.
     */
    @FXML
    private static Label nameLabel;
    @FXML
    private static Label phoneLabel;
    @FXML
    private static Label addressLabel;
    @FXML
    private static Label usernameLabel;
    @FXML
    private static TextField nameTextField;
    @FXML
    private static TextField phoneTextField;
    @FXML
    private static TextField addressTextField;
    @FXML
    private static TextField usernameTextField;
    @FXML
    private static TextField currentPasswordTextField;
    @FXML
    private static TextField newPasswordTextField;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Message ServerResponse = App.sendMessage(new Message("store_admin_profile",null));
        storeAdmin= (StoreAdmin) ServerResponse.getObjects().get(0);
        nameLabel.setText(storeAdmin.getName());
        phoneLabel.setText(storeAdmin.getPhoneNumber());
        addressLabel.setText(storeAdmin.getAddress());
        usernameLabel.setText(storeAdmin.getUsername());
    }

    @FXML
    public void update()
    {
        String name = nameTextField.getText();
        String phoneNumber = phoneTextField.getText();
        String address = addressTextField.getText();
        String username = usernameTextField.getText();
        String currPassword = currentPasswordTextField.getText();
        String newPassword = newPasswordTextField.getText();

        storeAdmin.update(name,phoneNumber,address,username,currPassword,newPassword);
        ArrayList objects = new ArrayList();
        objects.add(storeAdmin);
        objects.add(new Credential(username,currPassword));
        App.sendMessage(new Message("updateProfile",objects));
        App.loadScreen("store_admin_profile","Profile");
    }

    @FXML
    public void showDashboard()
    {
        App.loadScreen("store_admin_dashboard","Dashboard");
    }
    
}
