package CashierClient;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import common.*;

/**
 * Created by Vladimir on 05.05.2017.
 */
class App {
    
    private static Socket socket;
    private static ObjectInputStream in;
    private static ObjectOutputStream out;
    static boolean connected = false;
    
    public static void main(String[] args){
        final String IP = "127.0.0.1";
        final int PORT = 2009;
        try {
            Frame frame = new Frame();
            socket = new Socket(IP, PORT);
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            int login = send(new Packet("login","cashier"));
            connected = login == 0;
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    static int send(Packet packet){
        if(packet == null || !connected)
            return -1;
        try{
            out.writeObject(packet);
            packet = (Packet)in.readObject();
            if(packet.getHeader().equals("Response"))
                return (int)packet.getContent()[0];
        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }
        return -1;
    }

}
