package jp.co.sysral.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sysral.bean.Attend;
import jp.co.sysral.bean.Employee;
import jp.co.sysral.dao.AttendDAO;
import jp.co.sysral.dao.EmployeeDAO;

@WebServlet(urlPatterns= {"/sample"})
public class SampleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();
		String mail = request.getParameter("mail");
		String pass = request.getParameter("pass");
		String empId = request.getParameter("empid");
		
		out.println("<!DOCTYPE>");
		out.println("<html lang='ja'>");
		out.println("<head>");
		out.println("<meta charset='UTF-8'>");
		out.println("<title>sample</title>");
		out.println("<body>");
		try {
			EmployeeDAO empDao = new EmployeeDAO();
			Employee emp = empDao.search(mail, pass);
			
			out.println(emp.getEmpId());
			out.println(emp.getEmpName());
			out.println(emp.getMailaddress());
			out.println(emp.getEmpPass());
			out.println(emp.getStartWork());
			out.println(emp.getEndWork());
			out.println(emp.getUnitTime());
			out.println(emp.getBreakTime());
			out.println(emp.getAccessId());
			out.println(emp.isAtendFlag());
			
			AttendDAO attDao = new AttendDAO();
			List<Attend> list = attDao.search(Integer.parseInt(empId));
			
			for (Attend att : list) {
				out.println("<br/>");
				out.println(att.getAtendId());
				out.println(att.getPunchIn());
				out.println(att.getPunchOut());
				out.println(att.getRealBreakTime());
				out.println(att.getOperatingTime());
				out.println(att.getLocation());
				out.println(att.getEmpId());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		out.println("</body>");
		out.println("</html>");
	}
}
