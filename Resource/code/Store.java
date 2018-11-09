import database.Database;

public class Store {
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
