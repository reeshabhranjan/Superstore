package controller;

import classes.Message;
import client.App;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.lang.reflect.Array;
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
        private TreeView<String> categoryTreeView;

        @FXML
        private Label productNameLabel;
        @FXML
        private Label productIdLabel;
        @FXML
        private Label productCategoryPathLabel;
        @FXML
        private Label productPriceLabel;
        @FXML
        private Label productStockCountLabel;

        private String selectedCategoryPath;

        private ArrayList<String> selectedProductDetails;

        @FXML
        public void showDashboardPanel(MouseEvent event) throws java.io.IOException
        {
            App.loadScreen("end_user_dashboard","Dashboard", "end_user");
        }
        @FXML
        public void showSearchStorePanel(MouseEvent event) throws java.io.IOException
        {
            App.loadScreen("end_user_search_store","Select Store", "end_user");
            EndUserController endUserController = (EndUserController) App.getController();
            System.out.println(endUserController);
            ListView<String> storelistview = endUserController.storeListView;
//            System.out.println(storelistview);
            storelistview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//            Message storelistServerResponse = App.sendMessage(new Message("debugging",new ArrayList()));
//            ArrayList<String> storeDetailsList = (ArrayList<String>) storelistServerResponse.getObjects().get(0);
            ArrayList<String> storeDetailsList = new ArrayList<String>();
            storeDetailsList.add("store0"+" | "+"0");
//            storeDetailsList.add("store1 : 1");
//            storeDetailsList.add("store2 : 2");
//            storeDetailsList.add("store3 : 3");
            addToListView(storeDetailsList, storelistview);
        }

        public List<String> decodeString(String input, String separator)
        {
            List<String> list = (List<String>) Arrays.asList(input.split(separator));
//            System.out.println(list.get(0)+" "+list.get(1));
            return list;
        }

        @FXML
        public void showBrowseStorePanel(MouseEvent event) throws java.io.IOException
        {
            //Todo get the store from storelistview
            EndUserController controller = (EndUserController) App.getController();
            String selectedStoreDetails = controller.storeListView.getSelectionModel().getSelectedItem();
            String selectedStoreId = decodeString(selectedStoreDetails," \\| ").get(1);
            App.loadScreen("end_user_browse_store","Browse Store","end_user");
            controller = (EndUserController) App.getController();
//            System.out.println(selectedStoreId);
            ArrayList objects = new ArrayList();
            objects.add(Integer.parseInt(selectedStoreId));
//            Message categoryTreeViewServerResponse = App.sendMessage(new Message("enduser_get_treeview",objects));
//            TreeView<String> treeView = (TreeView<String>) categoryTreeViewServerResponse.getObjects().get(0);

            controller.categoryTreeView.setRoot(generateSampleTreeView());
            controller.categoryTreeView.setShowRoot(false);

            //ToDo fill products details in tableview.
        }

        public TreeItem<String> generateSampleTreeView()
        {
            TreeItem<String> root = new TreeItem<String>();
            root.setExpanded(true);

            TreeItem<String> electronics = new TreeItem<String>("electronics");
            electronics.setExpanded(true);
            TreeItem<String> phone = new TreeItem<String>("phone");
            phone.setExpanded(true);
            TreeItem<String> pixel = new TreeItem<String>("pixel");
            pixel.setExpanded(true);
            phone.getChildren().add(pixel);
            electronics.getChildren().add(phone);
            root.getChildren().add(electronics);
            return root;
        }
        @FXML
        public void showProductListView()
        {
            EndUserController controller = (EndUserController) App.getController();
            TreeItem<String> selectedCategory = controller.categoryTreeView.getSelectionModel().getSelectedItem();
            if(selectedCategory!=null)
            {
                selectedCategoryPath = generateCategoryPath(selectedCategory);
                ArrayList objects = new ArrayList();
                objects.add(selectedCategoryPath);
                Message productListServerResponse = App.sendMessage(new Message("enduser_get_product_list",objects));
                ArrayList<String> productDetailsList = (ArrayList<String>) productListServerResponse.getObjects().get(0);
                selectedProductDetails = productDetailsList; //only name and id
                try {
                    addToListView(productDetailsList,controller.productsListView);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//
        }
        @FXML
        public void showProductPage()
        {
            if(selectedProductDetails!=null)
            {
                String selectedProductName = selectedProductDetails.get(0);
                ArrayList objects = new ArrayList();
                objects.add(selectedProductName);
                Message productDetailsServerResponse = App.sendMessage(new Message("enduser_get_product",objects));
                objects = productDetailsServerResponse.getObjects();
                boolean productExists = (boolean) objects.get(0); //for the case when the product is removed from database

                if(productExists)
                {
                    String productDetailsString = (String) objects.get(1);
                    ArrayList<String> productDetailsList = (ArrayList<String>) decodeString(productDetailsString," \\| ");
                    selectedProductDetails = productDetailsList; //all the product details
                    App.loadScreen("end_user_product_page",productDetailsList.get(0),"end_user");
                    initializeProductPage();
                }
                else
                {
                    //Todo popup to say product is unavailable
                }
            }

        }

        @FXML
        public void initializeProductPage()
        {
            EndUserController controller = (EndUserController) App.getController();
            controller.productNameLabel.setText("Name: "+selectedProductDetails.get(0));
            controller.productIdLabel.setText("Id: "+selectedProductDetails.get(1));
            controller.productCategoryPathLabel.setText("Category: "+selectedProductDetails.get(2));
            controller.productPriceLabel.setText("Category: "+selectedProductDetails.get(3));
            controller.productStockCountLabel.setText("Category: "+selectedProductDetails.get(4));
        }

        @FXML
        public void addToCart()
        {
            ArrayList objects = new ArrayList();

            //ToDO pop up for added to product confirmation
        }

        @FXML
        public void showPopup(String filename, String title)
        {

        }

        public String generateCategoryPath(TreeItem<String> item)
        {
            String path="";
            while(item!=null)
            {
                path=(">"+item.getValue())+path;
                item=item.getParent();
            }
            path = path.substring(1);
            return path;
        }

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
            App.loadScreen("end_user_order_history","Order History", "end_user");
        }
        @FXML
        public void showWishlistPanel(MouseEvent event) throws java.io.IOException
        {
            App.loadScreen("end_user_wishlist","Wish List", "end_user");
        }
        @FXML
        public void showCartPanel(MouseEvent event) throws java.io.IOException
        {
            App.loadScreen("end_user_cart","Cart", "end_user");
        }
        @FXML
        public void showProfilePanel(MouseEvent event) throws java.io.IOException
        {
            App.loadScreen("end_user_profile","Profile", "end_user");
        }

        @FXML
        public void showWallePanel(MouseEvent event) throws java.io.IOException
        {
            App.loadScreen("end_user_wallet","Wallet", "end_user");
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
