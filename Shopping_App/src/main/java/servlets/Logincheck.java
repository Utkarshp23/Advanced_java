package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.User;

/**
 * Servlet implementation class Logincheck
 */
@WebServlet("/logincheck")
public class Logincheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		con=(Connection) config.getServletContext().getAttribute("dbcon");
		
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uid=request.getParameter("uid");
		String pwd= request.getParameter("password");
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		
		try {
			stmt=con.prepareStatement("select * from users where u_id=? and password=?");
			stmt.setString(1, uid);
			stmt.setString(2,pwd);
			
			rs=stmt.executeQuery();
			
			if(rs.next())
			{
				
				Cookie []all= request.getCookies();
				if(all!=null)
				{
					for(Cookie c: all)
					{
						if(c.getName().equals("loginerr"))
						{
							c.setMaxAge(0);
							response.addCookie(c);
						}
					}
				}
				HttpSession session= request.getSession();
				
				User u= new User(rs.getString(1),rs.getString(3),rs.getString(5));
				session.setAttribute("user", u);
				
				RequestDispatcher rd= request.getRequestDispatcher("/home");
				rd.forward(request, response);
			}else {
				Cookie c = new Cookie("loginerr","Login_failed");
				response.addCookie(c);
				response.sendRedirect("/Shopping_App/loginform.jsp");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
