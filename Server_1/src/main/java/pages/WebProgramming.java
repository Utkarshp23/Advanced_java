package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = { "/webp" }, 
		initParams = { 
				@WebInitParam(name = "subject", value = "Web Programming")
		})
public class WebProgramming extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletConfig sc= getServletConfig();
		ServletContext ctx=getServletContext();
		String sub=sc.getInitParameter("subject");
		String clg=ctx.getInitParameter("institute");
		
		PrintWriter out= response.getWriter();
		out.print(sub+" @"+clg);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
