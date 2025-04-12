import model.Booking;
import model.Guest;
import model.Room;
import service.HotelService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class HotelBookingSystem {
    private static HotelService hotelService = new HotelService();
    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        boolean running = true;
        
        System.out.println("Welcome to the Hotel Booking System!");
        
        while (running) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    viewAllRooms();
                    break;
                case 2:
                    viewAvailableRooms();
                    break;
                case 3:
                    registerNewGuest();
                    break;
                case 4:
                    createNewBooking();
                    break;
                case 5:
                    viewBookingDetails();
                    break;
                case 6:
                    cancelExistingBooking();
                    break;
                case 0:
                    System.out.println("Thank you for using the Hotel Booking System. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
        scanner.close();
    }

    private static void displayMainMenu() {
        System.out.println("\n===== HOTEL BOOKING SYSTEM =====");
        System.out.println("1. View All Rooms");
        System.out.println("2. View Available Rooms in Hotel");
        System.out.println("3. Register New Guest");
        System.out.println("4. Create New Booking");
        System.out.println("5. View Booking Details");
        System.out.println("6. Cancel Booking");
        System.out.println("0. Exit");
        System.out.println("================================");
    }

    private static void viewAllRooms() {
        System.out.println("\n===== ALL ROOMS =====");
        List<Room> rooms = hotelService.getAllRooms();
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    private static void viewAvailableRooms() {
        System.out.println("\n===== AVAILABLE ROOMS =====");
        List<Room> availableRooms = hotelService.getAvailableRooms();
        if (availableRooms.isEmpty()) {
            System.out.println("No rooms are available at the moment.");
        } else {
            for (Room room : availableRooms) {
                System.out.println(room);
            }
        }
    }

    private static void registerNewGuest() {
        System.out.println("\n===== REGISTER NEW GUEST =====");
        System.out.print("Enter guest name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter guest email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter guest phone: ");
        String phone = scanner.nextLine();
        
        Guest guest = hotelService.registerGuest(name, email, phone);
        System.out.println("\nGuest registered successfully!");
        System.out.println("Guest ID: " + guest.getId() + " (Keep this for bookings)");
    }

    private static void createNewBooking() {
        System.out.println("\n===== CREATE NEW BOOKING =====");
        
        // First, check if any rooms are available
        List<Room> availableRooms = hotelService.getAvailableRooms();
        if (availableRooms.isEmpty()) {
            System.out.println("Sorry, no rooms are available for booking.");
            return;
        }
        
        // Get guest ID
        System.out.print("Enter guest ID: ");
        String guestId = scanner.nextLine();
        Guest guest = hotelService.getGuestById(guestId);
        
        if (guest == null) {
            System.out.println("Guest not found. Please register the guest first.");
            return;
        }
        
        // Display available rooms
        System.out.println("\nAvailable Rooms:");
        for (Room room : availableRooms) {
            System.out.println(room);
        }
        
        // Get room number
        int roomNumber = getIntInput("Enter room number to book: ");
        Room selectedRoom = hotelService.getRoomByNumber(roomNumber);
        
        if (selectedRoom == null || !selectedRoom.isAvailable()) {
            System.out.println("Invalid room number or room is not available.");
            return;
        }
        
        // Get check-in and check-out dates
        LocalDate checkInDate = null;
        LocalDate checkOutDate = null;
        
        try {
            System.out.print("Enter check-in date (yyyy-MM-dd): ");
            String checkInStr = scanner.nextLine();
            checkInDate = LocalDate.parse(checkInStr, dateFormatter);
            
            System.out.print("Enter check-out date (yyyy-MM-dd): ");
            String checkOutStr = scanner.nextLine();
            checkOutDate = LocalDate.parse(checkOutStr, dateFormatter);
            
            // Validate dates
            if (checkInDate.isAfter(checkOutDate)) {
                System.out.println("Check-in date must be before check-out date.");
                return;
            }
            
            if (checkInDate.isBefore(LocalDate.now())) {
                System.out.println("Check-in date cannot be in the past.");
                return;
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd format.");
            return;
        }
        
        // Create booking
        Booking booking = hotelService.createBooking(selectedRoom, guest, checkInDate, checkOutDate);
        
        if (booking != null) {
            System.out.println("\nBooking created successfully!");
            System.out.println(booking);
            System.out.println("\nBooking ID: " + booking.getBookingId() + " (Keep this for reference)");
        } else {
            System.out.println("Failed to create booking. Please try again.");
        }
    }

    private static void viewBookingDetails() {
        System.out.println("\n===== VIEW BOOKING DETAILS =====");
        System.out.print("Enter booking ID: ");
        String bookingId = scanner.nextLine();
        
        Booking booking = hotelService.getBookingById(bookingId);
        
        if (booking != null) {
            System.out.println("\nBooking Details:");
            System.out.println(booking);
        } else {
            System.out.println("Booking not found.");
        }
    }

    private static void cancelExistingBooking() {
        System.out.println("\n===== CANCEL BOOKING =====");
        System.out.print("Enter booking ID to cancel: ");
        String bookingId = scanner.nextLine();
        
        boolean cancelled = hotelService.cancelBooking(bookingId);
        
        if (cancelled) {
            System.out.println("Booking cancelled successfully.");
        } else {
            System.out.println("Booking not found or could not be cancelled.");
        }
    }

    private static int getIntInput(String prompt) {
        int input = 0;
        boolean validInput = false;
        
        while (!validInput) {
            System.out.print(prompt);
            try {
                input = Integer.parseInt(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        
        return input;
    }
}
