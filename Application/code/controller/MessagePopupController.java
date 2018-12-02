package controller;

import client.MessagePopup;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MessagePopupController implements Initializable {

    @FXML
    Button okButton;

    @FXML
    Label messageLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        messageLabel.setText(MessagePopup.getMessage());
    }

    @FXML
    public void closePopup(MouseEvent mouseEvent){
        MessagePopup.closeStage();
    }
}
