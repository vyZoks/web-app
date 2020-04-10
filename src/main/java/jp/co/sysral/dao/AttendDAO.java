package jp.co.sysral.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.sysral.bean.Attend;

public class AttendDAO extends DAO {
	
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
			att.setAtendFlag(rs.getBoolean("atend_flag"));
			att.setEmpId(rs.getInt("emp_id"));
			list.add(att);
		}
		
		ps.close();
		co.close();
		
		return list;
	}
}
