package Lin.NoteBook;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Booking
 */
@WebServlet("/Booking")
public class Booking extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con=null;
	Connection con1=null;
    Statement  stmt=null;
    Statement  stmt1=null;
    ResultSet  rs;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Booking() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		   
	  }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected synchronized void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("big5");
		response.setCharacterEncoding("big5");
		response.setContentType("text/html;big5");
		PrintWriter  out=response.getWriter();
		
		//String ID=request.getParameter("ID");
		//String title=request.getParameter("title");
		//String[] seat=request.getParameterValues("seat");
		
		out.println("<html><head></head><body>");
		
		String ID=request.getParameter("ID");
		//String title=request.getParameter("title");
		//String date=request.getParameter("date");
		String seat=request.getParameter("seat");
		String price=request.getParameter("price");
		
		System.out.println("?????????:"+ID);
		System.out.println("????????????:Beauty");
		
	    System.out.println("??????:"+seat);
		
		System.out.println("??????:"+price);
				
		try {			   
			   Class.forName("com.mysql.jdbc.Driver");
	           con=DriverManager.getConnection("jdbc:mysql://localhost/book","root","");
	           stmt=con.createStatement();	           
	           stmt.executeUpdate("insert into location(seat,ID,name,time,price)values('"+seat+"','"+ID+"','Beauty','2021-12-25-08:00','"+price+"')");
	           
	           String USERS = "C:/Users/user/Desktop/Note.data";
			   File file = new File(USERS);
			   
			   FileWriter fw = new FileWriter(file, true);
			   BufferedWriter br = new BufferedWriter(fw);
			   
			   DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			   Date date = new Date();
			   String dateToStr = dateFormat.format(date);
			   
			   fw.append("\n\n");
			   fw.append("?????????:"+ID+"\n");
			   fw.append("????????????:Beauty"+"\n");
			   fw.append("??????:"+seat+"\n");
			   fw.append("????????????:2021-12-25-08:00"+"\n");
			   fw.append("??????:"+price+"\n");
			   fw.append("??????:"+dateToStr+"\n\n");
	   		   System.out.println("????????????????????????Note.data");
	   		   
	   		   fw.flush();
			   fw.close();
			   
			   boolean bool = file.exists();
			   while(!bool) {
				   file = new File(USERS);
				   bool=true;
			   }
			   
	    out.println("<a href='Input.html'>??????</a> <br/>");
	    out.println("<a href='Ticket.html'>????????????</a> <br/>");
	    out.print("</body></html>");
        out.close();	   	           
		}catch(Exception e)
	    {
		  out.println("????????????????????????<br/>");
		  out.println("<a href='Input.html'>??????</a> <br/>");
	    }finally {
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


