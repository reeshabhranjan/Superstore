import classes.Credential;

public class Superuser {

    Superstore superstore;
    Credential credential;

    public Superuser(Superstore superstore) {
        this.superstore = superstore;
        credential=new Credential("admin","admin");
    }

    public boolean authorize(Credential credential){
        return (this.credential.equals(credential));
    }

    public void updateCredential(Credential credential){
        this.credential=credential;
    }
}
