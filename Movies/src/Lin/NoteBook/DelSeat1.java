package Lin.NoteBook;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DelSeat1
 */
@WebServlet("/DelSeat1")
public class DelSeat1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con=null;
    Statement  stmt=null;
    ResultSet  rs;
    String sql;    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelSeat1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("big5");
		response.setCharacterEncoding("big5");
		response.setContentType("text/html;big5");
		PrintWriter  out=response.getWriter();
		
        out.println("<html><head></head><body>");
        
        String ID=request.getParameter("ID");
		
		sql="Delete from location1 WHERE ID = '"+ID+"'";
		System.out.println(sql);
		
		try {
			   Class.forName("com.mysql.jdbc.Driver");
	           con=DriverManager.getConnection("jdbc:mysql://localhost/book","root","");
	           stmt=con.createStatement();
	           stmt.executeUpdate(sql);   
	           //rs.last();
	           //rs.deleteRow();
	    	   RequestDispatcher  rd=request.getRequestDispatcher("/Query1");
	           rd.forward(request, response);
	           
	           //out.print("</table>");
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

}
