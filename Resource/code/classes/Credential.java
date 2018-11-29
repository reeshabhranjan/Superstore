package classes;

import java.io.Serializable;

public final class Credential implements Serializable {

    //TODO decide whether to keep username and password final or not
    private final String username;
    private final String password;


    public Credential(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
//        return super.equals(obj);

        if(obj!=null && getClass()==obj.getClass()) {

            Credential credential=(Credential)obj;
            boolean condition=(credential.getUsername().equalsIgnoreCase(this.getUsername()));
            condition=(condition && (credential.getPassword().equals(this.getPassword())));
            return condition;
        }

        return false;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
