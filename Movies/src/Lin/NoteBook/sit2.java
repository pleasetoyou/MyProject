package Lin.NoteBook;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class sit2
 */
@WebServlet("/sit2")
public class sit2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con=null;
    Statement  stmt=null;
    ResultSet   rs;     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sit2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("big5");
		response.setCharacterEncoding("big5");
		response.setContentType("text/html;big5");
		PrintWriter  out=response.getWriter();
		
		out.println("<html><head></head><body>");
		
		try {
			   Class.forName("com.mysql.jdbc.Driver");
	           con=DriverManager.getConnection("jdbc:mysql://localhost/book","root","");
	           stmt=con.createStatement();
	           rs=stmt.executeQuery("select  *  from  location2 where name='TOP GUN' and time='2021-12-25-12:00'");
	           
	           while(rs.next())
	           {   
	        	  String sit = rs.getString("seat");
	        	  int sitting = Integer.parseInt(sit);
	        	  	        	  	        	  
	        	  if(sitting > 10 && sitting < 14) {
	        		 out.println(sitting+"<br>");
	        	  }
	        	  if(sitting > 20 && sitting < 24) {
	        		 out.println(sitting+"<br>"); 	        		  
	        	  }
	        	  if(sitting > 30 && sitting < 34) {
	        		 out.println(sitting+"<br>");         		  
	        	  }	        	  
	           }
	           			   
	           out.println("<a href='Input1.html'>購票</a> <br/>");
	           //out.println("<a href='Box.html'>購買目錄</a> <br/>");
	           out.print("</body></html>");
			   out.close();
	       }catch(Exception e)
	       {
	    	   out.print(e);
	       }
		   finally {
			   try {
			      stmt.close();
	              con.close();
			   }catch(Exception e)
			   {
				   out.print(e);
			   }
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
