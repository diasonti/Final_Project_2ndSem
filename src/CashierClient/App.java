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
    
    static Frame frame;
    
    public static void main(String[] args){
        final String IP = "127.0.0.1";
        final int PORT = 2009;
        try {
            frame = new Frame();
            /* //TODO Remove comment after gui completion
            socket = new Socket(IP, PORT);
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            int login = send(new Packet("login","cashier"));
            connected = login == 0;
            */
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    // Send packet and wait for response
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
    
    static Flight[] findFlights(City from, City to, boolean business, int passengers){
        return new Flight[]{new Flight(new Aircraft("Airbus", "A231", 50, 150),
                new City("Astana", "Kazakhstan", "UACC"),
                new City("London Heathrow", "United Kingdom", "EGLL"),
                5000, 1000, 3000, "2.12.1998"),
                new Flight(new Aircraft("Airbus", "A231", 50, 150),
                        new City("Astana", "Kazakhstan", "UACC"),
                        new City("London Heathrow", "United Kingdom", "EGLL"),
                        5000, 1000, 3000, "2.12.1998"),
                new Flight(new Aircraft("Airbus", "A231", 50, 150),
                        new City("Astana", "Kazakhstan", "UACC"),
                        new City("London Heathrow", "United Kingdom", "EGLL"),
                        5000, 1000, 3000, "2.12.1998")}; //TODO Find flights
    }

    static int buyTickets(Flight flight, boolean business, int amount){
        return 0; // TODO Book tickets
    }
    
    static Flight[] getAllFlights(){
        return new Flight[]{new Flight(new Aircraft("Airbus", "A231", 50, 150),
                new City("Astana", "Kazakhstan", "UACC"),
                new City("London Heathrow", "United Kingdom", "EGLL"),
                5000, 1000, 3000, "2.12.1998"),
                new Flight(new Aircraft("Airbus", "A231", 50, 150),
                        new City("Astana", "Kazakhstan", "UACC"),
                        new City("London Heathrow", "United Kingdom", "EGLL"),
                        5000, 1000, 3000, "2.12.1998"),
                new Flight(new Aircraft("Airbus", "A231", 50, 150),
                        new City("Astana", "Kazakhstan", "UACC"),
                        new City("London Heathrow", "United Kingdom", "EGLL"),
                        5000, 1000, 3000, "2.12.1998")}; //TODO Get all flights
    }
    
}
