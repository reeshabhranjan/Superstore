/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import classes.EndUser;
import classes.Message;
import client.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author reesh
 */
public class End_user_profileController implements Initializable {

    @FXML
    private Pane logOutPane;
    @FXML
    private Pane walletPane;
    @FXML
    private Label profileLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField currentPasswordTextField;
    @FXML
    private PasswordField newPasswordTextField;
    @FXML
    private Button submitButton;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private Label nameLabel;
    @FXML
    private Label phoneNumberLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label fundsLabel;
    @FXML
    private Label purchasesLabel;
    @FXML
    private Label usernameLabel;
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

    private EndUser endUser;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Message ServerResponse = App.sendMessage(new Message("enduser_profile",null));
        endUser = (EndUser) ServerResponse.getObjects().get(0);
        nameLabel.setText(endUser.getName());
        phoneNumberLabel.setText(endUser.getPhoneNumber());
        addressLabel.setText(endUser.getAddress());
        fundsLabel.setText(String.valueOf(endUser.getFunds()));
        usernameLabel.setText(endUser.getUsername());
    }
    @FXML
    public void showCartPanel(MouseEvent event) throws java.io.IOException
    {
        Message serverResponse = App.sendMessage(new Message("enduser_get_cart",new ArrayList()));
        App.getObjects().add(serverResponse.getObjects().get(0));
        App.loadScreen("end_user_cart","Cart");
    }

    @FXML
    public void updateInformation()
    {
        String name = nameTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();
        String address = addressTextField.getText();
        String username = usernameTextField.getText();
        String currPassword = currentPasswordTextField.getText();
        String newPassword = newPasswordTextField.getText();

        endUser.update(name,phoneNumber,address,username,currPassword,newPassword);
        ArrayList objects = new ArrayList();
        objects.add(endUser);
        App.sendMessage(new Message("updateProfile",objects));
        App.loadScreen("end_user_profile","Profile");
    }
    
}
