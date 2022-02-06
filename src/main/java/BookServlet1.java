

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class BookServlet1
 */
@WebServlet("/BookServlet1")
public class BookServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String jdbcURL = "jdbc:mysql://localhost:3306/bookdb";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";
	
	//private static final String INSERT_BOOK_SQL = "INSERT INTO bookdetails" + " (bookName,	bookDesc, bookAuthor, bookLikes) VALUES (?, ?, ?,?)";
	private static final String SELECT_BOOK_BY_ID = "select bookName,	bookDesc, bookAuthor, bookLikes from bookdetails where bookName =?;";
	private static final String SELECT_ALL_BOOKS = "select * from bookdetails ;";
	private static final String DELETE_BOOK_SQL = "delete from bookdetails where bookName = ?;";
	private static final String UPDATE_BOOK_SQL = "update bookdetails set bookName = ?,bookAuthor=?,bookDesc=?, bookLikes=? where bookName=?;";
	
	
	
	protected Connection getConnection() {
		 Connection connection = null;
		 try {
			 	Class.forName("com.mysql.jdbc.Driver"); 
			 	   connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookdb", "root", "password"); 
			 	   System.out.println(connection);
		 } catch (SQLException e) {
			 	e.printStackTrace();
		 } catch (ClassNotFoundException e) {
			 	e.printStackTrace();
		 }
		 	return connection;
	}
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookServlet1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		String action = request.getServletPath();
		 try {
			 	switch (action) {
			 			case "/BookServlet1/delete":
			 					deleteBook(request,response);
			 			break;
			 			case "/BookServlet1/edit":
			 				showEditForm(request,response);
			 			break;
			 			case "/BookServlet1/update":
			 				updateBook(request,response);
			 			break;
			 			case "/BookServlet1/dashboard":
			 				listBooks(request,response);
			 			break;
			 	}
		 } catch (SQLException ex) {
			 	throw new ServletException(ex);
		 } 
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
			//get parameter passed in the URL
					String bookName = request.getParameter("bookName");
					Book existingBook = new Book("", "", "", 1);
					// Step 1: Establishing a Connection
			try (Connection connection = getConnection();
						// Step 2:Create a statement using connection object
						PreparedStatement preparedStatement = 
						connection.prepareStatement(SELECT_BOOK_BY_ID);) {
						preparedStatement.setString(1, bookName);
						// Step 3: Execute the query or update query
						ResultSet rs = preparedStatement.executeQuery();
						// Step 4: Process the ResultSet object 
						while (rs.next()) {
							bookName = rs.getString("bookName");
//								 String bookName = rs.getString("bookName");
								 System.out.print(bookName);
								 String bookDesc= rs.getString("bookDesc");
								 String bookAuthor = rs.getString("bookAuthor");
								 int bookLikes = rs.getInt("bookLikes");
								existingBook = new Book(bookName, bookDesc, bookAuthor, bookLikes);
						}
			} catch (SQLException e) {
			System.out.println(e.getMessage());
			}
			//Step 5: Set existingUser to request and serve up the userEdit form
			request.setAttribute("book", existingBook);
			request.getRequestDispatcher("/bookEdit.jsp").forward(request, response);
			}


	
	//Method 2: Edit Book  	//method to update the book table base on the form data
	private void updateBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
	//Step 1: Retrieve value from the request
			 String oriName = request.getParameter("oriName");
			 String bookName = request.getParameter("bookName");
			 String bookDesc = request.getParameter("bookDesc");
			 String bookAuthor = request.getParameter("bookAuthor");
			 int bookLikes = 1;
			 
			  
	 //Step 2: Attempt connection with database and execute update user SQL query
	 try (Connection connection = getConnection(); PreparedStatement statement = 
	connection.prepareStatement(UPDATE_BOOK_SQL);) {
				 statement.setString(1, bookName);
				 statement.setString(2, bookDesc);
				 statement.setString(3, bookAuthor);
				 statement.setInt(4, bookLikes);
				 statement.setString(5, oriName);

				 int i = statement.executeUpdate();
				 System.out.println(i);
	             
	             
	 }
	 //Step 3: redirect back to BookServlet1 (note: remember to change the url to your project name)
	 response.sendRedirect("http://localhost:8090/GroupProject/BookServlet1/dashboard");
	}
	
	
	
	//Method 3: deleteBook
	//method to delete Book
	private void deleteBook(HttpServletRequest request, HttpServletResponse response) 	throws SQLException, IOException {
	//Step 1: Retrieve value from the request
	 String bookName = request.getParameter("bookName");
	 //Step 2: Attempt connection with database and execute delete user SQL query
	 try (Connection connection = getConnection(); PreparedStatement statement = 
	connection.prepareStatement(DELETE_BOOK_SQL);) {
	 statement.setString(1, bookName);
	 int i = statement.executeUpdate();
	 }
	 //Step 3: redirect back to UserServlet dashboard (note: remember to change the url toyour project name)
	 response.sendRedirect("http://localhost:8090/GroupProject/BookServlet1/dashboard");
	}


	
	private void listBooks(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException 
			{
					List <Book> bookList= new ArrayList <>();
					try (Connection connection = getConnection();
						 PreparedStatement preparedStatement =connection.prepareStatement(SELECT_ALL_BOOKS);) {
						 // Step 5.2: Execute the query or update query
						 ResultSet rs = preparedStatement.executeQuery();
						 System.out.println(rs);
						 // Step 5.3: Process the ResultSet object.
						 while (rs.next()) {
								 String bookName = rs.getString("bookName");
								 System.out.print(bookName);
								 String bookDesc= rs.getString("bookDesc");
								 String bookAuthor = rs.getString("bookAuthor");
								 int bookLikes = rs.getInt("bookLikes");
								bookList.add(new Book(bookName, bookDesc, bookAuthor, bookLikes));
			 }	
			 } catch (SQLException e) {
			 System.out.println(e.getMessage());
			 }
					// Step 5.4: Set the users list into the listUsers attribute to be pass to theuserManagement.jsp
					request.setAttribute("listBooks",bookList);
					request.getRequestDispatcher("/bookManagement.jsp").forward(request, response);
			}

	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
