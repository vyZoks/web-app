package jp.co.sysral.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jp.co.sysral.bean.Employee;

public class EmployeeDAO extends DAO {

	// １件検索
	public Employee search(String mailaddress, String password) throws Exception {
		
		Employee emp = new Employee();
		
		Connection co = getConnection();
		
		String sql = "SELECT * FROM s_employee WHERE mailaddress = ? AND emp_pass = ?";
		PreparedStatement ps = co.prepareStatement(sql);
		ps.setString(1, mailaddress);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			emp.setEmpId(rs.getInt("emp_id"));
			emp.setEmpName(rs.getString("emp_name"));
			emp.setMailaddress(rs.getString("mailaddress"));
			emp.setEmpPass(rs.getString("emp_pass"));
			emp.setOpeningTime(rs.getTime("opening_time"));
			emp.setClosingTime(rs.getTime("closing_time"));
			emp.setCreditTime(rs.getString("credit_time"));
			emp.setRestTime(rs.getString("rest_time"));
			emp.setAttendFlag(rs.getBoolean("attend_flag"));
		}
		
		ps.close();
		co.close();
		
		return emp;
	}
	
	// 出退勤フラグの更新
	public void attendanceAndLeavingInfoUpdate(boolean attendFlag, int empId) throws Exception {
		
		Connection co = getConnection();
		
		String sql = "UPDATE s_employee SET attend_flag = ? WHERE emp_id = ?";
		PreparedStatement ps = co.prepareStatement(sql);
		ps.setBoolean(1, attendFlag);
		ps.setInt(2, empId);
		int cnt = ps.executeUpdate();
		
		System.out.println(cnt + "件のデータを更新しました。");
		
		ps.close();
		co.close();
	}
}
