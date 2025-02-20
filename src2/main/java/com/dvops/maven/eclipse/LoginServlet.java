package com.dvops.maven.eclipse;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public LoginServlet() {
        super();
    }

    /**
     * Handles GET requests.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * Handles POST requests for login.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html");

        // Create PrintWriter for response
        PrintWriter writer = response.getWriter();

        // Retrieve username and password from request
        String username = request.getParameter("uname");
        String password = request.getParameter("pass");

        // Validate credentials
        if (username != null && password != null && username.equals("julius123") && password.equals("456")) {
            writer.println("<h1>You have logged in successfully!</h1>");
        } else {
            writer.println("<h1>You have keyed in the wrong credentials!</h1>");
        }

        // Close writer
        writer.close();
    }
}
