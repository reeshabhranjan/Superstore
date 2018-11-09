public class WarehouseAdmin extends RegisteredUser {
    private final Warehouse warehouse;

    public WarehouseAdmin(Credential credential, String name, Warehouse warehouse) {
        super(credential,name);
        this.warehouse=warehouse;
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
