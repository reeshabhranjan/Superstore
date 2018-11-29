package classes;

import database.Database;

import java.io.Serializable;

public class Store implements Serializable {
    private final int id;
    private StoreAdmin admin;
    private String name;
    private Warehouse warehouse;
    Database database;

    public Store(int id, String name, StoreAdmin admin) {
        this.id=id;
        this.name=name;
        this.admin=admin;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse=warehouse;
    }
}
