package jp.co.sysral.bean;

import java.sql.Timestamp;

public class Attend {

	private int atendId;
	private Timestamp punchIn;
	private Timestamp punchOut;
	private String realBreakTime;
	private String operatingTime;
	private String location;
	private int empId;
	
	public int getAtendId() {
		return atendId;
	}
	public void setAtendId(int atendId) {
		this.atendId = atendId;
	}
	public Timestamp getPunchIn() {
		return punchIn;
	}
	public void setPunchIn(Timestamp punchIn) {
		this.punchIn = punchIn;
	}
	public Timestamp getPunchOut() {
		return punchOut;
	}
	public void setPunchOut(Timestamp punchOut) {
		this.punchOut = punchOut;
	}
	public String getRealBreakTime() {
		return realBreakTime;
	}
	public void setRealBreakTime(String realBreakTime) {
		this.realBreakTime = realBreakTime;
	}
	public String getOperatingTime() {
		return operatingTime;
	}
	public void setOperatingTime(String operatingTime) {
		this.operatingTime = operatingTime;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	
}
