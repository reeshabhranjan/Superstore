public class StoreAdmin extends User{
    private final Store store;

    public StoreAdmin(Credential credential, String name, Store store) {
        super(credential,name);
        this.store=store;
    }
}
