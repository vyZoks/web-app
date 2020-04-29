package jp.co.sysral.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sysral.bean.Employee;
import jp.co.sysral.dao.EmployeeDAO;

@WebServlet(urlPatterns={"/userupdateconfirmation"})
public class UserUpdateConfirmation extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		int empId = (Integer)session.getAttribute("empId");
		
		EmployeeDAO employeeDAO = new EmployeeDAO();
		
		try {
			Employee employee = employeeDAO.search(empId);
			
			request.setAttribute("employee", employee);
			request.getRequestDispatcher("/userupdate.jsp").forward(request, response);
		} catch(Exception e) {
			e.printStackTrace(out);
		}
	}
}
