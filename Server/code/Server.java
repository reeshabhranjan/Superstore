import classes.*;
import exceptions.CredentialNotPresentException;
import exceptions.UsernameAlreadyExistsException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server{

    //TODO decide where the opcodes will be accepted and where the appropriate methods be called.

    private static volatile Superstore superstore; //TODO should there be a 'volatile' here?
    private volatile int sessionCount;

    public static void main(String[] args) throws IOException {

        //TODO deserialize the Superstore here.

        ServerSocket serverSocket=new ServerSocket(1234);
        ExecutorService executorService= Executors.newFixedThreadPool(4);

        while (true){

            Socket client=serverSocket.accept();
            Runnable session=new Session(client,superstore);
            executorService.execute(session);
        }
    }

    private static class Session implements Runnable {

        private ObjectOutputStream outputStream;
        private ObjectInputStream inputStream;
        private volatile Superstore superstore;
        private boolean registeredSession;
        private boolean endSession;
        private Socket client;
        private RegisteredUser registeredUser;

        public Session(Socket client, Superstore superstore) {

            this.outputStream=null;
            this.inputStream=null;

            try{
                this.outputStream=new ObjectOutputStream(client.getOutputStream());
                this.inputStream=new ObjectInputStream(client.getInputStream());
                throw new IOException();
            }

            catch (IOException ioe) {
                System.err.println("Cannot find client's data-streams");
            }

            finally {

                try{
                    this.outputStream.close();
                    this.inputStream.close();
                }

                catch (IOException ioe){
                    System.err.println("Cannot close client's data-streams");
                }

            }

            this.superstore=superstore;
            this.registeredSession=false;
            this.endSession=false;
            this.client=client;
            this.registeredUser=null;
        }

        @Override
        public void run() {

            while(!endSession){

                Message message=null;
                Message response=new Message();
                Credential credential;
                String string;
                RegisteredUser registeredUser;

                try {
                    if(inputStream.available()==0){
                        continue;
                    }

                    else{
                        message=(Message)inputStream.readObject();
                    }
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Error while reading input stream.");
                }

                switch (message.getOpcode()){

                    case "register_end_user":
                        credential=(Credential)message.getObjects().get(0);
                        string=(String)message.getObjects().get(1);

                        try {
                            superstore.addEndUser(credential,string);
                            response.getObjects().add(true);

                        } catch (UsernameAlreadyExistsException e) {
                            response.getObjects().add(false);
                        }

                        break;

                    case "register_warehouse_admin":
                        credential=(Credential)message.getObjects().get(0);
                        string=(String)message.getObjects().get(1);

                        try {
                            superstore.addWarehouseAdmin(string,-1,credential);
                            response.getObjects().add((Boolean)true);

                        } catch (UsernameAlreadyExistsException e) {
                            response.getObjects().add((Boolean)false);
                        }

                        break;

                    case "register_store_admin":
                        credential=(Credential)message.getObjects().get(0);
                        string=(String)message.getObjects().get(1);

                        try {
                            superstore.addStoreAdmin(string,-1,credential);
                            response.getObjects().add(true);

                        } catch (UsernameAlreadyExistsException e) {
                            response.getObjects().add(false);
                        }

                        break;

                    case "login":
                        credential=(Credential)message.getObjects().get(0);
                        try {
                            registeredUser=superstore.getRegisteredUser(credential);
                            this.registeredSession=true;
                            response.getObjects().add(true);
                            response.getObjects().add(registeredUser);
                        } catch (CredentialNotPresentException e) {
                            response.getObjects().add(false);
                        }


                }

                try {
                    outputStream.writeObject(response);
                } catch (IOException e) {
                    System.err.println("Error writing into the output stream.");
                }

                // Once the command for login comes, login() will be called and registeredSession, registeredUser will be set
                // Here the commands will be accepted.
            }
        }
    }
}