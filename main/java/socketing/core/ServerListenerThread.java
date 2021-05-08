package socketing.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListenerThread extends Thread{
    private static Logger logger = LoggerFactory.getLogger(ServerListenerThread.class);

    private ServerSocket serverSocket;
    private int port;

    public ServerListenerThread(int port) throws IOException {
        this.port=port;
        this.serverSocket=new ServerSocket(this.port);
    }

    @Override
    public void run() {
        try{
            while (serverSocket.isBound() && !serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                logger.info("Client is connected on port "+port);

                HttpConnectionWorkerThread connectionWorkerThread =new HttpConnectionWorkerThread(socket);
                connectionWorkerThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(serverSocket !=null) {
                try{
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
