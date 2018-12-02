package controller;

import client.App;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class GuestUserController extends Controller{

    @FXML
    public void showSearchStorePanel(MouseEvent event) throws java.io.IOException
    {
        App.loadScreen("guest_user_search_store","Dashboard", "guest_user");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
