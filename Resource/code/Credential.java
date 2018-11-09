public class Credential {

    //TODO decide whether to keep username and password final or not
    private String username;
    private String password;

    @Override
    public boolean equals(Object obj) {
//        return super.equals(obj);

        if(obj!=null && getClass()==obj.getClass()) {

            Credential credential=(Credential)obj;
            boolean condition=(credential.getUsername().equals(this.getUsername()));
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
