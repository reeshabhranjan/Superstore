import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server{

    //TODO decide where the opcodes will be accepted and where the approprate methods be called.

    private volatile Superstore superstore;
    private volatile int sessionCount;

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket=new ServerSocket(1234);
        ExecutorService executorService= Executors.newFixedThreadPool(4);

        while (true){

            Socket client=serverSocket.accept();
            Runnable session=new Session(client);
            executorService.execute(session);
        }
    }

    private static class Session implements Runnable {

        private DataOutputStream outputStream;
        private DataInputStream inputStream;

        Socket client;

        public Session(Socket client) {
            this.client=client;
        }

        @Override
        public void run() {

            //TODO This run should be invoked only after a login() is done.
        }
    }

    public void register(Credential credential, String name, String type/*RegisteredUser registeredUser*/){

//        String type=registeredUser.getClass().toString().split(" ")[1]; //toString returns "class <ClassName>". Split it by the space and extracted the <ClassName>.

        switch (type){

            case "EndUser":
                superstore.addEndUser(credential,name);
                break;
            case "StoreAdmin":
                superstore.addStoreAdmin(name,-1,credential);
                break;
            case "WarehouseAdmin":
                superstore.addWarehouseAdmin(name,-1,credential);
                break;
        }
    }

    public void login(Credential credential, String type){

        User user=null;

        switch (type){

            case "EndUser":
                user=superstore.getEndUser(credential);
                break;
            case "StoreAdmin":
                user=superstore.getStoreAdmin(credential);
                break;
            case "WarehouseAdmin":
                user=superstore.getWarehouseAdmin(credential);
                break;
        }

        user.runSession();
    }
}