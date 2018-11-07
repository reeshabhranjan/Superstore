public class WarehouseAdmin extends User{
    private final Warehouse warehouse;

    public WarehouseAdmin(Credential credential, String name, Warehouse warehouse) {
        super(credential,name);
        this.warehouse=warehouse;
    }
}
