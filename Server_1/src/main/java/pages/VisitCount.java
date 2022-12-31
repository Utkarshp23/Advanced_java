package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VisitCount
 */
@WebServlet("/visitcount")
public class VisitCount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cnt=0;
		Cookie [] all= request.getCookies();
		PrintWriter out= response.getWriter();
		response.setContentType("text/html");
		if(all!=null)
		{
			for(Cookie c :all)
			{
				if(c.getName().equals("vcnt"))
				{
					cnt=Integer.parseInt(c.getValue());
				}
			}
		}
		cnt++;
		Cookie c = new Cookie("vcnt",cnt+"");
		response.addCookie(c);
		out.print("VisitCount: "+ cnt);
		out.print("<a href='visitcount'>Refresh..</a><br/>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
