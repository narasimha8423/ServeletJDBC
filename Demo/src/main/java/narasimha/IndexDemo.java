package narasimha;

import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class IndexDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String n1 = request.getParameter("fname");
		String n2 = request.getParameter("lname");
		PrintWriter out = response.getWriter();
		out.println(n1+" "+n2);
		try
		{
			Class.forName("com.mysql.jdbc.Driver"); //loading 4th driver
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/wipro","root","");
			if(con!=null)
			{
			System.out.println("Connection Successfull");
			}
			PreparedStatement ps=con.prepareStatement("insert into student values(?,?)");
			
			ps.setString(1, n1);
			ps.setString(2, n2);
			int r=ps.executeUpdate();
			if(r==0)
			{
			out.println("Insertion Failure");
			}
			else
			{
			out.println("No of rows inserted:"+r);
			}
			con.close();
		}
		catch(Exception q)
		{
			out.println("Error");
		}

	}

}
