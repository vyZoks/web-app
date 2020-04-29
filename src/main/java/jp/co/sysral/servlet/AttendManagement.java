package jp.co.sysral.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sysral.bean.Attend;
import jp.co.sysral.bean.Employee;
import jp.co.sysral.dao.AttendDAO;
import jp.co.sysral.dao.EmployeeDAO;
import jp.co.sysral.util.Utility;

@WebServlet(urlPatterns={"/attendmanagement"})
public class AttendManagement extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		PrintWriter out = response.getWriter();
		
		// session取得
		HttpSession session = request.getSession();
		int empId = (Integer)session.getAttribute("empId");
		
		// 打刻時間(YYYY/MM/DD hh:mm:ss)
		String punchTime = request.getParameter("punchtime");
		String punchTimeDate = punchTime.substring(0, 10);
		// String型からTimestamp型への変換
		Timestamp timestamp = Utility.conv(punchTime.substring(0, 16));
		
		// 出勤状態フラグ(true=出勤中, false=退勤中)
		boolean attendFlag = Boolean.parseBoolean(request.getParameter("attendflag"));
		
		// DAOクラス
		AttendDAO attendDAO = new AttendDAO();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		
		System.out.println("{empId : " + empId +
				", punchTime : " + punchTime +
				", punchTimeDate : " + punchTimeDate +
				", timestamp : " + timestamp +
				", attendFlag : " + attendFlag + "}");
		
		try {
			Attend attend = attendDAO.search(punchTimeDate, empId);
			Employee employee = employeeDAO.search(empId);
			
			System.out.println("{attendId:" + attend.getAttendId() +
					", attendanceTime:" + attend.getAttendanceTime() +
					", leavingTime:" + attend.getLeavingTime() +
					", actualRestTime:" + attend.getActualRestTime() +
					", operatingHours:" + attend.getOperatingHours() +
					", location:" + attend.getLocation() +
					", empId:" + attend.getEmpId() + "}");
			
			if (attendFlag == false) {
				// 出勤処理 false(退勤中) => true (出勤中)
				attend.setAttendanceTime(timestamp);
				attend.setLeavingTime(null);
				attend.setActualRestTime(null);
				attend.setOperatingHours(null);
				attend.setEmpId(empId);
				if(attend.getAttendId() == 0) {
					System.out.println("出勤処理開始:登録");
					// 出勤情報登録
					attendDAO.insert(attend);
					employeeDAO.attendanceAndLeavingInfoUpdate(true, empId);
				} else {
					System.out.println("出勤処理開始:更新");
					// 出勤情報更新
					attendDAO.update(attend);
					employeeDAO.attendanceAndLeavingInfoUpdate(true, empId);
				}
			} else {
				// 退勤処理 true(出勤中) => false (退勤中)
				attend.setLeavingTime(timestamp);
				attend.setActualRestTime(employee.getRestTime());
				if(attend.getAttendId() == 0) {
					// 日を跨いだ退勤
					System.out.println("退勤処理開始:更新");
					// シーケンス(attenId)の現在値を取得
					int attendId = attendDAO.searchAttendId(empId);
					attend.setAttendId(attendId);
					// 実稼働時間の算出
					String operatingHours = Utility.getOperatingHours(attend, employee.getCreditTime());
					attend.setOperatingHours(operatingHours);
					// 退勤情報更新
					attendDAO.update(attend);
					employeeDAO.attendanceAndLeavingInfoUpdate(false, empId);
				} else {
					// 日を跨がない退勤
					System.out.println("退勤処理開始:更新");
					// 実稼働時間の算出
					String operatingHours = Utility.getOperatingHours(attend, employee.getCreditTime());
					attend.setOperatingHours(operatingHours);
					// 退勤情報更新
					attendDAO.update(attend);
					employeeDAO.attendanceAndLeavingInfoUpdate(false, empId);
				}
			}
			
			request.getRequestDispatcher("/attenddetails").forward(request, response);
		} catch(Exception e) {
			e.printStackTrace(out);
		}
	}
}
