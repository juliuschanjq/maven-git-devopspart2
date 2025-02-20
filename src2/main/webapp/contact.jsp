<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contact Us</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <header>
        <h1>Contact Us</h1>
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
            <h2>We'd Love to Hear From You</h2>
            <p>If you have any questions or need assistance, please fill out the form below:</p>
            <form action="ContactServlet" method="post">
                <label for="name">Your Name:</label><br>
                <input type="text" id="name" name="name" required><br><br>

                <label for="email">Your Email:</label><br>
                <input type="email" id="email" name="email" required><br><br>

                <label for="subject">Subject:</label><br>
                <input type="text" id="subject" name="subject" required><br><br>

                <label for="message">Message:</label><br>
                <textarea id="message" name="message" rows="5" required></textarea><br><br>

                <button type="submit">Send Message</button>
            </form>
        </section>
    </main>
    <footer>
        <p>&copy; 2024 Hotel Booking System. All Rights Reserved.</p>
    </footer>
</body>
</html>
