import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

public class RoomManagerImpl implements RoomManager {
    HashMap<String, Room> rooms = new HashMap<>();

    public RoomManagerImpl() throws RemoteException {
        rooms.put("type0", new Room(10));
        rooms.put("type1", new Room(20));
        rooms.put("type2", new Room(5));
        rooms.put("type3", new Room(3));
        rooms.put("type4", new Room(2));
        UnicastRemoteObject.exportObject(this, 0);
    }

    // returns a list of guests from all rooms.
    @Override
    public ArrayList<String> guests() throws RemoteException {
        ArrayList<String> guestsList = new ArrayList<>();
        for (String key : rooms.keySet()) {
            // get all guests from each room type using the specified key.
            guestsList.addAll(this.rooms.get(key).getGuests());
        }
        return guestsList;
    }

    @Override
    public ArrayList<String> revenue() throws RemoteException {

        ArrayList<String> revenueList = new ArrayList<>();
        for (String key : rooms.keySet()) {
            if (key.equals("type0")) {

                revenueList.add(this.rooms.get(key).size() + " booked rooms of type 0 collected "
                        + (55000 * this.rooms.get(key).size()) + " UGX.");
            }
            if (key.equals("type1")) {
                ;
                revenueList.add(this.rooms.get(key).size() + " booked rooms of type 1 collected "
                        + (55000 * 75000 * this.rooms.get(key).size()) + " UGX.");
            }
            if (key.equals("type2")) {

                revenueList.add(this.rooms.get(key).size() + " booked rooms of type 2 collected "
                        + (80000 * this.rooms.get(key).size()) + " UGX.");
            }
            if (key.equals("type3")) {

                revenueList.add(this.rooms.get(key).size() + " booked rooms of type 3 collected "
                        + (150000 * this.rooms.get(key).size()) + " UGX.");
            }
            if (key.equals("type4")) {

                revenueList.add(this.rooms.get(key).size() + " booked rooms of type 4 collected "
                        + (230000 * this.rooms.get(key).size()) + " UGX.");
            }
        }
        return revenueList;
    }

    @Override
    public boolean book(String name, RoomType type) throws RemoteException {
        switch (type) {
            case TYPE0:
                return rooms.get("type0").addGuest(name);
            case TYPE1:
                return rooms.get("type1").addGuest(name);
            case TYPE2:
                return rooms.get("type2").addGuest(name);
            case TYPE3:
                return rooms.get("type3").addGuest(name);
            case TYPE4:
                return rooms.get("type4").addGuest(name);
            default:
                return false;
        }
    }

    // returns the number of all available rooms in a hash map
    @Override
    public HashMap<RoomType, Integer> list() throws RemoteException {
        HashMap<RoomType, Integer> lists = new HashMap<>();
        for (String key : rooms.keySet()) {
            if (key.equals("type0")) {
                lists.put(RoomType.TYPE0, (this.rooms.get(key).getTotal() - this.rooms.get(key).size()));
            }
            if (key.equals("type1")) {
                lists.put(RoomType.TYPE1, (this.rooms.get(key).getTotal() - this.rooms.get(key).size()));
            }
            if (key.equals("type2")) {
                lists.put(RoomType.TYPE2, (this.rooms.get(key).getTotal() - this.rooms.get(key).size()));
            }
            if (key.equals("type3")) {
                lists.put(RoomType.TYPE3, (this.rooms.get(key).getTotal() - this.rooms.get(key).size()));
            }
            if (key.equals("type4")) {
                lists.put(RoomType.TYPE4, (this.rooms.get(key).getTotal() - this.rooms.get(key).size()));
            }
        }
        return lists;
    }
}
