public abstract class RegisteredUser implements User {
    private Credential credential;
    private String name;

    public RegisteredUser(Credential credential, String name) {
        this.credential=credential;
        this.name=name;
    }

    public void updateCredentials(Credential credential){
        //TODO decide if we need to move this method to the Credential-class from here:
        this.credential=credential;
    }

    public String getName() {
        return name;
    }

    public boolean authenticate(Credential credential){

        return(this.credential.equals(credential));
        //TODO add equals method in credential
    }
}
