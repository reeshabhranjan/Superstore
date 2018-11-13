import classes.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Superstore {

    private static final Superstore superstore;

    static{
        superstore = new Superstore();
    }
    private Superstore() {

    }
    private HashMap<Integer, Warehouse> warehouseHashMap;
    private HashMap<Integer, Store> storeHashMap;
    private HashMap<Credential, RegisteredUser> registeredUserHashMap;
    private HashMap<Credential, EndUser> endUserHashMap;
    private HashMap<Credential, WarehouseAdmin> warehouseAdminHashMap;
    private HashMap<Credential, StoreAdmin> storeAdminHashMap;
    private int endUserCount,warehouseAdminCount,storeAdminCount,storeCount,warehouseCount,registeredUserCount;
    //TODO can add a log-history of the superstore.

    public static Superstore getInstance() {
        return superstore;
    }

    public int getEndUserCount() {
        return endUserCount;
    }

    public int getWarehouseAdminCount() {
        return warehouseAdminCount;
    }

    public int getStoreAdminCount() {
        return storeAdminCount;
    }

    public int getStoreCount() {
        return storeCount;
    }

    public int getWarehouseCount() {
        return warehouseCount;
    }

    public int getRegisteredUserCount() {
        return registeredUserCount;
    }

    public void createWarehouse(String warehouseName, WarehouseAdmin warehouseAdmin) {

        int id=++this.warehouseCount;
        warehouseHashMap.put(id, new Warehouse(id, warehouseName, warehouseAdmin));
    }

    public void createStore(String storeName, StoreAdmin storeAdmin) {

        int id=++this.storeCount;
        storeHashMap.put(id, new Store(id, storeName, storeAdmin));
    }

    public void linkWarehouseStore(int warehouseId, int storeId) {
        getWarehouse(warehouseId).addStore(getStore(storeId));
        getStore(storeId).setWarehouse(getWarehouse(warehouseId));
    }

    public void addWarehouseAdmin(String name, int warehouseId, Credential credential) {

        WarehouseAdmin warehouseAdmin = new WarehouseAdmin(credential, name, ++this.warehouseAdminCount, getWarehouse(warehouseId),getWarehouseList());
        registeredUserHashMap.put(credential, warehouseAdmin);
        warehouseAdminHashMap.put(credential, warehouseAdmin);
        ++this.registeredUserCount;
    }

    public void addStoreAdmin(String name, int storeId, Credential credential) {

        StoreAdmin storeAdmin = new StoreAdmin(credential, name, ++this.storeAdminCount, getStore(storeId));
        registeredUserHashMap.put(credential, storeAdmin);
        storeAdminHashMap.put(credential, storeAdmin);
        ++this.registeredUserCount;
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

        EndUser endUser = new EndUser(credential, name, ++this.endUserCount);
        registeredUserHashMap.put(credential, endUser);
        endUserHashMap.put(credential, endUser);
        ++this.registeredUserCount;
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

    public WarehouseAdmin getWarehouseAdmin(Credential credential){

        return warehouseAdminHashMap.get(credential);
    }

    public StoreAdmin getStoreAdmin(Credential credential){

        return storeAdminHashMap.get(credential);
    }

    public EndUser getEndUser(Credential credential){

        return endUserHashMap.get(credential);
    }
}
