package jp.co.sysral.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class Utility {

	// String型をTimestamp型に変換する
	public static Timestamp conv(String date) {
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Timestamp ts = new Timestamp(sdf.parse(date).getTime());
			return ts;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// Time型をTimestamp型に変換する
	@SuppressWarnings("deprecation")
	public static Timestamp conv(Timestamp timestamp, Time time) {
		
		if (Objects.isNull(time)) new Timestamp(0L);
		
		Timestamp ts = new Timestamp(time.getTime());
		ts.setYear(timestamp.getYear());
		ts.setMonth(timestamp.getMonth());
		ts.setDate(timestamp.getDate());
		return ts;
	}
	
	// Timestamp型から日付をString型で返却する
	public static String getDate(Timestamp date) {
		
		if (Objects.isNull(date)) return null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
		return sdf.format(date);
	}
	
	// Timestamp型から時間をString型で返却する
	public static String getTime(Timestamp date) {
		
		if (Objects.isNull(date)) return null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return sdf.format(date);
	}
	
	// 出勤時間と退勤時間から稼働時間を算出する
	public static String getOperatingHours(Timestamp attendance, Timestamp leaving, Time opening, Time closing, String credit, String rest) {
		
		// 出勤時間または退勤時間がnullの場合
		if (Objects.isNull(attendance) || Objects.isNull(leaving)) return null;
		// 始業時間または終業時間がnullの場合
		if (Objects.isNull(opening) || Objects.isNull(closing)) return null;
		// 単位時間がnullまたはブランクの場合
		if (Objects.isNull(credit) || credit.isEmpty()) return null;
		// 休憩時間がnullまたはブランクの場合
		if (Objects.isNull(rest) || rest.isEmpty()) return null;
		
		Long millisecond = 60L * 60L * 1000L;
		Long attendanceMillisec = operationHoursAdjustment(attendance, opening, credit);
		Long leavingMillisec = operationHoursAdjustment(leaving, closing, credit);
		// 稼働時間(ms) = 退勤時間(ms) - 出勤時間(ms)
		Long operatingHours = leavingMillisec - attendanceMillisec;
		
		double tmp = operatingHours.doubleValue() / millisecond.doubleValue();
		if (tmp > 6) operatingHours = operatingHours - (Long.parseLong(rest) * 60L * 1000L);
		
		String result = String.valueOf(operatingHours.doubleValue() / millisecond.doubleValue());
		if (result.length() >= 6) result = result.substring(0, 5);
		
		return result;
	}
	
	// 出勤時間または退勤時間を単位時間で調整する
	@SuppressWarnings("deprecation")
	public static Long operationHoursAdjustment(Timestamp date, Time time, String credit) {
		
		date.setSeconds(00);
		// (1)始業時間(終業時間)と出勤時間(退勤時間)の差分を算出(ms)
		Long diff = Utility.conv(date, time).getTime() - date.getTime();
		
		// (2) (1)で算出したmsをminutsに変換
		Long min = diff / (60L * 1000L);
		
		// (3) (2)で算出したminuts(分)をcredittime(単位時間:分)で割って剰余(分)を算出
		Long rem = min % Long.parseLong(credit);
		
		// (4) (3)で算出した剰余(分)をmsに変換
		Long remMin = rem * (60L * 1000L);
		
		// (5) (3)で算出した剰余が０の場合は出勤時間(ms)をそのまま返却
		if (rem == 0) return date.getTime();
		// (5) (3)で算出した剰余が０でない場合は出勤時間(ms)から剰余(ms)を加減算したものを返却
		if (rem != 0) return date.getTime() + remMin;
		
		return 0L;
	}
}
