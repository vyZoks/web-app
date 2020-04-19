package jp.co.sysral.bean;

import java.sql.Timestamp;

public class Attend {

	private int attendId;
	private Timestamp attendanceTime;
	private Timestamp leavingTime;
	private String actualRestTime;
	private String operatingHours;
	private String location;
	private int empId;
	
	public int getAttendId() {
		return attendId;
	}
	public void setAttendId(int attendId) {
		this.attendId = attendId;
	}
	public Timestamp getAttendanceTime() {
		return attendanceTime;
	}
	public void setAttendanceTime(Timestamp attendanceTime) {
		this.attendanceTime = attendanceTime;
	}
	public Timestamp getLeavingTime() {
		return leavingTime;
	}
	public void setLeavingTime(Timestamp leavingTime) {
		this.leavingTime = leavingTime;
	}
	public String getActualRestTime() {
		return actualRestTime;
	}
	public void setActualRestTime(String actualRestTime) {
		this.actualRestTime = actualRestTime;
	}
	public String getOperatingHours() {
		return operatingHours;
	}
	public void setOperatingHours(String operatingHours) {
		this.operatingHours = operatingHours;
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
