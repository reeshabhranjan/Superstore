import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class Server {

    private class ConnectionHandler extends RecursiveAction {
        private final Socket client;
        private final Superstore superstore;

        public ConnectionHandler(Socket client)
        {
            this.client=client;
            superstore=Superstore.getInstance();
        }
        @Override
        protected void compute(){
            ObjectInputStream in=null;
            ObjectOutputStream out=null;
            try {
                in=new ObjectInputStream(client.getInputStream());
                out=new ObjectOutputStream(client.getOutputStream());
                String message=(String) in.readObject();
                Credential credential=(Credential) in.readObject();
                User user;
                switch (message.toLowerCase())
                {
                    case "superuser":
                        user=superstore.getRegisteredUser(credential);
                        out.writeObject(user);
                        break;
                    case "enduser":
                        user=superstore.getRegisteredUser(credential);
                        out.writeObject(user);
                        break;
                    case "warehouseadmin":
                        user=superstore.getRegisteredUser(credential);
                        out.writeObject(user);
                        break;
                    case "storeadmin":
                        user=superstore.getRegisteredUser(credential);
                        out.writeObject(user);
                        break;
                    case "storelist":
                        out.writeObject(superstore.getStoreList());
                        break;
                    default:
                        out.writeObject("Error");
                        break;
                }
            }
            catch (IOException e) {
                System.out.println("Can't get inputstream from client.");
            }
            catch (Exception e){
                System.out.println("Some Other Exception.");
            }
            finally{
                //TODO handle IOException
                in.close();
                client.close();
            }
        }
    }
    public static void main(String[] args)throws IOException {
        ServerSocket serversocket = new ServerSocket(80);
        ForkJoinPool pool=new ForkJoinPool(2);
        Server server=new Server();
        while(true)
        {
            Socket client=serversocket.accept();
            server.new ConnectionHandler(client).fork();

        }
    }
}