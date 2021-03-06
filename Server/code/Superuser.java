import classes.Credential;

import java.io.Serializable;

public class Superuser implements Serializable {

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
