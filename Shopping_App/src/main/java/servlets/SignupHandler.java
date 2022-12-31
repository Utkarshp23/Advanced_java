package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignupHandler
 */
@WebServlet("/user/signup")
public class SignupHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {

		con=(Connection) config.getServletContext().getAttribute("dbcon");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid= request.getParameter("uid");
		String fname= request.getParameter("fname");
		String mname= request.getParameter("mname");
		String lname= request.getParameter("lname");
		String email= request.getParameter("email");
		String pwd= request.getParameter("pwd");
		String mono= request.getParameter("mono");
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		PrintWriter out=response.getWriter();
		try {
			stmt=con.prepareStatement("insert into users values(?,?,?,?,?,?,?)");
			
			stmt.setString(1, uid);
			stmt.setString(2,fname);
			stmt.setString(3, mname);
			stmt.setString(4, lname);
			stmt.setString(5, email);
			stmt.setString(6, pwd);
			stmt.setString(7, mono);
			
			
			
			int n=stmt.executeUpdate();
			out.print(n+" records added succesfully");
			
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
//				rs.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
