package controller;

import classes.Message;
import client.App;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class EndUserController extends Controller implements Initializable {
        @FXML
        private TextField productNameTextField;

        @FXML
        private TextField priceTextField;

        @FXML
        private TextField fixedCostTextField;

        @FXML
        private TextField carryingCostTextField;

        @FXML
        private TextField demandedUnitsTextField;

        @FXML
        private ComboBox loginComboBox;

        @FXML
        private TableView databaseTable;

        @FXML
        private ListView<String> productsListView;

        @FXML
        private ListView<String> storeListView;

    @FXML
        public void showDashboardPanel(MouseEvent event) throws java.io.IOException
        {
            App.loadScreen("end_user_dashboard","Dashboard");
        }
        @FXML
        public void showSearchStorePanel(MouseEvent event) throws java.io.IOException
        {
            System.out.println(productsListView);
            App.loadScreen("end_user_search_store","Select Store");
            EndUserController endUserController = (EndUserController) App.getController();
            System.out.println(endUserController);
//            ListView<String> storelistview = endUserController.storeListView;
//            System.out.println(storelistview);
//            storelistview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
////            Message storelistServerResponse = App.sendMessage(new Message("enduser_get_store_list",new ArrayList()));
////            ArrayList<String> storeDetailsList = (ArrayList<String>) storelistServerResponse.getObjects().get(0);
//            ArrayList<String> storeDetailsList = new ArrayList<String>();
//            storeDetailsList.add("store0 | 0");
//            storeDetailsList.add("store1 | 1");
//            storeDetailsList.add("store2 | 2");
//            storeDetailsList.add("store3 | 3");
//            addToListView(storeDetailsList, storelistview);
        }

        public ArrayList<String> decodeString(String input, String separator)
        {
            return (ArrayList<String>) Arrays.asList(input.split(separator));
        }

//        @FXML
//        public void showBrowseStorePanel(MouseEvent event) throws java.io.IOException
//        {
//            //Todo get the store from storelistview
//            String selectedzStoreDetails = (String) storeListView.getSelectionModel().getSelectedItem();
//            String selectedStoreId = decodeString(selectedStoreDetails," | ").get(1);
//
//            System.out.println(selectedStoreId);
////            ArrayList objects = new ArrayList();
////            objects.add(Integer.parseInt(selectedStoreId));
////            Message productlistServerResponse = App.sendMessage(new Message("enduser_get_product_list",objects));
////            Message categoryTreeViewServerResponse = App.sendMessage(new Message("enduser_get_treeview",objects));
//////            ArrayList<String> productsDetails = (ArrayList<String>) productlistServerResponse.getObjects().get(0);
////            TreeView treeView = (TreeView) categoryTreeViewServerResponse.getObjects().get(0);
//////            addProductsToListView(productsDetails);
////            App.loadScreen("end_user_browse_store","Browse Store");
//            //ToDO fill products details in tableview.
//        }

        @FXML
        public void addToListView(ArrayList<String> productsDetails, ListView listView) throws java.io.IOException
        {
            ObservableList observableList = listView.getItems();
            for (String item : productsDetails) {
                observableList.add(item);
            }
        }

        @FXML
        public void showOrderHistoryPanel(MouseEvent event) throws java.io.IOException
        {
            App.loadScreen("end_user_order_history","Order History");
        }
        @FXML
        public void showWishlistPanel(MouseEvent event) throws java.io.IOException
        {
            App.loadScreen("end_user_wishlist","Wish List");
        }
        @FXML
        public void showCartPanel(MouseEvent event) throws java.io.IOException
        {
            App.loadScreen("end_user_cart","Cart");
        }
        @FXML
        public void showProfilePanel(MouseEvent event) throws java.io.IOException
        {
            App.loadScreen("end_user_profile","Profile");
        }

        @FXML
        public void showWallePanel(MouseEvent event) throws java.io.IOException
        {
            App.loadScreen("end_user_wallet","Wallet");
        }

        @FXML
        public void logout()
        {
            App.logout();
        }

        @Override
        public void initialize(URL location, ResourceBundle resources) {
            App.setController(this);
        }

//    public void display(MouseEvent event){
//        label.setText("Browse store!");
//    }

}
