public class StoreAdmin extends RegisteredUser {
    private final Store store;

    public StoreAdmin(Credential credential, String name, Store store) {
        super(credential,name);
        this.store=store;
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
