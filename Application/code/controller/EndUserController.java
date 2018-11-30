package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class EndUserController extends Controller{
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
        public void showDashboardPanel(MouseEvent event) throws java.io.IOException
        {
            app.loadScreen("end_user_dashboard","Dashboard");
        }
        @FXML
        public void showSelectStorePanel(MouseEvent event) throws java.io.IOException
        {
            app.loadScreen("end_user_search_store","Select Store");
        }
        @FXML
        public void showBrowseStorePanel(MouseEvent event) throws java.io.IOException
        {
            app.loadScreen("end_user_browse_store","Browse Store");
            //ToDO fill products details in tableview.
        }

        @FXML
        public void showOrderHistoryPanel(MouseEvent event) throws java.io.IOException
        {
            app.loadScreen("end_user_order_history","Order History");
        }
        @FXML
        public void showWishlistPanel(MouseEvent event) throws java.io.IOException
        {
            app.loadScreen("end_user_wishlist","Wish List");
        }
        @FXML
        public void showCartPanel(MouseEvent event) throws java.io.IOException
        {
            app.loadScreen("end_user_cart","Cart");
        }
        @FXML
        public void showProfilePanel(MouseEvent event) throws java.io.IOException
        {
            app.loadScreen("end_user_profile","Profile");
        }

        @FXML
        public void showWallePanel(MouseEvent event) throws java.io.IOException
        {
            app.loadScreen("end_user_wallet","Wallet");
        }

        @FXML
        public void logout()
        {
            app.logout();
        }
//    public void display(MouseEvent event){
//        label.setText("Browse store!");
//    }

}
