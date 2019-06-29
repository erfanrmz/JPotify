import java.net.BindException;

public class ServerThread extends Thread {
    private Server server;
    public ServerThread (int port)
    {
        server = new Server(port);
    }
    @Override
    public void run() {
            try {
                server.getSong();
        } catch (Throwable t) { // <--- here, Throwable
                System.out.println("vay");
        }


        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    }

