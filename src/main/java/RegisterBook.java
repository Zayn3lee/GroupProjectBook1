import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Servlet implementation class BookServlet1
 */
@WebServlet("/RegisterBook")
public class RegisterBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//Get(request, response);
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String bN=request.getParameter("bookName");
 		String bD=request.getParameter("bookDesc");
		String bA=request.getParameter("bookAuthor");
		 
		
		//Step 3: attempt connection to database using JDBC, you can change the username and password accordingly using the phpMyAdmin > User Account dashboard
		try {
		 Class.forName("com.mysql.jdbc.Driver");
		 Connection con = DriverManager.getConnection(
		 "jdbc:mysql://localhost:3306/bookdb", "root", "password");
		//Step 4: implement the sql query using prepared statement 
		
		 PreparedStatement ps = con.prepareStatement("insert into bookdetails values(?,?,?,1)");
		//Step 5: parse in the data retrieved from the web form request into the prepared statement accordingly
		 ps.setString(1, bN);
		 ps.setString(2, bD);
		 ps.setString(3, bA);
		//s.setString(4, bL);
		//Step 6: perform the query on the database using the prepared statement
		 int i = ps.executeUpdate();
		 System.out.println(i);
		//Step 7: check if the query had been successfully execute, return “You are successfully  registered” via the response,
		 if (i > 0){
//		PrintWriter writer = response.getWriter();
//	    writer.println("<h1>" + "You have successfully add a book !" + "</h1>");
//		writer.close(); 
		
		response.sendRedirect("http://localhost:8090/GroupProject/BookServlet1/dashboard");
		} 
		}
		 
		//Step 8: catch and print out any exception
		catch (Exception exception) {
		 System.out.println(exception);
		 out.close();
		}
		}
}	
