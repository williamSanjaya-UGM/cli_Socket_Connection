package socketing.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HttpConnectionWorkerThread extends Thread{
    private static final Logger logger = LoggerFactory.getLogger(HttpConnectionWorkerThread.class);

    private Socket socket;

    public HttpConnectionWorkerThread(Socket socket){
        this.socket=socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String text = br.readLine();
            logger.info("from client: "+text);

//            PrintWriter pw = new PrintWriter(socket.getOutputStream());
//            pw.println(text);
//            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
