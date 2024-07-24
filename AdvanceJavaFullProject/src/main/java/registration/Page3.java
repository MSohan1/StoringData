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

@WebServlet("/view/CompanyDetails")
public class Page3 extends HttpServlet
{

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
		{
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String URL = "jdbc:mysql://localhost:3307/teca59";
				String USER = "root";
				String PASS = "12345";
				HttpSession session = req.getSession();
				Connection connection = DriverManager.getConnection(URL, USER, PASS);
				String email = (String) session.getAttribute("mail");
				if(email!=null)
				{
					String username = req.getParameter("username");
					String salary = req.getParameter("sal");
					String deptno = req.getParameter("deptno");
					String QWERY = "Update employee set username=?, salary=?,deptno=? where email=?";
					PreparedStatement preparedStatement = connection.prepareStatement(QWERY);
					preparedStatement.setString(1, username);
					preparedStatement.setString(2, salary);
					preparedStatement.setString(3, deptno);
					preparedStatement.setString(4, email);
					int result = preparedStatement.executeUpdate();
					if(result>0)
					{
						resp.sendRedirect("Successful.html");
					}
					else
					{
						resp.sendRedirect("https://www.youtube.com/");
						
					}
					
				}
				else
				{
					String QWERY="delete from employee where username is NULL;";
					PreparedStatement preparedStatement = connection.prepareStatement(QWERY);
					int result = preparedStatement.executeUpdate();
					resp.sendRedirect("SessionTime.html");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}
