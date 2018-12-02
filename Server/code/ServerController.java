import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;


public class ServerController implements Initializable {
    @FXML
    private Label ipAddressLabel;

    @FXML
    private Label portLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ipAddressLabel.setText("IP Address: "+Server.getIpAddress());
        portLabel.setText("Port: "+Server.getPort());
    }
}
