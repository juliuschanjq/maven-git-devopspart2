<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hotel Room Booking</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>

    <header>
        <h1>Hotel Room Booking</h1>
        <nav>
            <ul>
                <li><a href="home.jsp">Home</a></li>
                <li><a href="rooms.jsp">Rooms</a></li>
                <li><a href="bookings.jsp">Bookings</a></li>
                <li><a href="contact.jsp">Contact Us</a></li>
            </ul>
        </nav>
    </header>

    <main>
        <section class="contact-form">
            <h2>Book Your Stay</h2>
            <p>Please fill out the form below to reserve your room:</p>
            <form action="BookingServlet" method="POST">
                
                <!-- Guest Information Section -->
                <fieldset>
                    <legend>Guest Information</legend>
                    <label for="name">Name:</label><br>
                    <input type="text" id="name" name="name" required><br><br>

                    <label for="email">Email:</label><br>
                    <input type="email" id="email" name="email" required><br><br>

                    <label for="phone">Phone Number:</label><br>
                    <input type="tel" id="phone" name="phone" required><br><br>
                </fieldset>
                
                <!-- Booking Details Section -->
                <fieldset>
                    <legend>Booking Details</legend>
                    <label for="checkInDate">Check-in Date:</label><br>
                    <input type="date" id="checkInDate" name="checkInDate" required><br><br>

                    <label for="checkOutDate">Check-out Date:</label><br>
                    <input type="date" id="checkOutDate" name="checkOutDate" required><br><br>

                    <label for="roomType">Room Type:</label><br>
                    <select id="roomType" name="roomType" required>
                        <option value="single">Premier Room</option>
                        <option value="double">Premier Studio</option>
                        <option value="suite">Premier Suite</option>
                    </select><br><br>

                    <label for="numGuests">Number of Guests:</label><br>
                    <input type="number" id="numGuests" name="numGuests" min="1" required><br><br>
                </fieldset>


                <button type="submit">Book Room</button>
            </form>
        </section>
        </main>

    <footer>
        <p>&copy; 2024 Hotel Booking System. All Rights Reserved.</p>
    </footer>

</body>
</html>
