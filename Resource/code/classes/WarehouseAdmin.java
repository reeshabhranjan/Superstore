package classes;

import database.Product;
import exceptions.ProductNotFoundException;

import java.io.Serializable;
import java.util.ArrayList;

public class WarehouseAdmin extends RegisteredUser implements Serializable {

    private final Warehouse warehouse;
    private ArrayList<Warehouse> warehouseList;

    public WarehouseAdmin(Credential credential, String name, int id, Warehouse warehouse, ArrayList<Warehouse> warehouseList) {
        super(credential,name, id);
        this.warehouse=warehouse;
        this.warehouseList=warehouseList;
    }

    @Override
    public void runSession() {
        //TODO complete definition
    }

    @Override
    public void showMenu() {
        //TODO complete definition
    }

    @Override
    public void update(RegisteredUser registeredUser) {

    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public ArrayList<Warehouse> getWarehouseList() {
        return warehouseList;
    }

    public Warehouse getOptimumWarehouse(Product product){

        Warehouse warehouse=null;
        Product product1= null;
        int max=0;

        for (Warehouse warehouse1 :
                warehouseList) {
            try {
                product1 = warehouse1.getDatabase().searchProduct(product.getName());
            } catch (ProductNotFoundException e) {
                continue;
            }
            if(product1.getStockCount()>=max && product1.getStockCount()>=product.getEOQ()){
                max=product1.getStockCount();
                warehouse=warehouse1;
            }
        }

        // NOT REQUIRED:
//        if(warehouse!=null){
//            if(product1.getStockCount()<product.getEOQ()){
//                warehouse=null;
//            }
//        }

        return warehouse;
    }
}
