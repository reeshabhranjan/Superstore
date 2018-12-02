package classes;

import java.io.Serializable;

public abstract class RegisteredUser implements User, Serializable {
    private Credential credential;
    private String name;
    private final int id;
    private String phoneNumber;
    private String address;

    public RegisteredUser(Credential credential, String name, int id) {
        this.credential=credential;
        this.name=name;
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public abstract void update(RegisteredUser registeredUser);

    public void updateDetails(RegisteredUser registeredUser){
        this.credential=registeredUser.credential;
        this.address=registeredUser.address;
        this.name=registeredUser.name;
        this.phoneNumber=registeredUser.phoneNumber;
    }
    public String getUsername()
    {
        return credential.getUsername();
    }
    public void update(String name, String phoneNumber, String address, String newUsername, String currentPasword, String newPassword)
    {
        if(!name.equals(""))
        {
            this.name=name;
        }
        if(!phoneNumber.equals(""))
        {
            this.phoneNumber=phoneNumber;
        }
        if(!address.equals(""))
        {
            this.address=address;
        }
        if(!newUsername.equals(""))
        {
            credential = new Credential(newUsername,this.credential.getPassword());

        }
        if(!currentPasword.equals("") && currentPasword.equals(credential.getPassword()))
        {
            if(!newPassword.equals(""))
            {
                credential = new Credential(credential.getUsername(),newPassword);
            }
        }

    }
}
