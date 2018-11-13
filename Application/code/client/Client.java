package client;

import classes.Credential;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client extends Application {

    private static Stage primaryStage;
    private ExecutorService executorService;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage=primaryStage;
        executorService= Executors.newFixedThreadPool(4);
        Parent root = FXMLLoader.load(getClass().getResource("/layout/fxml/login.fxml"));
        this.primaryStage.setTitle("Login");
        this.primaryStage.setScene(new Scene(root, 800, 500));
        this.primaryStage.setResizable(false);
        this.primaryStage.show();
    }

    public void addSession(Credential credential){

//        Runnable session
    }

    private class Session extends Application implements Runnable{

        String serverName="localhost";
        int serverPort=1234;
        Socket server;
        DataInputStream inputStream;
        DataOutputStream outputStream;

        @Override
        public void run() {
            try {
                server=new Socket(serverName,serverPort);
            } catch (IOException e) {
                System.err.println("Cannot connect to the server.");
            }

            //TODO server may be null

            try{
                inputStream=new DataInputStream(server.getInputStream());
                outputStream=new DataOutputStream(server.getOutputStream());
            }

            catch (IOException ioe){
                System.err.println("Cannot load server streams.");
            }

            finally {

                try{
                    inputStream.close();
                    outputStream.close();
                }

                catch (IOException ioe){

                    System.err.println("Cannot close server streams.");
                }

            }

        }

        @Override
        public void start(Stage primaryStage) throws Exception {

        }
    }
}
