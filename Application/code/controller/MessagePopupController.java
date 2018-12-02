package controller;

import client.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MessagePopupController extends Controller implements Initializable{

    @FXML
    Button okButton;

    @FXML
    public Label messageLabel;

    private String message;

    public MessagePopupController() {

    }
//    public MessagePopupController(String message) {
//        this.message=message;
//    }

    public void setMessage(String message) {
        messageLabel.setText(message);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        messageLabel.setText(message);
    }

//    public void setMessageLabel(String messageLabel) {
//        this.messageLabel.setText(messageLabel);
//    }

    @FXML
    public void closePopup(MouseEvent mouseEvent){
        App.getPopupStage().close();
    }
}
