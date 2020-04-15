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
		String atendTime = request.getParameter("atendtime");
		String date = atendTime.substring(0, 10);
		// String型からTimestamp型への変換
		Timestamp ts = Utility.conv(atendTime);
		
		// 出勤状態フラグ(true=出勤中, false=退勤中)
		boolean atendFlag = Boolean.parseBoolean(request.getParameter("atendflag"));
		
		// DAOクラス
		AttendDAO attDao = new AttendDAO();
		EmployeeDAO empDao = new EmployeeDAO();
		
		System.out.println("{empId:" + empId + ", atendTime:" + atendTime + ", date:" + date + ", timestamp" + ts + ", atendFlag:" + atendFlag + "}");
		
		try {
			Attend att = attDao.search(date, empId);
			
			System.out.println("{atendId:" + att.getAtendId() + ", punchIn:" + att.getPunchIn() + ", punchOut:" + att.getPunchOut() + 
					", realBreakTime:" + att.getRealBreakTime() + ", operatingTime:" + att.getOperatingTime() + ", location:" + att.getLocation()
					+ ", empId:" + att.getEmpId() + "}");
			
			if (atendFlag == false) {
				// 出勤処理 false(退勤中) => true (出勤中)
				if(att.getAtendId() == 0) {
					// レコード追加
					System.out.println("出勤処理開始:登録");
					attDao.insert(ts, empId);
					empDao.update(true, empId);
				} else {
					// レコード更新
					System.out.println("出勤処理開始:更新");
					attDao.update(ts, att.getAtendId(), atendFlag);
					empDao.update(true, empId);
				}
			} else {
				// 退勤処理 true(出勤中) => false (退勤中)
				if(att.getAtendId() == 0) {
					// 日を跨いだ退勤
					System.out.println("退勤処理開始:更新");
					// シーケンスの現在値を取得する処理
					int seq = attDao.currval(empId);
					attDao.update(ts, seq, atendFlag);
					empDao.update(false, empId);
				} else {
					// 日を跨がない退勤
					System.out.println("退勤処理開始:更新");
					attDao.update(ts, att.getAtendId(), atendFlag);
					empDao.update(false, empId);
				}
			}
			
			out.println("処理完了");
			
		} catch(Exception e) {
			e.printStackTrace(out);
		}
	}

}
