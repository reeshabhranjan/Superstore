package classes;

import database.Database;

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
}
