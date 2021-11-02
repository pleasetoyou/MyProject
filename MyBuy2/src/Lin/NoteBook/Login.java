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
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con=null;
    Statement  stmt=null;
    ResultSet  rs;
	  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		response.setContentType("big5");
		PrintWriter  out=response.getWriter();
		
		boolean isFindData = false;
		String account=request.getParameter("account");
		String password=request.getParameter("password");
		
		try {
			   Class.forName("com.mysql.jdbc.Driver");
	           con=DriverManager.getConnection("jdbc:mysql://localhost/book","root","");
	           stmt=con.createStatement();
	           //account是唯一的
	           rs=stmt.executeQuery("SELECT * FROM member WHERE(account='"+account+"' and password='"+password+"')");
	           
	           while(true==rs.next()) {
	        	   isFindData = true;
	        	   
	        	   if(request.getSession(false) != null) {
	        		   request.changeSessionId();
	        	   }
	        	   
	        	   request.getSession().setAttribute("login", account);
	        	   
	        	   int keyword=(int)(((Math.random())*10000)+1000);
		   		   System.out.println(keyword);
		   		   
		   		   String USERS = "C:/Users/user/Desktop/Note.data";
				   File file = new File(USERS);
				   
				   FileWriter fw = new FileWriter(file, true);
				   BufferedWriter br = new BufferedWriter(fw);
				   
				   DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				   Date date = new Date();
				   String dateToStr = dateFormat.format(date);
				   
				   fw.append("\n\n");
				   fw.append("KeyWord:"+keyword+"\n");
				   fw.append("時間:"+dateToStr+"\n\n");
		   		   System.out.println("KeyWord已發送到Note.data");
		   		   
		   		   fw.flush();
				   fw.close();
				   
				   boolean bool = file.exists();
				   while(!bool) {
					   file = new File(USERS);
					   bool=true;
				   }
				   
		   		   Scanner sc = new Scanner(System.in);
				   System.out.print("請輸入KeyWord:");
				   int a = sc.nextInt();
				   
				   if(a == keyword) {
	        	   RequestDispatcher  rd=request.getRequestDispatcher("/Member");
		           rd.forward(request, response);
				   }else {
					   
					   out.println("<a href='Login.html'>重回登入首頁</a> <br/>");
				   }
	           }
	    	   //RequestDispatcher  rd=request.getRequestDispatcher("/read_Member");
	           //rd.forward(request, response);
	           
	           //out.print("</table>");
	           if (false == isFindData) {	                
	                out.println("<a href='Login.html'>重回登入頁面</a> <br />");
	                return;
	            }
			   
			   out.close();
			   stmt.close();
		       con.close();
	       }catch(Exception e)
	       {
	    	   out.print(e);
	       }
	}

}
