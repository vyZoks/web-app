package jp.co.sysral.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Objects;

import jp.co.sysral.bean.Attend;

public class Utility {

	// String型をTimestamp型に変換する
	public static Timestamp conv(String date) {
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			Timestamp timestamp = new Timestamp(sdf.parse(date).getTime());
			return timestamp;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// Timestamp型をString型に変換する
	public static String conv(Timestamp timestamp) {
		
		if (Objects.isNull(timestamp)) return null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(timestamp).replace(" ", "T");
	}
	
	// yyyy-MM-ddTHH:mmをyyyy/MM/dd HH:mmに変換
	public static String format(String date) {
		
		String tmp = date.replace("-", "/").replace("T", " ");
		StringBuilder sb = new StringBuilder(tmp);
		
		return sb.toString();
	}
	
	// Timestamp型から日付をString型で返却する
	public static String getDate(Timestamp timestamp) {
		
		if (Objects.isNull(timestamp)) return null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
		return sdf.format(timestamp);
	}
	
	// Timestamp型から時間をString型で返却する
	public static String getTime(Timestamp timestamp) {
		
		if (Objects.isNull(timestamp)) return null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return sdf.format(timestamp);
	}
	
	// String型をTime型に変換する
	public static Time getTime(String date) {
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			Time time = new Time(sdf.parse(date).getTime());
			return time;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// 出勤時間と退勤時間から稼働時間を算出する
	public static String getOperatingHours(Attend attend, String creditTime) {
		
		// 出勤時間または退勤時間がnullの場合
		if (Objects.isNull(attend.getAttendanceTime()) || Objects.isNull(attend.getLeavingTime())) return null;
		// 単位時間がnullまたはブランクの場合
		if (Objects.isNull(creditTime) || creditTime.isEmpty()) return null;
		// 休憩時間がnullまたはブランクの場合
		if (Objects.isNull(attend.getActualRestTime()) || attend.getActualRestTime().isEmpty()) return null;
		
		Long millisecond = 60L * 60L * 1000L;
		Long restMillisec = Long.parseLong(attend.getActualRestTime()) * 60L * 1000L;
		Long attendanceMillisec = operationHoursAdjustment(attend.getAttendanceTime(), creditTime);
		Long leavingMillisec = operationHoursAdjustment(attend.getLeavingTime(), creditTime);
		// 稼働時間(ms) = 退勤時間(ms) - 出勤時間(ms)
		Long operatingHours = leavingMillisec - attendanceMillisec;
		
		if (operatingHours > restMillisec) {
			operatingHours = operatingHours - restMillisec;
		} else {
			attend.setActualRestTime("0");
		}
		
		String result = String.valueOf(operatingHours.doubleValue() / millisecond.doubleValue());
		// 稼働時間の桁数が6桁以上の場合6桁以降を切り捨て
		if (result.length() >= 6) result = result.substring(0, 5);
		
		return result;
	}
	
	// 出勤時間または退勤時間を単位時間で調整する
	@SuppressWarnings("deprecation")
	public static Long operationHoursAdjustment(Timestamp timestamp, String creditTime) {
		
		// (1)出勤時間(退勤時間)の分数を算出(分)
		int min = timestamp.getMinutes();
		
		// (2) min(分)をcredit(分:単位時間)で割って剰余(分)を算出
		int rem = min % Integer.parseInt(creditTime);
		
		// (3) (2)で算出した剰余(分)をmsに変換
		Long remMin = rem * (60L * 1000L);
		
		// (4) (3)で算出した剰余が０の場合は出勤時間(ms)をそのまま返却
		if (rem == 0) return timestamp.getTime();
		// (4) (3)で算出した剰余が０でない場合は出勤時間(ms)から剰余(ms)を減算したものを返却
		if (rem != 0) return timestamp.getTime() - remMin;
		
		return 0L;
	}
}
