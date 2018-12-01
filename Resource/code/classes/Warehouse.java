package classes;

import database.Database;
import database.Product;
import exceptions.ProductNotFoundException;

import java.io.Serializable;
import java.util.ArrayList;

public class Warehouse implements Serializable {

    private final int id;
    private String name;
    private WarehouseAdmin admin;
    private ArrayList<Store> storeList;
    private Database database;

    public Warehouse(int id, String name, WarehouseAdmin admin) {
        this.id=id;
        this.name=name;
        this.admin=admin;
        this.database=new Database();
    }

    public void addStore(Store store) {
        this.storeList.add(store);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public WarehouseAdmin getAdmin() {
        return admin;
    }

    public ArrayList<Store> getStoreList() {
        return storeList;
    }

    public Database getDatabase() {
        return database;
    }

    public Boolean orderProduct(Product product, int quantity) {

        Product localProduct= null;
        try {
            localProduct = database.searchProduct(product.getName());
        } catch (ProductNotFoundException e) { // in case the product was deleted from Warehouse but not from the store.
            Warehouse warehouse=admin.getOptimumWarehouse(product);
            if(warehouse==null){
                return false;
            }
            return warehouse.orderProduct(product,quantity);
        }

        if (localProduct.getStockCount()>=quantity){

            localProduct.setStockCount(localProduct.getStockCount()-quantity);
            return true;
        }

        else if(localProduct.getStockCount()<quantity) {
            //TODO optimisation algorithm to get products from other warehouses.
            Warehouse warehouse=admin.getOptimumWarehouse(product);
            if(warehouse==null){
                return false;
            }
            return warehouse.orderProduct(product,quantity);
        }

        return false;
    }
}
