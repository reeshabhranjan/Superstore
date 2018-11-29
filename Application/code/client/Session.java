package client;

import classes.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Session {
    private Socket server;
    private ObjectInputStream in=null;
    private ObjectOutputStream out=null;

    public Session(String serverName, int portNumber) {
        try {
            server = new Socket(serverName,portNumber);
            in= new ObjectInputStream(server.getInputStream());
            out= new ObjectOutputStream(server.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendData(Message message)
    {
        try {
            out = new ObjectOutputStream(server.getOutputStream());
            out.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public Message getData()
    {
        Message message=null;
        try {
//            boolean dataReceived = false;
            while(in.available()==0) {}
            message =(Message) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            return message;
        }
    }
}
