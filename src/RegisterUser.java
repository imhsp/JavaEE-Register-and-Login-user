

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
 * Servlet implementation class RegisterUser
 */
@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public RegisterUser() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/html");  
        PrintWriter pw = response.getWriter(); 
        //String connectionURL = "jdbc:mysql://127.0.0.1:3306/newData";// newData is the database  
        //Connection connection;  
        Connection conn=null;
        String url="jdbc:mysql://localhost:3306/";
        String dbName="users";
        String driver="com.mysql.jdbc.Driver";
    //String dbUserName="root";
    //String dbPassword="root";

    try{  
      String Fname = request.getParameter("firstname");  
      String Lname = request.getParameter("lastname");  
      String Email = request.getParameter("email");  
      String Password = request.getParameter("psw");  

      Class.forName(driver).newInstance();  
      conn = DriverManager.getConnection(url+dbName,"root", "");
      PreparedStatement pst =(PreparedStatement) conn.prepareStatement("insert into users.register(firstname,lastname,email,psd) values(?,?,?,?)");//try2 is the name of the table  

      pst.setString(1,Fname);  
      pst.setString(2,Lname);
      pst.setString(3,Email);
      pst.setString(4,Password);
      

      int i = pst.executeUpdate();
      conn.commit(); 
      String msg=" ";
      if(i!=0){  
        msg="Record has been inserted";
        pw.println("<font size='6' color=blue>" + msg + "</font>");  


      }  
      else{  
        msg="failed to insert the data";
        pw.println("<font size='6' color=blue>" + msg + "</font>");
       }  
      pst.close();
    }  
    catch (Exception e){  
      pw.println(e);  
    }  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
