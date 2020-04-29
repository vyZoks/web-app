package jp.co.sysral.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.sysral.bean.Attend;

public class AttendDAO extends DAO {
	
	// 全件検索
	public List<Attend> allSearch(int empId) throws Exception {
		
		List<Attend> list = new ArrayList<>();
		
		Connection co = getConnection();
		
		String sql = "SELECT * FROM s_attend WHERE emp_id = ?";
		PreparedStatement ps = co.prepareStatement(sql);
		ps.setInt(1, empId);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			Attend att = new Attend();
			att.setAttendId(rs.getInt("attend_id"));
			att.setAttendanceTime(rs.getTimestamp("attendance_time"));
			att.setLeavingTime(rs.getTimestamp("leaving_time"));
			att.setActualRestTime(rs.getString("actual_rest_time"));
			att.setOperatingHours(rs.getString("operating_hours"));
			att.setLocation(rs.getString("location"));
			att.setEmpId(rs.getInt("emp_id"));
			list.add(att);
		}
		
		ps.close();
		co.close();
		
		return list;
	}
	
	// １件検索
	public Attend search(String date, int empId) throws Exception {
		
		Attend att = new Attend();
		
		Connection co = getConnection();
		
		String sql = "SELECT * FROM s_attend WHERE TO_CHAR(attendance_time, 'yyyy/mm/dd') LIKE ? AND emp_id = ?";
		PreparedStatement ps = co.prepareStatement(sql);
		ps.setString(1, date + "%");
		ps.setInt(2, empId);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			att.setAttendId(rs.getInt("attend_id"));
			att.setAttendanceTime(rs.getTimestamp("attendance_time"));
			att.setLeavingTime(rs.getTimestamp("leaving_time"));
			att.setActualRestTime(rs.getString("actual_rest_time"));
			att.setOperatingHours(rs.getString("operating_hours"));
			att.setLocation(rs.getString("location"));
			att.setEmpId(rs.getInt("emp_id"));
		}
		
		ps.close();
		co.close();
		
		return att;
	}
	
	// １件検索
	public Attend search(int attendId) throws Exception {
		
		Attend att = new Attend();
		
		Connection co = getConnection();
		
		String sql = "SELECT * FROM s_attend WHERE attend_id = ?";
		PreparedStatement ps = co.prepareStatement(sql);
		ps.setInt(1, attendId);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			att.setAttendId(rs.getInt("attend_id"));
			att.setAttendanceTime(rs.getTimestamp("attendance_time"));
			att.setLeavingTime(rs.getTimestamp("leaving_time"));
			att.setActualRestTime(rs.getString("actual_rest_time"));
			att.setOperatingHours(rs.getString("operating_hours"));
			att.setLocation(rs.getString("location"));
			att.setEmpId(rs.getInt("emp_id"));
		}
		
		ps.close();
		co.close();
		
		return att;
	}
	
	// シーケンス(attenId)の現在値を取得
	public int searchAttendId(int empId) throws Exception {
		
		Connection co = getConnection();
		int seq = 0;
		
		String sql = "SELECT MAX(attend_id) AS attend_id FROM s_attend WHERE emp_id = ?";
		PreparedStatement ps = co.prepareStatement(sql);
		ps.setInt(1, empId);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			seq = rs.getInt("attend_id");
		}
		
		System.out.println("{attend_id_seq:" + seq + "}");
		ps.close();
		co.close();
		
		return seq;
	}
	
	// 登録
	public void insert(Attend attend) throws Exception {
		
		Connection co = getConnection();
		
		String sql = "INSERT INTO s_attend(attendance_time, emp_id) VALUES (?, ?)";
		PreparedStatement ps = co.prepareStatement(sql);
		ps.setTimestamp(1, attend.getAttendanceTime());
		ps.setInt(2, attend.getEmpId());
		int cnt = ps.executeUpdate();
		
		System.out.println("出退勤情報" + cnt + "件を登録しました。");
		ps.close();
		co.close();
	}
	
	// 更新
	public void update(Attend attend) throws Exception {
		
		Connection co = getConnection();
		
		String sql = "UPDATE s_attend SET attendance_time = ?, leaving_time = ?, actual_rest_time = ?, operating_hours = ? WHERE attend_id = ?";
		PreparedStatement ps = co.prepareStatement(sql);
		ps.setTimestamp(1, attend.getAttendanceTime());
		ps.setTimestamp(2, attend.getLeavingTime());
		ps.setString(3, attend.getActualRestTime());
		ps.setString(4, attend.getOperatingHours());
		ps.setInt(5, attend.getAttendId());
		int cnt = ps.executeUpdate();
		
		System.out.println("出退勤情報" + cnt + "件を更新しました。");
		ps.close();
		co.close();
	}
	
	// 削除
	public void delete(int attendId) throws Exception {
		
		Connection co = getConnection();
		
		String sql = "DELETE FROM s_attend WHERE attend_id = ?";
		PreparedStatement ps = co.prepareStatement(sql);
		ps.setInt(1, attendId);
		int cnt = ps.executeUpdate();
		
		System.out.println("出退勤情報" + cnt + "件を削除しました。");
		ps.close();
		co.close();
	}
}
