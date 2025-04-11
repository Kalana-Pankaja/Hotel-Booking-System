package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Booking {
    private String bookingId;
    private Room room;
    private Guest guest;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private double totalPrice;

    public Booking(String bookingId, Room room, Guest guest, LocalDate checkInDate, LocalDate checkOutDate) {
        this.bookingId = bookingId;
        this.room = room;
        this.guest = guest;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        
        // Calculate total price based on the number of nights
        long numberOfNights = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        this.totalPrice = numberOfNights * room.getPricePerNight();
    }

    public String getBookingId() {
        return bookingId;
    }

    public Room getRoom() {
        return room;
    }

    public Guest getGuest() {
        return guest;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId + "\n" +
               "Guest: " + guest.getName() + "\n" +
               "Room: " + room.getRoomNumber() + " (" + room.getType() + ")\n" +
               "Check-in: " + checkInDate + "\n" +
               "Check-out: " + checkOutDate + "\n" +
               "Total Price: $" + totalPrice;
    }
}
