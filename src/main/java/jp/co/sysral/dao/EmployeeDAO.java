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
			emp.setStartWork(rs.getTime("start_work"));
			emp.setEndWork(rs.getTime("end_work"));
			emp.setUnitTime(rs.getString("unit_time"));
			emp.setBreakTime(rs.getString("break_time"));
			emp.setAccessId(rs.getString("access_id"));
			emp.setAtendFlag(rs.getBoolean("atend_flag"));
		}
		
		ps.close();
		co.close();
		
		return emp;
	}
	
	// １件更新(出退勤情報)
	public void update(boolean atendFlag, int empId) throws Exception {
		
		Connection co = getConnection();
		
		String sql = "UPDATE s_employee SET atend_flag = ? WHERE emp_id = ?";
		PreparedStatement ps = co.prepareStatement(sql);
		ps.setBoolean(1, atendFlag);
		ps.setInt(2, empId);
		int cnt = ps.executeUpdate();
		
		System.out.println(cnt + "件のデータを更新しました。");
		
		ps.close();
		co.close();
	}
}
