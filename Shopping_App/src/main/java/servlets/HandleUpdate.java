package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.User;

/**
 * Servlet implementation class HandleUpdate
 */
@WebServlet("/handleUpdate")
public class HandleUpdate extends HttpServlet {
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
		String email=request.getParameter("email");
		String mono= request.getParameter("mono");
//		String uid=request.getParameter("uid");
		
//		System.out.println(email+" "+mono +" "+uid);
		HttpSession session = request.getSession();
		User u=(User)session.getAttribute("user");
		u.setEmail(email);
		u.setMono(mono);
		PreparedStatement stmt= null;
		
		try {
			stmt= con.prepareStatement("update users set email=?, contact=? where u_id=?");
			stmt.setString(1,email);
			stmt.setString(2, mono);
			stmt.setString(3, u.getUid());
			int n= stmt.executeUpdate();
			
			response.sendRedirect("home");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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