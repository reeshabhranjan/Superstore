public class Store {
    private final int id;
    private StoreAdmin admin;
    private String name;
    private Warehouse warehouse;

    public Store(int id, String name, StoreAdmin admin) {
        this.id=id;
        this.name=name;
        this.admin=admin;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse=warehouse;
    }
}
