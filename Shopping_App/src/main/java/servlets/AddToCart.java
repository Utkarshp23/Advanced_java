package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/addtocart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pid=Integer.parseInt(request.getParameter("pid"));
		HttpSession session= request.getSession();
		List<Integer> products= null;
		products=(List<Integer>)session.getAttribute("cart");
		
		if(products==null)
		{
			products= new ArrayList<>();
		}
		products.add(pid);
		session.setAttribute("cart", products);
		
		PrintWriter out= response.getWriter();
		RequestDispatcher rd1=request.getRequestDispatcher("/header");
		RequestDispatcher rd2=request.getRequestDispatcher("/footer");
		rd1.include(request, response);
		out.print("Product added to cart");
		out.print("<br/>No. of Products:"+products.size());
		out.print("<br/><br/><a href='viewcart'>viewcart</a><br/>");
		out.print("<a href='home'>home</a><br/>");
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
