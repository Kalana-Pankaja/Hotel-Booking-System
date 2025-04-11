package service;

import model.Booking;
import model.Guest;
import model.Room;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class HotelService {
    private List<Room> rooms;
    private Map<String, Guest> guests;
    private Map<String, Booking> bookings;

    public HotelService() {
        this.rooms = new ArrayList<>();
        this.guests = new HashMap<>();
        this.bookings = new HashMap<>();
        
        // Initialize with some rooms
        initializeRooms();
    }

    private void initializeRooms() {
        // Add some standard rooms
        for (int i = 101; i <= 110; i++) {
            rooms.add(new Room(i, "Standard", 99.99));
        }
        
        // Add some deluxe rooms
        for (int i = 201; i <= 205; i++) {
            rooms.add(new Room(i, "Deluxe", 149.99));
        }
        
        // Add some suites
        for (int i = 301; i <= 303; i++) {
            rooms.add(new Room(i, "Suite", 299.99));
        }
    }

    public List<Room> getAllRooms() {
        return new ArrayList<>(rooms);
    }

    public List<Room> getAvailableRooms() {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public Room getRoomByNumber(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }

    public Guest registerGuest(String name, String email, String phone) {
        String guestId = UUID.randomUUID().toString().substring(0, 8);
        Guest guest = new Guest(guestId, name, email, phone);
        guests.put(guestId, guest);
        return guest;
    }

    public Guest getGuestById(String guestId) {
        return guests.get(guestId);
    }

    public Booking createBooking(Room room, Guest guest, LocalDate checkInDate, LocalDate checkOutDate) {
        if (!room.isAvailable()) {
            return null; // Room is not available
        }
        
        String bookingId = "BK" + UUID.randomUUID().toString().substring(0, 6);
        Booking booking = new Booking(bookingId, room, guest, checkInDate, checkOutDate);
        bookings.put(bookingId, booking);
        
        // Mark room as unavailable
        room.setAvailable(false);
        
        return booking;
    }

    public boolean cancelBooking(String bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking != null) {
            // Mark room as available again
            booking.getRoom().setAvailable(true);
            bookings.remove(bookingId);
            return true;
        }
        return false;
    }

    public Booking getBookingById(String bookingId) {
        return bookings.get(bookingId);
    }

    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookings.values());
    }
}