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
		String date = punchTime.substring(0, 10);
		// String型からTimestamp型への変換
		Timestamp ts = Utility.conv(punchTime);
		
		// 出勤状態フラグ(true=出勤中, false=退勤中)
		boolean attendFlag = Boolean.parseBoolean(request.getParameter("attendflag"));
		
		// DAOクラス
		AttendDAO attDao = new AttendDAO();
		EmployeeDAO empDao = new EmployeeDAO();
		
		System.out.println("{empId:" + empId +
				", punchTime:" + punchTime +
				", date:" + date +
				", timestamp" + ts +
				", attendFlag:" + attendFlag + "}");
		
		try {
			Attend att = attDao.search(date, empId);
			
			System.out.println("{attendId:" + att.getAttendId() +
					", attendanceTime:" + att.getAttendanceTime() +
					", leavingTime:" + att.getLeavingTime() +
					", actualRestTime:" + att.getActualRestTime() +
					", operatingHours:" + att.getOperatingHours() +
					", location:" + att.getLocation() +
					", empId:" + att.getEmpId() + "}");
			
			if (attendFlag == false) {
				// 出勤処理 false(退勤中) => true (出勤中)
				if(att.getAttendId() == 0) {
					System.out.println("出勤処理開始:登録");
					// 出勤情報新規追加
					attDao.newAttendanceInfoAddition(ts, empId);
					empDao.attendanceAndLeavingInfoUpdate(true, empId);
				} else {
					System.out.println("出勤処理開始:更新");
					// 出勤情報更新
					attDao.attendanceInfoUpdate(ts, att.getAttendId());
					empDao.attendanceAndLeavingInfoUpdate(true, empId);
				}
			} else {
				// 退勤処理 true(出勤中) => false (退勤中)
				if(att.getAttendId() == 0) {
					// 日を跨いだ退勤
					System.out.println("退勤処理開始:更新");
					// シーケンス(attenId)の現在値を取得する処理
					int attendId = attDao.searchAttendId(empId);
					// 退勤情報更新
					attDao.leavingInfoUpdate(ts, attendId);
					empDao.attendanceAndLeavingInfoUpdate(false, empId);
				} else {
					// 日を跨がない退勤
					System.out.println("退勤処理開始:更新");
					// 退勤情報更新
					attDao.leavingInfoUpdate(ts, att.getAttendId());
					empDao.attendanceAndLeavingInfoUpdate(false, empId);
				}
			}
			
			request.getRequestDispatcher("/attenddetails").forward(request, response);
			
		} catch(Exception e) {
			e.printStackTrace(out);
		}
	}

}
