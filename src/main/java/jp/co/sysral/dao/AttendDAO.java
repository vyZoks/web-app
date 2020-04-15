package jp.co.sysral.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jp.co.sysral.bean.Attend;

public class AttendDAO extends DAO {
	
	// 全件検索
	public List<Attend> search(int empId) throws Exception {
		
		List<Attend> list = new ArrayList<>();
		
		Connection co = getConnection();
		
		String sql = "SELECT * FROM s_attend WHERE emp_id = ?";
		PreparedStatement ps = co.prepareStatement(sql);
		ps.setInt(1, empId);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			Attend att = new Attend();
			att.setAtendId(rs.getInt("atend_id"));
			att.setPunchIn(rs.getTimestamp("punch_in"));
			att.setPunchOut(rs.getTimestamp("punch_out"));
			att.setRealBreakTime(rs.getString("real_break_time"));
			att.setOperatingTime(rs.getString("operating_time"));
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
		
		String sql = "SELECT * FROM s_attend WHERE TO_CHAR(punch_in, 'yyyy/mm/dd') LIKE ? AND emp_id = ?";
		PreparedStatement ps = co.prepareStatement(sql);
		ps.setString(1, date + "%");
		ps.setInt(2, empId);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			att.setAtendId(rs.getInt("atend_id"));
			att.setPunchIn(rs.getTimestamp("punch_in"));
			att.setPunchOut(rs.getTimestamp("punch_out"));
			att.setRealBreakTime(rs.getString("real_break_time"));
			att.setOperatingTime(rs.getString("operating_time"));
			att.setLocation(rs.getString("location"));
			att.setEmpId(rs.getInt("emp_id"));
		}
		
		ps.close();
		co.close();
		
		return att;
	}
	
	// １件登録(出勤情報)
	public void insert(Timestamp atendTime, int empId) throws Exception {
		
		Connection co = getConnection();
		
		String sql = "INSERT INTO s_attend(punch_in, emp_id) VALUES (?, ?)";
		PreparedStatement ps = co.prepareStatement(sql);
		ps.setTimestamp(1, atendTime);
		ps.setInt(2, empId);
		int cnt = ps.executeUpdate();
		
		System.out.println(cnt + "件のデータを登録しました。");
		
		ps.close();
		co.close();
	}
	
	// １件更新(出退勤情報)
	public void update(Timestamp atendTime, int atendId, boolean atendFlag) throws Exception {
		
		Connection co = getConnection();
		String sql = "";
		
		if (atendFlag == false) {
			// 出勤処理(false = 退勤中)
			sql = "UPDATE s_attend SET punch_in = ? WHERE atend_id = ?";
		} else {
			// 退勤処理(true = 出勤中)
			sql = "UPDATE s_attend SET punch_out = ? WHERE atend_id = ?";
		}
		PreparedStatement ps = co.prepareStatement(sql);
		ps.setTimestamp(1, atendTime);
		ps.setInt(2, atendId);
		int cnt = ps.executeUpdate();
		
		System.out.println(cnt + "件のデータを更新しました。");
		
		ps.close();
		co.close();
	}
	
	// シーケンスの現在値取得
	public int currval(int empId) throws Exception {
		
		Connection co = getConnection();
		int seq = 0;
		
		String sql = "SELECT MAX(atend_id) AS atend_id FROM s_attend WHERE emp_id = ?";
		PreparedStatement ps = co.prepareStatement(sql);
		ps.setInt(1, empId);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			seq = rs.getInt("atend_id");
		}
		
		System.out.println("{atend_id_seq:" + seq + "}");
		ps.close();
		co.close();
		
		return seq;
	}
}
