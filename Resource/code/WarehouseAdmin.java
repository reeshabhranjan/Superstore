import java.util.ArrayList;

public class WarehouseAdmin extends RegisteredUser {

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
}
