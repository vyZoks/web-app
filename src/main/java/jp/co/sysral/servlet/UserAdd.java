package jp.co.sysral.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sysral.bean.Employee;
import jp.co.sysral.dao.EmployeeDAO;
import jp.co.sysral.util.Utility;

@WebServlet(urlPatterns={"/useradd"})
public class UserAdd extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		PrintWriter out = response.getWriter();
		Employee employee = new Employee();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		
		employee.setEmpName(request.getParameter("empname"));
		employee.setEmpPass(request.getParameter("emppass"));
		employee.setMailaddress(request.getParameter("mail"));
		employee.setOpeningTime(Utility.getTime(request.getParameter("openingtime")));
		employee.setClosingTime(Utility.getTime(request.getParameter("closingtime")));
		employee.setCreditTime(request.getParameter("credittime"));
		employee.setRestTime(request.getParameter("resttime"));
		employee.setAttendFlag(false);
		
		try {
			System.out.println("empName : " + employee.getEmpName());
			System.out.println("empPass : " + employee.getEmpPass());
			System.out.println("mail : " + employee.getMailaddress());
			System.out.println("openingTime : " + employee.getOpeningTime());
			System.out.println("closingTime : " + employee.getClosingTime());
			System.out.println("creditTime : " + employee.getCreditTime());
			System.out.println("restTime : " + employee.getRestTime());
			System.out.println("attendFlag : " + employee.isAttendFlag());
			
			System.out.println("ユーザ情報登録");
			employeeDAO.insert(employee);
			
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} catch(Exception e) {
			e.printStackTrace(out);
		}
	}
}
