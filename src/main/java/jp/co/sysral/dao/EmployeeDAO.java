package jp.co.sysral.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jp.co.sysral.bean.Employee;

public class EmployeeDAO extends DAO {

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
}
