/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;

import classes.Credential;
import client.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author reesh
 */
public class End_user_registerController implements Initializable {

    @FXML
    private TextField nameTextField;
    @FXML
    private Button registerButton;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private TextField usernameTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    private boolean textFieldValidator(){
        boolean cond1 = usernameTextField.getText().length()>0;
        boolean cond2 = passwordTextField.getText().length()>0;
        return  cond1&&cond2;
    }
    @FXML
    public void register(MouseEvent event) throws java.io.IOException
    {
        Credential credential;
        String name = nameTextField.getText();
        if(textFieldValidator() && !name.equals("")) {
            credential=new Credential(usernameTextField.getText(),passwordTextField.getText());
//            System.out.println(app);
            App.register(credential,name);
        }
        else
        {
            //TODO Invalid input popup.
        }
    }
    
}
