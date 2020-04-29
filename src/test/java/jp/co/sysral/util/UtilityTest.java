package jp.co.sysral.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import jp.co.sysral.bean.Attend;

public class UtilityTest {

	private Attend attend;
	
	@Before
	public void setup() {
		this.attend = new Attend();
	}
	
	@Test
	@Ignore
	public void test001() throws Exception {
		
		String credit = "5";
		this.attend.setActualRestTime("60");
		this.attend.setAttendanceTime(new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-04-18 09:00:00").getTime()));
		this.attend.setLeavingTime(new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-04-18 18:00:00").getTime()));
		
		String operatingHours = Utility.getOperatingHours(this.attend, credit);
		
		assertThat(operatingHours, equalTo("8.0"));
	}
	
	@Test
	public void test002() throws Exception {
		
		String credit = "5";
		this.attend.setActualRestTime("60");
		this.attend.setAttendanceTime(new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-04-18 09:20:00").getTime()));
		this.attend.setLeavingTime(new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-04-18 17:45:00").getTime()));
		
		String operatingHours = Utility.getOperatingHours(this.attend, credit);
		
		assertThat(operatingHours, equalTo("7.416"));
	}

}
