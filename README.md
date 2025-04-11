# Hotel Booking System

A simple Java-based hotel booking management system that allows users to manage rooms, guests, and bookings through a console interface.

## Features

- **Room Management**: View all rooms and check availability
- **Guest Registration**: Register new guests with contact information
- **Booking Operations**: Create, view, and cancel room bookings
- **Date-based Reservations**: Book rooms for specific date ranges with validation
- **Multiple Room Types**: Support for Standard, Deluxe, and Suite room categories
- **Price Calculation**: Automatic calculation of total booking price based on stay duration

## Project Structure

```
hotel-booking-system/
├── src/
│   ├── model/
│   │   ├── Room.java
│   │   ├── Guest.java
│   │   └── Booking.java
│   ├── service/
│   │   └── HotelService.java
│   └── HotelBookingSystem.java
└── README.md
```

## Classes Overview

### Models
- **Room**: Represents a hotel room with number, type, price, and availability status
- **Guest**: Stores guest information including ID, name, email, and phone
- **Booking**: Manages booking details with links to room and guest, check-in/out dates, and price

### Service
- **HotelService**: Core business logic for room, guest, and booking management

### Main Application
- **HotelBookingSystem**: Console interface for interacting with the system

## Usage

### Running the Application

1. Clone the repository
2. Open in your Java IDE (VS Code, IntelliJ, Eclipse, etc.)
3. Run `HotelBookingSystem.java`

### Sample Workflow

1. Register a new guest (option 3)
2. View available rooms (option 2)
3. Create a new booking (option 4)
4. View booking details (option 5)
5. Cancel booking if needed (option 6)

## Sample Commands

Register a guest:
```
Enter guest name: John Doe
Enter guest email: john@example.com
Enter guest phone: 555-123-4567
```

Create a booking:
```
Enter guest ID: [ID from registration]
Enter room number to book: 101
Enter check-in date (yyyy-MM-dd): 2025-05-01
Enter check-out date (yyyy-MM-dd): 2025-05-05
```

## Development

### Requirements
- Java Development Kit (JDK) 8 or higher
- Any Java IDE (VS Code recommended)

### Future Enhancements
- Database integration for persistent storage
- Graphical user interface
- Room availability calendar
- Payment processing
- User authentication and roles
- Reporting capabilities

## License

This project is available under the MIT License. Feel free to use, modify, and distribute as needed.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request
