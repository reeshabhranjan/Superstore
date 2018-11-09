public abstract class RegisteredUser implements User {
    private Credential credential;
    private String name;
    private final int id;

    public RegisteredUser(Credential credential, String name, int id) {
        this.credential=credential;
        this.name=name;
        this.id = id;
    }

    public void updateCredentials(Credential credential){
        this.credential=credential;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public boolean authenticate(Credential credential){
        return(this.credential==credential);
    }
}
