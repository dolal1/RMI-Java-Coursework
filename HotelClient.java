

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public class HotelClient {

    public static void main(String[] args) throws RemoteException, NotBoundException {

        HotelClientImpl client = new HotelClientImpl();
        if (args.length == 0) {
            System.out.println("Usage: ");
            System.out.println("java Hotelclient list <server address>");
            System.out.println("java Hotelclient book <server address> <room_type(e.g 0)> <guest_name(e.g Tonny_Ray)>");
            System.out.println("java Hotelclient guests <server address>");
            System.out.println("java Hotelclient revenue <server address>");
            return;
        }
        //Makes sure connection is established
        try {
            // connects to the server depending on the specified arguments
        	//if arguments are provided.
            if(args.length>=2)
            client.startClient(args[1]);
            else
            System.out.println("Some arguments are required");
        } catch (Exception e) {
            System.out.println("Can't connect to " + args[1]);
            //returns to close the program when connection fails.
            return;
        }
        // list arguments 
        if (args[0].equals("list") && args.length == 2) {
            System.out.println("List: ");
            HashMap<RoomType, Integer> list = client.list();
            System.out.println(list.get(RoomType.TYPE0) + " rooms of type 0 are available for 55,000 UGX per night");
            System.out.println(list.get(RoomType.TYPE1) + " rooms of type 1 are available for 75,000 UGX per night");
            System.out.println(list.get(RoomType.TYPE2) + " rooms of type 2 are available for 80,000 UGX per night");
            System.out
                    .println(list.get(RoomType.TYPE3) + " rooms of type 3 are available for 150,000 UGX per night");
            System.out
                    .println(list.get(RoomType.TYPE4) + " rooms of type 4 are available for 230,000 UGX per night");
        }
        // book rooms
        if (args[0].equals("book") &&  args.length == 4) {
            try {
                int type;
                boolean result;
                try {
                    type = Integer.parseInt(args[2]);
                    String name = args[3];
                    switch (type) {
                    case 0:
                        result = client.book(name, RoomType.TYPE0);
                        break;
                    case 1:
                        result = client.book(name, RoomType.TYPE1);
                        break;
                    case 2:
                        result = client.book(name, RoomType.TYPE2);
                        break;
                    case 3:
                        result = client.book(name, RoomType.TYPE3);
                        break;
                    case 4:
                        result = client.book(name, RoomType.TYPE4);
                        break;
                    default:
                        System.out.println("type must be one of 0,1,2,3,4");
                        result = false;
                        break;
                    }
                    if (result) {
                        System.out.println("Room booked successfully.");
                    }
                } catch (NumberFormatException n) {
                    System.out.println("type must be one of 0,1,2,3,4");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Type and name required.");
            }
        }
        // return guests
        if (args[0].equals("guests") && args.length == 2) {
            ArrayList<String> guests = client.guests();
            System.out.println("Guests: ");
            guests.forEach(guest -> System.out.println(guest));
        }
       // list revenue
        if (args[0].equals("revenue") && args.length ==2) {
            for (String revenue : client.revenue()) {
                System.out.println(revenue);
            }
        }
    }
}
// to handle all the requests from the server(server communications)
class HotelClientImpl {

    private RoomManager server;

    public HotelClientImpl() throws RemoteException {
    }
    // look up to the server
    public void startClient(String address) throws RemoteException, NotBoundException, MalformedURLException {
        server = (RoomManager) Naming.lookup("rmi://" + address + ":1099/hotelservice");
    }

    public ArrayList<String> revenue() {
        ArrayList<String> result;
        try {
            result = server.revenue();
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not contact server");
        }
        return result;
    }

    public boolean book(String name, RoomType type) {
        boolean result;
        try {
            result = server.book(name, type);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not contact server");
        }
        return result;
    }

    public ArrayList<String> guests() {
        ArrayList<String> result;
        try {
            result = server.guests();
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not contact server");
        }
        return result;
    }

    public HashMap<RoomType, Integer> list() {
        HashMap<RoomType, Integer> result;
        try {
            result = server.list();
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not contact server");
        }
        return result;
    }
}