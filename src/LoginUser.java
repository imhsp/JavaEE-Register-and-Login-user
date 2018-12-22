

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginUser
 */
@WebServlet("/LoginUser")
public class LoginUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginUser() {
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		PrintWriter out = response.getWriter();
        String connectionURL = "jdbc:mysql://localhost/dblogin";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String email = request.getParameter("email");
        String password = request.getParameter("psw");
        response.setContentType("text/html");

        try {
            // Load the database driver
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(connectionURL, "root", "");
            //Add the data into the database
            String sql = "SELECT * FROM login WHERE userid = ? AND password = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            rs = preparedStatement.getResultSet();

            if(rs.next()) {
                // redirect or print but not both...
                out.println("The user is valid");
                // response.sendRedirect("index_true.jsp");
            } else {
                out.println("You are not valid");
            }
        } catch(Exception e) {
            System.out.println("Exception is: " + e);
        } finally {
           
            try {
				rs.close();
				preparedStatement.close();
	            connection.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
            
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
