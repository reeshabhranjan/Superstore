import java.util.ArrayList;
import java.util.HashMap;

public class Superstore {

    //TODO add consructor, implement serialization, make this class Singleton
    private HashMap<Integer, Warehouse> warehouseHashMap;
    private HashMap<Integer, Store> storeHashMap;
    private HashMap<Credential, RegisteredUser> registeredUserHashMap;
    private HashMap<Credential, EndUser> endUserHashMap;
    private HashMap<Credential, WarehouseAdmin> warehouseAdminHashMap;
    private HashMap<Credential, StoreAdmin> storeAdminHashMap;

    public void createWarehouse(String warehouseName, int id, WarehouseAdmin warehouseAdmin) {

        warehouseHashMap.put(id, new Warehouse(id, warehouseName, warehouseAdmin));
    }

    public void createStore(String storeName, int id, StoreAdmin storeAdmin) {
        storeHashMap.put(id, new Store(id, storeName, storeAdmin));
    }

    public void linkWarehouseStore(int warehouseId, int storeId) {
        getWarehouse(warehouseId).addStore(getStore(storeId));
        getStore(storeId).setWarehouse(getWarehouse(warehouseId));
    }

    public void addWarehouseAdmin(String name, int warehouseId, Credential credential) {

        WarehouseAdmin warehouseAdmin = new WarehouseAdmin(credential, name, getWarehouse(warehouseId));
        registeredUserHashMap.put(credential, warehouseAdmin);
        warehouseAdminHashMap.put(credential, warehouseAdmin);
    }

    public void addStoreAdmin(String name, int storeId, Credential credential) {

        StoreAdmin storeAdmin = new StoreAdmin(credential, name, getStore(storeId));
        registeredUserHashMap.put(credential, storeAdmin);
        storeAdminHashMap.put(credential, storeAdmin);
    }

    public ArrayList<Warehouse> getWarehouseList() {
        ArrayList<Warehouse> warehouseArrayList = new ArrayList<Warehouse>(warehouseHashMap.values());
        return warehouseArrayList;
    }

    public ArrayList<Store> getStoreList() {
        ArrayList<Store> storeArrayList = new ArrayList<Store>(storeHashMap.values());
        return storeArrayList;
    }

    public Warehouse getWarehouse(int id) {
        return warehouseHashMap.get(id);
    }

    public Store getStore(int id) {
        return storeHashMap.get(id);
    }

    public void addEndUser(Credential credential, String name) {

        EndUser endUser = new EndUser(credential, name);
        registeredUserHashMap.put(credential, endUser);
        endUserHashMap.put(credential, endUser);
    }

    public RegisteredUser getRegisteredUser(Credential credential){
        return registeredUserHashMap.get(credential);
    }

    public ArrayList<RegisteredUser> getRegisteredUserList(){

        ArrayList<RegisteredUser> registeredUserArrayList =new ArrayList<RegisteredUser>(registeredUserHashMap.values());
        return registeredUserArrayList;
    }

    public ArrayList<EndUser> getEndUserList(){

        ArrayList<EndUser> endUserArrayList=new ArrayList<EndUser>(endUserHashMap.values());
        return endUserArrayList;
    }

    public ArrayList<WarehouseAdmin> getWarehouseAdminList(){

        ArrayList<WarehouseAdmin> warehouseAdminArrayList=new ArrayList<WarehouseAdmin>(warehouseAdminHashMap.values());
        return warehouseAdminArrayList;
    }

    public ArrayList<StoreAdmin> getStoreAdminList(){

        ArrayList<StoreAdmin> storeAdminArrayList=new ArrayList<StoreAdmin>(storeAdminHashMap.values());
        return storeAdminArrayList;
    }
}
