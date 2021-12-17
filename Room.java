import java.util.ArrayList;

public class Room {
	//lists of gues for the room type
    private ArrayList<String> guests;
    //total number of rooms
    private int total;

    //sets the total number of rooms
    //creates a new array for the guests.
    public Room(int total) {
        setTotal(total);
        guests = new ArrayList<String>();
    }

    public int getTotal() {
        return total;
    }

    public ArrayList<String> getGuests() {
        return guests;
    }

    public boolean addGuest(String guest) {
        if (this.guests.size() < total) {
            this.guests.add(guest);
            return true;
        }
        return false;
    }

    //returns the number of guests in the room
    public int size() {
        return guests.size();
    }

    public void setTotal(int total) {
        this.total = total;
    }

}

///Server
//Room
//RoomType
//RoomManager(Tonny)
//RoomManagerImpl(T)

//Client - (Olal)
//HotelClientImpl inside the HotelClient
//HotelClient
