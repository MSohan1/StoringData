package registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/view/Register")
public class Page1 extends HttpServlet
{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String URL="jdbc:mysql://localhost:3307/teca59";
			String USER="root";
			String PASS="12345";
			HttpSession session = req.getSession();
			
			String QWERY= "INSERT INTO employee VALUES (?,?,?,?,?,?,?,?,?,?);";
			Connection connection = DriverManager.getConnection(URL, USER, PASS);
			PreparedStatement  preparedStatement = connection.prepareStatement(QWERY);
			int employeeId=0;
			String  firstname=null, lastname=null, email=null, address=null, mobile=null, username=null, password=null, salary=null, deptno=null;
			
			email = req.getParameter("mail");
			password  = req.getParameter("pass");
			preparedStatement.setInt(1, employeeId);
			preparedStatement.setString(2, firstname);
			preparedStatement.setString(3, lastname);
			preparedStatement.setString(4, email);
			preparedStatement.setString(5, address);
			preparedStatement.setString(6, mobile);
			preparedStatement.setString(7, username);
			preparedStatement.setString(8, password);
			preparedStatement.setString(9, salary);
			preparedStatement.setString(10, deptno);
			
			int record = preparedStatement.executeUpdate();
			
			session.setAttribute("mail", email);
			session.setAttribute("password", password);
			session.setMaxInactiveInterval(30);
			
			System.out.println(record);
			if(record>0)
			{
				resp.sendRedirect("UserInfo.html");
			}
			else
			{
				resp.sendRedirect("https://www.youtube.com/");
			}
			
			
		} catch (Exception e)
		
		{

		}
		
	}
}
