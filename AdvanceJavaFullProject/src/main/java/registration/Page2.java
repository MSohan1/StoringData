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

@WebServlet("/view/UserInfo")
public class Page2 extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String URL = "jdbc:mysql://localhost:3307/teca59";
			String USER = "root";
			String PASS = "12345";
			HttpSession session = req.getSession();
//			String QWERY = "Select email from employee where email = ?";
			HttpSession session2 = req.getSession();
			
			String id = req.getParameter("eid");
			System.out.println(id);
			int employeeId = Integer.parseInt(id);
			System.out.println(employeeId);
			String firstName = req.getParameter("firstname");
			String lastName =req.getParameter("lastname");
			String address =req.getParameter("address");
			String mobile =req.getParameter("mobile");
			Connection connection = DriverManager.getConnection(URL, USER, PASS);
			
			String email = (String) session2.getAttribute("mail");
			if(email!=null )
			{
//				String id="0";
				String QWERY2 = "Update employee set employeeId = ?, firstname = ?, lastname = ?, address = ?, mobile = ? where email = ?";
				PreparedStatement preparedStatement = connection.prepareStatement(QWERY2);
				System.out.println(employeeId);
				preparedStatement.setInt(1, employeeId);
				preparedStatement.setString(2, firstName);
				preparedStatement.setString(3, lastName);
				preparedStatement.setString(4, address);
				preparedStatement.setString(5, mobile);
				preparedStatement.setString(6, email);
				
				int result = preparedStatement.executeUpdate();
				session2.setAttribute("mail", email);
				session2.setMaxInactiveInterval(30);
				
				if(result>0)
				{
					resp.sendRedirect("CompanyDetails.html");
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
