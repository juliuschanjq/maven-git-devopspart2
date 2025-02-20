package com.dvops.maven.eclipse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve form data
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String checkInDate = request.getParameter("checkInDate");
        String checkOutDate = request.getParameter("checkOutDate");
        String roomType = request.getParameter("roomType");
        String numGuestsStr = request.getParameter("numGuests");
        
        // Validate input (optional, but recommended for security and completeness)
        if (name == null || email == null || phone == null || checkInDate == null || checkOutDate == null || roomType == null || numGuestsStr == null || 
            name.isEmpty() || email.isEmpty() || phone.isEmpty() || checkInDate.isEmpty() || checkOutDate.isEmpty() || roomType.isEmpty() || numGuestsStr.isEmpty()) {
            out.println("<h3>Error: All fields are required!</h3>");
            return;
        }

        // Parse the number of guests
        int numGuests = 0;
        try {
            numGuests = Integer.parseInt(numGuestsStr);
        } catch (NumberFormatException e) {
            out.println("<h3>Error: Invalid number of guests!</h3>");
            return;
        }

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection (update the database name and credentials as needed)
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/userdetails", "root", "password");

            // SQL query to insert booking details
            String query = "INSERT INTO bookings (name, email, phone, checkinDate, checkoutDate, roomType, guestsNum) VALUES (?, ?, ?, ?, ?, ?, ?)";

            // Prepare and set query parameters
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, phone);
            pstmt.setString(4, checkInDate);
            pstmt.setString(5, checkOutDate);
            pstmt.setString(6, roomType);
            pstmt.setInt(7, numGuests);

            // Execute the query and check rows affected
            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                out.println("<h3>Your booking has been successfully made!</h3>");
            } else {
                out.println("<h3>Sorry, something went wrong. Please try again.</h3>");
            }

        } catch (Exception e) {
            // Print error details to the response for debugging
            out.println("<h3>Error occurred while processing your booking:</h3>");
            e.printStackTrace(out);
        } finally {
            try {
                // Close resources to prevent memory leaks
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace(out);
            }
        }
    }
}
