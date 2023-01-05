package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ViewCart
 */
@WebServlet("/viewcart")
public class ViewCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		con=(Connection) config.getServletContext().getAttribute("dbcon");
	}
	
	public void destroy() {
		// TODO Auto-generated method stub
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<Integer> products= (List<Integer>)session.getAttribute("cart");
		
		String m=request.getParameter("todel");
		
		
		if(m!=null)
		{
			int todel=Integer.parseInt(m);
			products.remove(Integer.valueOf(todel));
		}
		
//		for(int i : products)
//		{
//			System.out.println(i);
//		}
		
		PreparedStatement stmt=null;
		ResultSet rs= null;
		RequestDispatcher rd1=request.getRequestDispatcher("/header");
		RequestDispatcher rd2=request.getRequestDispatcher("/footer");
		
		rd1.include(request, response);
		String str="";
		str+="<table border=1 >";
		str+="<thead>";
		str+="<td>Sr. no</td>";
		str+="<td>Product Name</td>";
		str+="<td>Product Description</td>";
		str+="<td>Price</td>";
		str+="<td>Remove product</td>";
		str+="</thead>";
		str+="<tbody>";
		double sum=0;
		
		
			try {
				stmt=con.prepareStatement("select * from product where p_id=?");
				for(int i=0;i<products.size();i++) {
//				System.out.println(products.get(i));
				stmt.setInt(1,products.get(i));
				rs=stmt.executeQuery();
				rs.next();
				str+="<tr>";
				str+="<td>"+(i+1)+"</td>";
				str+="<td>"+rs.getString(2)+"</td>";
				str+="<td>"+rs.getString(3)+"</td>";
				str+="<td>"+rs.getString(4)+"</td>";
				str+="<td><a href='viewcart?todel="+rs.getInt(1)+"'>delete</a></td>";
				str+="</tr>";
				sum+=Double.parseDouble(rs.getString(4));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		str+="<tr>";
		str+="<td colspan=3>Total Price</td>";
		str+="<td>"+sum+"</td>";
		str+="</tr>";
		str+="</tbody>";
		str+="</table>";
		PrintWriter out= response.getWriter();
		out.print(str);
		
		if(products.size()!=0)
		{
			out.print("<br/><br/><a href='confirm?tp="+sum+"'>Confirm order</a><br/><br/>");
		}
		
		out.print("<br/><a href='home'>Home</a>");
		rd2.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
