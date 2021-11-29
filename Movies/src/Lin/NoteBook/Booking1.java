package Lin.NoteBook;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
 * Servlet implementation class Booking1
 */
@WebServlet("/Booking1")
public class Booking1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con=null;
	Connection con1=null;
    Statement  stmt=null;
    Statement  stmt1=null;
    ResultSet  rs;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Booking1() {
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
	protected synchronized void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("big5");
		response.setCharacterEncoding("big5");
		response.setContentType("text/html;big5");
		PrintWriter  out=response.getWriter();
		
		out.println("<html><head></head><body>");
		
		String ID=request.getParameter("ID");
		//String title=request.getParameter("title");
		//String date=request.getParameter("date");
		String seat=request.getParameter("seat");
		String price=request.getParameter("price");
		
		System.out.println("身分證:"+ID);
		System.out.println("電影名稱:The rack");		
	    System.out.println("座位:"+seat);		
		System.out.println("票價:"+price);
		
		try {			   
			   Class.forName("com.mysql.jdbc.Driver");
	           con=DriverManager.getConnection("jdbc:mysql://localhost/book","root","");
	           stmt=con.createStatement();	           
	           stmt.executeUpdate("insert into location1(seat,ID,name,time,price)values('"+seat+"','"+ID+"','The rack','2021-12-25-10:00','"+price+"')");
	    
	           String USERS = "C:/Users/user/Desktop/Note.data";
			   File file = new File(USERS);
			   
			   FileWriter fw = new FileWriter(file, true);
			   BufferedWriter br = new BufferedWriter(fw);
			   
			   DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			   Date date = new Date();
			   String dateToStr = dateFormat.format(date);
			   
			   fw.append("\n\n");
			   fw.append("身分證:"+ID+"\n");
			   fw.append("電影名稱:The rack"+"\n");
			   fw.append("座位:"+seat+"\n");
			   fw.append("電影時刻:2021-12-25-10:00"+"\n");
			   fw.append("票價:"+price+"\n");
			   fw.append("時間:"+dateToStr+"\n\n");
	   		   System.out.println("購票紀錄已發送到Note.data");
	   		   
	   		   fw.flush();
			   fw.close();
			   
			   boolean bool = file.exists();
			   while(!bool) {
				   file = new File(USERS);
				   bool=true;
			   }
	    out.println("<a href='Input1.html'>購票</a> <br/>");
	    out.println("<a href='Ticket1.html'>退票系統</a> <br/>");
	    out.print("</body></html>");
     out.close();	   	           
		}catch(Exception e)
	    {
		  out.println("此座位已經被購買<br/>");
		  out.println("<a href='Input1.html'>購票</a> <br/>");
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
