import javax.swing.*;
import java.util.ArrayList;

public class Warehouse {

    private final int id;
    private String name;
    private WarehouseAdmin admin;
    private ArrayList<Store> storeList;

    public Warehouse(int id, String name, WarehouseAdmin admin) {
        this.id=id;
        this.name=name;
        this.admin=admin;
    }

    public void addStore(Store store) {
        this.storeList.add(store);
    }
}
