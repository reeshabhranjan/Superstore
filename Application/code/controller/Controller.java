package controller;

import client.App;
import javafx.fxml.Initializable;

abstract class Controller implements Initializable {
    App app;

    public void setApp(App app) {
        this.app = app;
    }
}
