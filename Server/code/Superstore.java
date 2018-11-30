import classes.*;
import exceptions.CredentialNotPresentException;
import exceptions.StoreNotFoundException;
import exceptions.UsernameAlreadyExistsException;
import exceptions.WarehouseNotFoundException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Superstore implements Serializable{

    private static final Superstore superstore;

    static{
        superstore = deserialize();
    }

    public static Superstore getInstance() {
        return superstore;
    }

    private Superstore(){
        // to implement Singleton design pattern
    }

    public void serialize(){

        ObjectOutputStream out = null;

        try {
            String fileName = "data/" + "superstore" + ".dat";
            File file = new File(fileName);

            if (!file.exists()) {
                file.createNewFile();
            }

            out = new ObjectOutputStream(new FileOutputStream(fileName, false));
            out.writeObject(this);
        } catch (IOException ioe){
            System.err.println("Error while serialization.");
        }
        finally {
            try {
                if(out!=null){
                    out.close();
                }
            } catch (IOException e) {
                System.err.println("Error while closing the stream.");
            }
        }
    }

    private static Superstore deserialize(){
        ObjectInputStream in = null;
        String fileName = "data/" + "superstore" + ".dat";

        try {
            in = new ObjectInputStream(new FileInputStream(fileName));
            Superstore superstore = (Superstore) in.readObject();
            return superstore;
        }
        catch (FileNotFoundException e) {
            System.err.println("Error while deserialization.");
        }
        catch (IOException e) {
            System.err.println("Error while deserialization.");
        }
        catch (ClassNotFoundException e) {
            System.err.println("Error while deserialization.");
        }
        finally {
            try {

                if(in!=null){
                    in.close();
                }
            } catch (IOException e) {
                System.err.println("Error while deserialization.");
            }
        }

        return new Superstore();
    }

    private HashMap<Integer, Warehouse> warehouseHashMap;
    private HashMap<Integer, Store> storeHashMap;
    private HashMap<Credential, RegisteredUser> registeredUserHashMap;
    private HashMap<Credential, EndUser> endUserHashMap;
    private HashMap<Credential, WarehouseAdmin> warehouseAdminHashMap;
    private HashMap<Credential, StoreAdmin> storeAdminHashMap;
    private HashMap<String, Credential> usernameCredentialHashmap;
    private int endUserCount,warehouseAdminCount,storeAdminCount,storeCount,warehouseCount,registeredUserCount;
    //TODO can add a log-history of the superstore.

//    public static Superstore getInstance() {
//        return superstore;
//    }

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

    public int createWarehouse(String warehouseName, WarehouseAdmin warehouseAdmin) {

        //TODO raise exception in case of same name, maybe?
        int id=++this.warehouseCount;
        warehouseHashMap.put(id, new Warehouse(id, warehouseName, warehouseAdmin));
        return id;
    }

    public int createStore(String storeName, StoreAdmin storeAdmin) {

        int id=++this.storeCount;
        storeHashMap.put(id, new Store(id, storeName, storeAdmin));
        return id;
    }

    public void linkWarehouseStore(int warehouseId, int storeId) throws WarehouseNotFoundException, StoreNotFoundException {

        if(getWarehouse(warehouseId)==null){
            throw new WarehouseNotFoundException("Warehouse with id "+warehouseId+" does not exist.");
        }

        if(getStore(storeId)==null){
            throw new StoreNotFoundException("Warehouse with id "+warehouseId+" does not exist.");
        }

        getWarehouse(warehouseId).addStore(getStore(storeId));
        getStore(storeId).setWarehouse(getWarehouse(warehouseId));
    }

    public void addWarehouseAdmin(String name, int warehouseId, Credential credential) throws UsernameAlreadyExistsException {

        if(usernameCredentialHashmap.containsKey(credential.getUsername())){
            throw new UsernameAlreadyExistsException("Username is already taken.");
        }

        WarehouseAdmin warehouseAdmin = new WarehouseAdmin(credential, name, ++this.warehouseAdminCount, getWarehouse(warehouseId),getWarehouseList());
        registeredUserHashMap.put(credential, warehouseAdmin);
        warehouseAdminHashMap.put(credential, warehouseAdmin);
        usernameCredentialHashmap.put(credential.getUsername(),credential);
        ++this.registeredUserCount;
    }

    public void addStoreAdmin(String name, int storeId, Credential credential) throws UsernameAlreadyExistsException {

        if(usernameCredentialHashmap.containsKey(credential.getUsername())){
            throw new UsernameAlreadyExistsException("Username is already taken.");
        }

        StoreAdmin storeAdmin = new StoreAdmin(credential, name, ++this.storeAdminCount, getStore(storeId));
        registeredUserHashMap.put(credential, storeAdmin);
        storeAdminHashMap.put(credential, storeAdmin);
        usernameCredentialHashmap.put(credential.getUsername(),credential);
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

//        if(id==-1){
//            return null;
//        }

        if(!warehouseHashMap.containsKey(id)){
            return null;
        }

        return warehouseHashMap.get(id);
    }

    public Store getStore(int id) {

//        if(id==-1){
//            return null;
//        }

        if(!storeHashMap.containsKey(id)){
            return null;
        }

        return storeHashMap.get(id);
    }

    public void addEndUser(Credential credential, String name) throws UsernameAlreadyExistsException {

        if(usernameCredentialHashmap.containsKey(credential.getUsername())){
            throw new UsernameAlreadyExistsException("Username is already taken.");
        }

        EndUser endUser = new EndUser(credential, name, ++this.endUserCount);
        registeredUserHashMap.put(credential, endUser);
        endUserHashMap.put(credential, endUser);
        usernameCredentialHashmap.put(credential.getUsername(),credential);
        ++this.registeredUserCount;
    }

    public RegisteredUser getRegisteredUser(Credential credential) throws CredentialNotPresentException {

        if(!registeredUserHashMap.containsKey(credential)){
            throw new CredentialNotPresentException("Credentials not present in the database.");
        }
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

    public ArrayList<String> reflectionOf(ArrayList<?> objects){

//        ArrayList<Object> objects=(ArrayList<Object>)object;
        ArrayList<String> reflection=new ArrayList<>();

        for (Object object1 :
                objects) {
            reflection.add(object1.toString());
        }

        return reflection;
    }
}
