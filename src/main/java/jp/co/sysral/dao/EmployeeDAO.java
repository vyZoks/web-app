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
	
	// １件検索
	public Employee search(int empId) throws Exception {
		
		Employee emp = new Employee();
		
		Connection co = getConnection();
		
		String sql = "SELECT * FROM s_employee WHERE emp_id = ?";
		PreparedStatement ps = co.prepareStatement(sql);
		ps.setInt(1, empId);
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
	
	// 出勤(退勤)フラグの更新
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
	
	// 登録
	public void insert(Employee employee) throws Exception {
		
		Connection co = getConnection();
		
		String sql = "INSERT INTO s_employee(emp_name, mailaddress, emp_pass, opening_time, closing_time, credit_time, rest_time, attend_flag) VALUES (?, ?, ?, ?, ?, ?, ? ,?)";
		PreparedStatement ps = co.prepareStatement(sql);
		ps.setString(1, employee.getEmpName());
		ps.setString(2, employee.getMailaddress());
		ps.setString(3, employee.getEmpPass());
		ps.setTime(4, employee.getOpeningTime());
		ps.setTime(5, employee.getClosingTime());
		ps.setString(6, employee.getCreditTime());
		ps.setString(7, employee.getRestTime());
		ps.setBoolean(8, employee.isAttendFlag());
		int cnt = ps.executeUpdate();
		
		System.out.println(cnt + "件のデータを登録しました。");
		ps.close();
		co.close();
	}
	// 更新
	public void update(Employee employee) throws Exception {
		
		Connection co = getConnection();
		
		String sql = "UPDATE s_employee SET emp_name = ?, mailaddress = ?, emp_pass = ?, opening_time = ?, closing_time = ?, credit_time = ?, rest_time = ? WHERE emp_id = ?";
		PreparedStatement ps = co.prepareStatement(sql);
		ps.setString(1, employee.getEmpName());
		ps.setString(2, employee.getMailaddress());
		ps.setString(3, employee.getEmpPass());
		ps.setTime(4, employee.getOpeningTime());
		ps.setTime(5, employee.getClosingTime());
		ps.setString(6, employee.getCreditTime());
		ps.setString(7, employee.getRestTime());
		ps.setInt(8, employee.getEmpId());
		int cnt = ps.executeUpdate();
		
		System.out.println(cnt + "件のデータを更新しました。");
		ps.close();
		co.close();
	}
}
