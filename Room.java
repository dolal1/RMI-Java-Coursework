import java.util.ArrayList;

public class Room {
    // Lists of guests for the room type
    private ArrayList<String> guests;
    // Total number of rooms
    private int total;

    // Sets the total number of rooms
    // Creates a new array for the guests.
    public Room(int total) {
        setTotal(total);
        guests = new ArrayList<String>();
    }

    // Returns Total Numbert of Rooms
    public int getTotal() {
        return total;
    }

    // Returns List of Guests
    public ArrayList<String> getGuests() {
        return guests;
    }

    // Adds Guest to guest list
    public boolean addGuest(String guest) {
        if (this.guests.size() < total) {
            this.guests.add(guest);
            return true;
        }
        return false;
    }

    // Returns the number of guests in the room
    public int size() {
        return guests.size();
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
