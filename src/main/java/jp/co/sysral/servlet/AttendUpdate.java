package jp.co.sysral.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sysral.bean.Attend;
import jp.co.sysral.bean.Employee;
import jp.co.sysral.dao.AttendDAO;
import jp.co.sysral.dao.EmployeeDAO;
import jp.co.sysral.util.Utility;

@WebServlet(urlPatterns={"/attendupdate"})
public class AttendUpdate extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		PrintWriter out = response.getWriter();
		Attend attend = new Attend();
		AttendDAO attendDAO = new AttendDAO();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		
		attend.setAttendId(Integer.parseInt(request.getParameter("attendid")));
		attend.setAttendanceTime(Utility.conv(Utility.format(request.getParameter("attendancetime"))));
		attend.setLeavingTime(Utility.conv(Utility.format(request.getParameter("leavingtime"))));
		attend.setActualRestTime(request.getParameter("actualresttime"));
		attend.setEmpId(Integer.parseInt(request.getParameter("empid")));
		
		try {
			Employee emp = employeeDAO.search(attend.getEmpId());
			
			String creditTime = emp.getCreditTime();
			// 稼働時間算出
			attend.setOperatingHours(Utility.getOperatingHours(attend, creditTime));
			
			System.out.println("creditTime : " + creditTime);
			System.out.println("attendanceTime : " + attend.getAttendanceTime());
			System.out.println("leavingTime : " + attend.getLeavingTime());
			System.out.println("restTime : " + attend.getActualRestTime());
			System.out.println("operatingHours : " + attend.getOperatingHours());
			
			System.out.println("出退勤情報更新");
			attendDAO.update(attend);
			
			request.getRequestDispatcher("/attenddetails").forward(request, response);
		} catch(Exception e) {
			e.printStackTrace(out);
		}
	}
}
