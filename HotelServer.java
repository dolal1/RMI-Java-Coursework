import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class HotelServer {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException {
        // Creates an object of type roomManager that is used to communicate on the
        // client side.
        RoomManager server = new RoomManagerImpl();
        Naming.rebind("rmi://localhost:1099/hotelservice", server);
        System.out.println("Server started");
    }
}
