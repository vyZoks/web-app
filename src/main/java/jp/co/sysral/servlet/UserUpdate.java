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
import jp.co.sysral.util.Utility;

@WebServlet(urlPatterns={"/userupdate"})
public class UserUpdate extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		PrintWriter out = response.getWriter();
		Employee employee = new Employee();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		
		HttpSession session = request.getSession();
		employee.setEmpId((Integer)session.getAttribute("empId"));
		employee.setEmpName(request.getParameter("empname"));
		employee.setEmpPass(request.getParameter("emppass"));
		employee.setMailaddress(request.getParameter("mail"));
		employee.setOpeningTime(Utility.getTime(request.getParameter("openingtime")));
		employee.setClosingTime(Utility.getTime(request.getParameter("closingtime")));
		employee.setCreditTime(request.getParameter("credittime"));
		employee.setRestTime(request.getParameter("resttime"));
		
		try {
			System.out.println("empId : " + employee.getEmpId());
			System.out.println("empName : " + employee.getEmpName());
			System.out.println("empPass : " + employee.getEmpPass());
			System.out.println("mail : " + employee.getMailaddress());
			System.out.println("openingTime : " + employee.getOpeningTime());
			System.out.println("closingTime : " + employee.getClosingTime());
			System.out.println("creditTime : " + employee.getCreditTime());
			System.out.println("restTime : " + employee.getRestTime());
			
			System.out.println("ユーザー情報更新");
			employeeDAO.update(employee);
			
			request.getRequestDispatcher("/attenddetails").forward(request, response);
		} catch(Exception e) {
			e.printStackTrace(out);
		}
	}
}
