import java.util.ArrayList;
import java.util.HashMap;

public class Superstore {
    private HashMap<Integer, Warehouse> warehouseHashMap;
    private HashMap<Integer,Store> storeHashMap;
    private HashMap<Credential,User> userHashMap;

    public void createWarehouse(String warehouseName, int id, WarehouseAdmin warehouseAdmin){

        warehouseHashMap.put(id,new Warehouse(id,warehouseName,warehouseAdmin));
    }

    public void createStore(String storeName, int id, StoreAdmin storeAdmin){
        storeHashMap.put(id,new Store(id,storeName,storeAdmin));
    }

    public void linkWarehouseStore(int warehouseId, int storeId){
        getWarehouse(warehouseId).addStore(getStore(storeId));
        getStore(storeId).setWarehouse(getWarehouse(warehouseId));
    }

    public void createWarehouseAdmin(String name, int warehouseId, Credential credential){
        userHashMap.put(credential,new WarehouseAdmin(credential,name,getWarehouse(warehouseId)));
    }

    public void createStoreAdmin(String name, int storeId, Credential credential){
        userHashMap.put(credential,new StoreAdmin(credential,name,getStore(storeId)));
    }

    public ArrayList<Warehouse> getWarehouseList(){
        ArrayList<Warehouse> warehouseArrayList=new ArrayList<Warehouse>(warehouseHashMap.values());
        return warehouseArrayList;
    }

    public ArrayList<Store> getStoreList(){
        ArrayList<Store> storeArrayList=new ArrayList<Store>(storeHashMap.values());
        return storeArrayList;
    }

    public Warehouse getWarehouse(int id){
        return warehouseHashMap.get(id);
    }

    public Store getStore(int id){
        return storeHashMap.get(id);
    }

    public void addEndUser(Credential credential, String name){
        userHashMap.put(credential,new EndUser(credential,name));
    }
}
