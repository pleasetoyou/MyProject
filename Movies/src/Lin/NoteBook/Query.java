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
 * Servlet implementation class Query
 */
@WebServlet("/Query")
public class Query extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con=null;
    Statement  stmt=null;
    ResultSet  rs;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Query() {
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
	           rs=stmt.executeQuery("select  *  from  location");
	           
	           out.print("<table border='1'>");
	           out.print("<tr><td>座位<td>身分證<td>電影<td>時間<td>價格");
	           
	           while(rs.next())
	           {   out.print("<tr>");
	               out.print("<td>"+rs.getString("seat")); 
	               out.print("<td>"+rs.getString("ID"));
	               out.print("<td>"+rs.getString("name"));
	               out.print("<td>"+rs.getString("time"));
	               out.print("<td>"+rs.getString("price"));
	           }
	           out.print("</table>"); 
			   
	           out.println("<a href='DelSeat.html'>刪除電影票</a> <br/>");
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
