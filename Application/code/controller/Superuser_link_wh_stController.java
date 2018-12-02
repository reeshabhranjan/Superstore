/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import classes.Message;
import client.App;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author reesh
 */
public class Superuser_link_wh_stController implements Initializable {

    @FXML
    private Pane createWarehouseLabel;
    @FXML
    private Pane createStoreLabel;
    @FXML
    private Pane linkWarehouseStoreLabel;
    @FXML
    private Pane createAdministratorsLabel;
    @FXML
    private Pane browseWarehouseLabel;
    @FXML
    private Pane browseStoreLabel;
    @FXML
    private Pane myProfileLabel;
    @FXML
    private Pane logoutPane;
    @FXML
    private Button submitButton;
    @FXML
    private ListView<String> storeListView;
    @FXML
    private ListView<String> warehouseListView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Message serverResponse = App.sendMessage(new Message("su_get_warehouse_list",null));
        ArrayList<String> warehouseList = (ArrayList<String>) serverResponse.getObjects().get(0);

        serverResponse = App.sendMessage(new Message("su_get_store_list",null));
        ArrayList<String> storeList = (ArrayList<String>) serverResponse.getObjects().get(0);

        try {
            addToListView(warehouseList,warehouseListView);
            addToListView(storeList,storeListView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addToListView(ArrayList<String> productsDetails, ListView listView) throws java.io.IOException
    {
        ObservableList observableList = listView.getItems();
        for (String item : productsDetails) {
            observableList.add(item);
        }
    }

    public void linkWarehouseStore()
    {
        //todo check for nothing selected
        String selectedWarehouse = warehouseListView.getSelectionModel().getSelectedItem();
        int warehouseID = Integer.parseInt(decodeString(selectedWarehouse," \\| ").get(1));

        String selectedStore = storeListView.getSelectionModel().getSelectedItem();
        int storeID = Integer.parseInt(decodeString(selectedStore," \\| ").get(1));

        App.sendMessage(new Message("link_warehouse_store",null));

    }

    public List<String> decodeString(String input, String separator)
    {
        List<String> list = (List<String>) Arrays.asList(input.split(separator));
//            System.out.println(list.get(0)+" "+list.get(1));
        return list;
    }
}
