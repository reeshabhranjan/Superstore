package classes;

public class StoreAdmin extends RegisteredUser {
    private final Store store;

    public StoreAdmin(Credential credential, String name, int id, Store store) {
        super(credential,name, id);
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

    @Override
    public void update(RegisteredUser registeredUser) {

    }
}
