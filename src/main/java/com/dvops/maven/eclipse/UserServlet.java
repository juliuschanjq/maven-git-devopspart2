package com.dvops.maven.eclipse;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class UserServlet 
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Step 1: Prepare list of variables used for database connections
    private String jdbcURL = "jdbc:mysql://localhost:3307/userdetails";
    private String jdbcUsername = "root";
    private String jdbcPassword = "password";

    private static final String INSERT_USERS_SQL = "INSERT INTO UserDetails (name, password, email, language) VALUES (?, ?, ?, ?);";
    private static final String SELECT_USER_BY_ID = "SELECT name, password, email, language FROM UserDetails WHERE name = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM UserDetails";
    private static final String DELETE_USERS_SQL = "DELETE FROM UserDetails WHERE name = ?;";
    private static final String UPDATE_USERS_SQL = "UPDATE UserDetails SET name = ?, password = ?, email = ?, language = ? WHERE name = ?;";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/UserServlet/delete":
                    deleteUser(request, response);
                    break;
                case "/UserServlet/edit":
                    showEditForm(request, response);
                    break;
                case "/UserServlet/update":
                    updateUser(request, response);
                    break;
                case "/UserServlet/dashboard":
                    listUsers(request, response);
                    break;
                case "/UserServlet/insert":
                    addNewUser(request, response);
                    break;
                default:
                    response.getWriter().write("Unknown action: " + action);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    // Step 3: Implement the getConnection method which facilitates connection to the database via JDBC
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String language = rs.getString("language");
                users.add(new User(name, password, email, language));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        request.setAttribute("listUsers", users);
        request.getRequestDispatcher("/userManagement.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String name = request.getParameter("name");
        User existingUser = new User("", "", "", "");
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                name = rs.getString("name");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String language = rs.getString("language");
                existingUser = new User(name, password, email, language);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        request.setAttribute("user", existingUser);
        request.getRequestDispatcher("/userEdit.jsp").forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String oriName = request.getParameter("oriName");
        String name = request.getParameter("userName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String language = request.getParameter("language");

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL)) {
            statement.setString(1, name);
            statement.setString(2, password);
            statement.setString(3, email);
            statement.setString(4, language);
            statement.setString(5, oriName);
            statement.executeUpdate();
        }

        response.sendRedirect("http://localhost:8090/HelloWorldJava/UserServlet/dashboard");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL)) {
            statement.setString(1, name);
            statement.executeUpdate();
        }

        response.sendRedirect("http://localhost:8090/HelloWorldJava/UserServlet/dashboard");
    }

    private void addNewUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/RegisterServlet");
        rd.include(request, response);
    }
}
