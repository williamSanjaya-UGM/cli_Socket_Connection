package socketing;

import socketing.core.ServerListenerThread;

import java.io.IOException;

public class HttpServer {
    public static void main(String[] args) throws IOException {
        ServerListenerThread serverListenerThread=new ServerListenerThread(8080);
        serverListenerThread.start();
    }
}
