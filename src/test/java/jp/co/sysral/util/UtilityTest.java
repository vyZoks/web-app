package jp.co.sysral.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.junit.Test;

public class UtilityTest {

	@Test
	public void test001() throws Exception {
		
		String credit = "5";
		String rest = "60";
		Time opening = new Time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-04-18 09:00:00").getTime());
		Time closing = new Time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-04-18 18:00:00").getTime());
		Timestamp attendance = new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-04-18 09:00:00").getTime());
		Timestamp leaving = new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-04-18 18:00:00").getTime());
		
		String operatingHours = Utility.getOperatingHours(attendance, leaving, opening, closing, credit, rest);
		
		assertThat(operatingHours, equalTo("8.0"));
	}
	
	@Test
	public void test002() throws Exception {
		
		String credit = "5";
		String rest = "60";
		Time opening = new Time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1970-01-01 09:00:00").getTime());
		Time closing = new Time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1970-01-01 18:00:00").getTime());
		Timestamp attendance = new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-04-18 08:58:00").getTime());
		Timestamp leaving = new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-04-18 18:02:00").getTime());
		
		String operatingHours = Utility.getOperatingHours(attendance, leaving, opening, closing, credit, rest);
		
		assertThat(operatingHours, equalTo("8.0"));
	}

}
