import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server{

    //TODO decide where the opcodes will be accepted and where the appropriate methods be called.

    private static volatile Superstore superstore; //TODO should there be a 'volatile' here?
    private volatile int sessionCount;

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket=new ServerSocket(1234);
        ExecutorService executorService= Executors.newFixedThreadPool(4);

        while (true){

            Socket client=serverSocket.accept();
            Runnable session=new Session(client,superstore);
            executorService.execute(session);
        }
    }

    private static class Session implements Runnable {

        private DataOutputStream outputStream;
        private DataInputStream inputStream;
        private volatile Superstore superstore;
        private boolean registeredSession;
        private boolean endSession;
        private Socket client;

        public Session(Socket client, Superstore superstore) {

            this.outputStream=null;
            this.inputStream=null;

            try{
                this.outputStream=new DataOutputStream(client.getOutputStream());
                this.inputStream=new DataInputStream(client.getInputStream());
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
        }

        @Override
        public void run() {

            while(!endSession){

                // Here the commands will be accepted.
            }
        }

        public void registerEndUser(Credential credential,String name){
            superstore.addEndUser(credential,name);
        }

        public void registerWarehouseAdmin(Credential credential,String name, int warehouseId){
            superstore.addWarehouseAdmin(name,warehouseId,credential);
        }

        public void registerStoreAdmin(Credential credential, String name, int storeId){
            superstore.addStoreAdmin(name,storeId,credential);
        }

        public RegisteredUser login(Credential credential){

            RegisteredUser registeredUser=null;

            registeredUser=superstore.getRegisteredUser(credential);

            return registeredUser;
        }
    }
}