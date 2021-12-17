import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

// Room interface
public interface RoomManager extends Remote {

    // books a room of the specified type (if available), and registers the name of
    // the guest.
    boolean book(String name, RoomType type) throws RemoteException;

    // list the names of all the registered guests
    ArrayList<String> guests() throws RemoteException;

    // prints the revenue breakdown based on the booked rooms and their types.
    ArrayList<String> revenue() throws RemoteException;

    HashMap<RoomType, Integer> list() throws RemoteException;
}
