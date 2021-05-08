package socketing2;

import java.io.*;
import java.net.*;

// Requester is the client
public class Requester{

    Socket requestSocket;
    ObjectOutputStream out;
    ObjectInputStream in;
    String message;

    Requester(){}

    void run() {
        try{

            requestSocket = new Socket("localhost", 2004);
            System.out.println("Connected to localhost in port 2004");

            out = new ObjectOutputStream(requestSocket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(requestSocket.getInputStream());


            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (br.readLine()!=null){
                sendMessage(br.readLine());
            }
        } catch(UnknownHostException unknownHost){
            System.err.println("You are trying to connect to an unknown host!");
        } catch(IOException ioException){
            ioException.printStackTrace();
        } finally{
            try{
                in.close();
                out.close();
                requestSocket.close();
            } catch(IOException ioException){
                ioException.printStackTrace();
            }
        }
    }

    void sendMessage(String msg) {
        try{
            out.writeObject(msg);
            out.flush();
            System.out.println("client>" + msg);
        } catch(IOException ioException){
            ioException.printStackTrace();
        }
    }

    public static void main(String args[]) {
        Requester client = new Requester();
        client.run();
    }
}
