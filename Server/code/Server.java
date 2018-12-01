import classes.*;
import database.Cart;
import database.Category;
import database.Product;
import exceptions.*;
import javafx.scene.control.TreeView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server{

    //TODO decide where the opcodes will be accepted and where the appropriate methods be called.

    private static volatile Superstore superstore; //TODO should there be a 'volatile' here?
    private volatile int sessionCount;

    public static void main(String[] args) throws IOException {

        //TODO deserialize the Superstore here.

        superstore=Superstore.getInstance();
        ServerSocket serverSocket=new ServerSocket(1400);
        ExecutorService executorService= Executors.newFixedThreadPool(4);

        while (true){

            Socket client=serverSocket.accept();
            Runnable session=new Session(client,superstore);
            executorService.execute(session);
        }
    }

    private static class Session implements Runnable {

        private ObjectOutputStream outputStream;
        private ObjectInputStream inputStream;
        private volatile Superstore superstore;
        private boolean registeredSession;
        private boolean endSession;
        private Socket client;
        private RegisteredUser registeredUser,registeredUser1;
        private Credential credential;
        private String string,string1;
        private Store store;
        private Warehouse warehouse;
        private int integer;
        private int integer1;
        private Product product;
        private Product product1;
        private ArrayList<String> stringArrayList,stringArrayList1;
        private TreeView<String> treeView;
        private Category category;
        private Cart cart;

        public void resetSession(){

            registeredSession=false;
            registeredUser=null;
            credential=null;
            string=null;
            store=null;
        }

        public Session(Socket client, Superstore superstore) {

            this.outputStream=null;
            this.inputStream=null;

            try{
                this.outputStream=new ObjectOutputStream(client.getOutputStream());
                this.inputStream=new ObjectInputStream(client.getInputStream());
                System.out.println("Connected!");
//                throw new IOException();
            }

            catch (IOException ioe) {
                System.err.println("Cannot find client's data-streams");

                try{
                    this.outputStream.close();
                    this.inputStream.close();
                }

                catch (IOException ioe2){
                    System.err.println("Cannot close client's data-streams");
                }
            }

            finally {

//                try{
//                    this.outputStream.close();
//                    this.inputStream.close();
//                }
//
//                catch (IOException ioe){
//                    System.err.println("Cannot close client's data-streams");
//                }

            }

            this.superstore=superstore;
            this.registeredSession=false;
            this.endSession=false;
            this.client=client;
            this.registeredUser=null;
        }

        @Override
        public void run() {

//            System.out.println("waiting for request");

            while(!endSession){

                Message message=null;
                Message response=new Message();

                try {
//                    if(inputStream.available()==0){
////                        System.out.println("waiting for request");
//                        continue;
//                    }
//
//                    else{
//                        message=(Message)inputStream.readObject();
//                    }

                    message=(Message)inputStream.readObject();

                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Error while reading input stream.");
                }

                switch (message.getOpcode()){

                    // Generic methods

                    case "register_end_user":
                        credential=(Credential)message.getObjects().get(0);
                        string=(String)message.getObjects().get(1);

                        try {
                            superstore.addEndUser(credential,string);
                            response.getObjects().add(true);

                        } catch (UsernameAlreadyExistsException e) {
                            response.getObjects().add(false);
                        }

                        break;

                    case "login":
                        credential=(Credential)message.getObjects().get(0);
                        try {
                            registeredUser=superstore.getRegisteredUser(credential);
                            this.registeredSession=true;
                            response.getObjects().add(true);
                            string=registeredUser.getClass().toString();
                            string=string.split(" ")[1];
                            string=string.toLowerCase();
                            response.getObjects().add(string);
                        } catch (CredentialNotPresentException e) {
                            response.getObjects().add(false);
                        }

                        break;

                    case "updateProfile":
                        registeredUser1=(RegisteredUser)(message.getObjects().get(0));
                        credential=(Credential)(message.getObjects().get(1));
                        if(registeredUser.authenticate(credential)){
                            registeredUser.updateDetails(registeredUser1);
                        }
                        break;

                    case "logout":
                        registeredSession=false;
                        resetSession();
                        break;

                    case "exit":
                        System.out.println("Exit request received.");
                        endSession=true;
                        try{
                            this.outputStream.close();
                            this.inputStream.close();
                        }

                        catch (IOException ioe){
                            System.err.println("Cannot close client's data-streams");
                        }
                        break;

                    // Superuser methods

                    case "register_warehouse_admin":
                        credential=(Credential)message.getObjects().get(0);
                        string=(String)message.getObjects().get(1);

                        try {
                            superstore.addWarehouseAdmin(string,-1,credential);
                            response.getObjects().add((Boolean)true);

                        } catch (UsernameAlreadyExistsException e) {
                            response.getObjects().add((Boolean)false);
                        }

                        break;

                    case "register_store_admin":
                        credential=(Credential)message.getObjects().get(0);
                        string=(String)message.getObjects().get(1);

                        try {
                            superstore.addStoreAdmin(string,-1,credential);
                            response.getObjects().add(true);

                        } catch (UsernameAlreadyExistsException e) {
                            response.getObjects().add(false);
                        }

                        break;

                    case "create_warehouse":
                        string=(String)message.getObjects().get(0);
                        integer=superstore.createWarehouse(string,null);
                        response.getObjects().add(integer);
                        break;

                    case "create_store":
                        string=(String)message.getObjects().get(0);
                        integer=superstore.createStore(string,null);
                        response.getObjects().add(integer);
                        break;

                    case "link_warehouse_store":
                        integer=(Integer)message.getObjects().get(0); // warehouseId
                        integer1=(Integer)message.getObjects().get(1); // storeId

                        try {
                            superstore.linkWarehouseStore(integer,integer1);
                            response.getObjects().add(true);
                        } catch (WarehouseNotFoundException e) {
                            response.getObjects().add(false);
                        } catch (StoreNotFoundException e) {
                            response.getObjects().add(false);
                        }
                        break;

                    case "link_admin_warehouse_page":
                        stringArrayList=superstore.reflectionOf(superstore.getUnassignedStores());
                        stringArrayList1=superstore.reflectionOf(superstore.getStoreAdminList());
                        response.getObjects().add(stringArrayList);
                        response.getObjects().add(stringArrayList1);
                        break;

                    case "link_admin_store_page":
                        stringArrayList=superstore.reflectionOf(superstore.getUnassignedWarehouses());
                        stringArrayList1=superstore.reflectionOf(superstore.getWarehouseAdminList());
                        response.getObjects().add(stringArrayList);
                        response.getObjects().add(stringArrayList1);
                        break;

                    case "su_get_warehouse_list":
                        stringArrayList=superstore.reflectionOf(superstore.getWarehouseList());
                        response.getObjects().add(stringArrayList);
                        break;

                    case "su_get_store_list":
                        stringArrayList=superstore.reflectionOf(superstore.getStoreList());
                        response.getObjects().add(stringArrayList);
                        break;

                    case "su_get_warehouse_treeview":
                        integer=(Integer)message.getObjects().get(0);
                        warehouse=superstore.getWarehouse(integer);
                        treeView=warehouse.getDatabase().generateTreeView();
                        response.getObjects().add(treeView);
                        break;

                    case "su_get_store_treeview":
                        integer=(Integer)(message.getObjects().get(0));
                        store=superstore.getStore(integer);
                        treeView=store.getDatabase().generateTreeView();
                        response.getObjects().add(treeView);

                    case "su_get_profile":
                        //TODO do we really require a profile page for superuser, or just keep its username, password a constant?
                        break;

                    // Warehouse admin methods

                    //TODO case for updation and deletion of categories, subcategories and items

                    case "warehouse_admin_get_treeview":
                        treeView=warehouse.getDatabase().generateTreeView(); // TODO make sure that warehouse is already set
                        response.getObjects().add(treeView);
                        break;

                    case "warehouse_admin_get_product_list":
                        string=(String)message.getObjects().get(0); //category path
                        stringArrayList=superstore.reflectionOf(warehouse.getDatabase().getCategory(string).getProductArrayList());
                        response.getObjects().add(stringArrayList);
                        break;

                    case "warehouse_admin_get_product":
                        string=(String)message.getObjects().get(0); //name of product
                        try {
                            product=((WarehouseAdmin)registeredUser).getWarehouse().getDatabase().searchProduct(string);
                            response.getObjects().add(true);
                            response.getObjects().add(product);
                        } catch (ProductNotFoundException e) {
                            response.getObjects().add(false);
                        }

                        break;

                    case "warehouse_admin_update_product":
                        product1=(Product)message.getObjects().get(0);
                        product.update(product1);
                        break;

                    case "warehouse_admin_update_category":
                        string=(String)(message.getObjects().get(0)); // categoryPath
                        string1=(String)(message.getObjects().get(1)); // newCategoryName
                        category=warehouse.getDatabase().getCategory(string);
                        category.setName(string1);
                        response.getObjects().add(warehouse.getDatabase().generateTreeView());
                        break;

                    case "warehouse_admin_profile":
                        response.getObjects().add((WarehouseAdmin)registeredUser);
                        break;

                    //TODO case for Order handling from stores

                    //Store admin methods

                    //TODO case for managing categories and subcategories of items in the store
                    //TODO case for addition, updation and deletion

                    case "store_admin_get_treeview":
                        treeView=store.getDatabase().generateTreeView();
                        response.getObjects().add(treeView);
                        break;

                    case "store_admin_get_product_list":
                        string=(String)message.getObjects().get(0); //categoryPath
                        category=store.getDatabase().getCategory(string);
                        stringArrayList=superstore.reflectionOf(category.getProductArrayList());
                        response.getObjects().add(stringArrayList);
                        break;

                    case "store_admin_profile":
                        response.getObjects().add((StoreAdmin)registeredUser);
                        break;

                    case "store_admin_get_product":
                        string=(String)message.getObjects().get(0);

                        try {
                            product=((StoreAdmin)registeredUser).getStore().getDatabase().searchProduct(string);
                            response.getObjects().add(true);
                            response.getObjects().add(product);
                        } catch (ProductNotFoundException e) {
                            response.getObjects().add(false);
                        }

                        break;

                    case "store_admin_update_product":
                        product1=(Product)message.getObjects().get(0);
                        product.update(product1);
                        break;

                    case "store_admin_update_category":
                        string=(String)(message.getObjects().get(0)); // categoryPath
                        string1=(String)(message.getObjects().get(1)); // newCategoryName
                        category=store.getDatabase().getCategory(string);
                        category.setName(string1);
                        response.getObjects().add(store.getDatabase().generateTreeView());
                        break;

                    // Enduser methods

                    case "enduser_get_store_list":
                        stringArrayList=superstore.reflectionOf(superstore.getStoreList());
                        response.getObjects().add(stringArrayList);
                        break;

                    case "enduser_get_treeview":
                        integer=(Integer) message.getObjects().get(0); // storeId
                        store=superstore.getStore(integer);
                        treeView=store.getDatabase().generateTreeView();
                        response.getObjects().add(treeView);
                        break;

                    case "enduser_get_product_list":
                        string=(String) message.getObjects().get(0); // categoryPath
                        category=store.getDatabase().getCategory(string); // assuming store field is already initialized (logically it should be)
                        stringArrayList=superstore.reflectionOf(category.getProductArrayList());
                        response.getObjects().add(stringArrayList);
                        break;

                    case "enduser_get_product":
                        string=(String) message.getObjects().get(0); //productName
                        try {
                            product=store.getDatabase().searchProduct(string);
                            response.getObjects().add((Boolean)true);
                            response.getObjects().add(product.getBasicDetails());
                        } catch (ProductNotFoundException e) {
                            response.getObjects().add(false);
                        }
                        break;

                    case "enduser_add_to_cart":
                        integer=(Integer) message.getObjects().get(0);
                        ((EndUser)registeredUser).addItem(product,integer);
                        break;

                    case "enduser_get_cart":
                        stringArrayList=superstore.reflectionOf(cart.getCartItems());
                        response.getObjects().add(stringArrayList);
                        break;

                    case "enduser_checkout":
                        try {
                            ((EndUser)registeredUser).checkout(store);
                            response.getObjects().add(true);
                        } catch (ProductNotFoundException | CartNotValidException | InsufficientFundsException | StockInsufficientException e) {
                            response.getObjects().add(false);
                            response.getObjects().add(e.getMessage());
                        }
                        break;

                    case "enduser_profile":
                        response.getObjects().add((EndUser)registeredUser);
                        break;

                    // Guest user methods:

                    case "guest_get_store_list":
                        stringArrayList=superstore.reflectionOf(superstore.getStoreList());
                        response.getObjects().add(stringArrayList);
                        break;

                    case "guest_get_treeview":
                        integer=(Integer) message.getObjects().get(0); // storeId
                        store=superstore.getStore(integer);
                        treeView=store.getDatabase().generateTreeView();
                        response.getObjects().add(treeView);
                        break;

                    case "guest_get_product_list":
                        string=(String) message.getObjects().get(0); // categoryPath
                        category=store.getDatabase().getCategory(string); // assuming store field is already initialized (logically it should be)
                        stringArrayList=superstore.reflectionOf(category.getProductArrayList());
                        response.getObjects().add(stringArrayList);
                        break;

                    case "guest_get_product":
                        string=(String) message.getObjects().get(0); //productName
                        try {
                            product=store.getDatabase().searchProduct(string);
                            response.getObjects().add(true);
                            response.getObjects().add(product.getBasicDetails());
                        } catch (ProductNotFoundException e) {
                            response.getObjects().add(false);
                        }
                        break;

                    // For debugging purposes only

                    case "debugging":
//                        System.out.println("Debugging case invoked.");
//                        System.out.println("Send the response?");
//                        Scanner sc=new Scanner(System.in);
//                        sc.nextLine();
//                        response.getObjects().add("server responded");
//                        System.out.println("Response sent.");

                        ArrayList<String> arrayList=new ArrayList<>();
                        arrayList.add("Reeshabh");
                        arrayList.add("Apurv");

                        response.getObjects().add(arrayList);
                        break;
                }

                try {
                    outputStream.writeObject(response);
                } catch (IOException e) {
                    System.err.println("Error writing into the output stream.");
                }

                // Once the command for login comes, login() will be called and registeredSession, registeredUser will be set
                // Here the commands will be accepted.
            }

            System.out.println("Connection closed.");
        }
    }
}