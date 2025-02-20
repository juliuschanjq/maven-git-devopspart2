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

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // Default constructor
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // Step 1: Initialize a PrintWriter object to return the HTML values via the response
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Step 2: Retrieve the four parameters from the request from the web form
        String n = request.getParameter("userName");
        String p = request.getParameter("password");
        String e = request.getParameter("email");
        String c = request.getParameter("language");

        // Step 3: Attempt connection to database using JDBC
        try {
            // Load the database driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection (change the database name, username, and password as needed)
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3307/userdetails", "root", "password");

            // Step 4: Implement the SQL query using a prepared statement
            PreparedStatement ps = con.prepareStatement("INSERT INTO USERDETAILS VALUES(?,?,?,?)");

            // Step 5: Parse in the data retrieved from the web form request into the prepared statement
            ps.setString(1, n);
            ps.setString(2, p);
            ps.setString(3, e);
            ps.setString(4, c);

            // Step 6: Perform the query on the database using the prepared statement
            int i = ps.executeUpdate();

            // Step 7: Check if the query was successfully executed, return success message
            if (i > 0) {
                out.println("<h1>You have successfully registered an account!</h1>");
            }

        } catch (Exception exception) {
            // Step 8: Catch and print out any exception
            exception.printStackTrace(out);
        } finally {
            // Close the PrintWriter
            out.close();
        }
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }
}
