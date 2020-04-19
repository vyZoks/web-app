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
	
	// Timestamp型から日付をString型で返却する
	public static String getDate(Timestamp date) {
		
		if(Objects.isNull(date)) return null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
		return sdf.format(date);
	}
	
	// Timestamp型から時間をString型で返却する
	public static String getTime(Timestamp date) {
		
		if(Objects.isNull(date)) return null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return sdf.format(date);
	}
	
	// 出勤時間と退勤時間から稼働時間を算出する
	public static String getOperatingHours(Timestamp attendance, Timestamp leaving, Time opening, Time closing, String credit, String rest) {
		
		// 実出勤時間または実退勤時間がnullの場合
		if(Objects.isNull(attendance) || Objects.isNull(leaving)) return null;
		// 出勤時間または退勤時間がnullの場合
		if(Objects.isNull(opening) || Objects.isNull(closing)) return null;
		// 単位時間がnullまたはブランクの場合
		if(Objects.isNull(credit) || credit.isEmpty()) return null;
		// 休憩時間がnullまたはブランクの場合
		if(Objects.isNull(rest) || rest.isEmpty()) return null;
		
		Long millisecond = 60L * 60L * 1000L;
		Long attendanceMillisec = operationHoursAdjustment(attendance, opening, credit);
		Long leavingMillisec = operationHoursAdjustment(leaving, closing, credit);
		// 退勤時間(ミリ秒) - 出勤時間(ミリ秒) - 休憩時間(ミリ秒)
		Long operatingHours = leavingMillisec - attendanceMillisec - (Long.parseLong(rest) * 60L * 1000L);
		
		// 稼働時間(ミリ秒) / 1時間(ミリ秒) = 稼働時間(時間)
		return String.valueOf(operatingHours.doubleValue() / millisecond.doubleValue());
	}
	
	// 出勤時間または退勤時間から単位時間外の時間を調整する
	public static Long operationHoursAdjustment(Timestamp date, Time time, String credit) {
		
		// 開始時刻(終了時刻)の差分(ミリ秒)を算出
		Long diff = time.getTime() - date.getTime();
		System.out.println("diff:" + diff);
		
		// ミリ秒(差分)を分に変換
		Long min = diff / (60L * 1000L);
		System.out.println("min:" + min);
		
		// 分 % 単位時間(分)で剰余(分)を算出
		Long rem = min % Long.parseLong(credit);
		System.out.println("rem:" + rem);
		
		// 分(剰余)をミリ秒に変換
		Long remMin = rem * (60L * 1000L);
		System.out.println("remMin:" + remMin);
		
		// 剰余が０の場合は実稼働時間(ミリ秒)をそのまま返却
		if (rem == 0) return date.getTime();
		// 剰余が０でない場合は実稼働時間から剰余を加減算したものを返却
		if (rem != 0) return date.getTime() + remMin;
		
		return 0L;
	}
}
