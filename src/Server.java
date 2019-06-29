// A Java program for a Server

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    //initialize socket and input stream
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;
    private int port;
    private Song obj;

    // constructor with port
    public Server(int port) {
        this.port = port;

    }
    public void getSong()
    {
        // starts server and waits for a connection
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");
            socket = server.accept();
            System.out.println("Client accepted");
            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));
            Song obj = null;
            ObjectInputStream objectInputStream = new ObjectInputStream(in);
            try {
                obj = (Song) objectInputStream.readObject();
                System.out.println("received:\n" + obj.getTitle());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("Closing connection");



            // close connection
            socket.close();
            in.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public Song getObj() {
        return obj;
    }
    //    public static void main(String args[]) {
//        Server server = new Server(5000);
//    }
}

