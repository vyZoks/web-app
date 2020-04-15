package jp.co.sysral.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Utility {

	public static Timestamp conv(String dateTime) {
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Timestamp ts = new Timestamp(sdf.parse(dateTime).getTime());
			return ts;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
