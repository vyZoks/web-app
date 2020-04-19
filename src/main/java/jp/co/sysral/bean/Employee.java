package jp.co.sysral.bean;

import java.sql.Time;

public class Employee {

	private int empId;
	private String empName;
	private String mailaddress;
	private String empPass;
	private Time openingTime;
	private Time closingTime;
	private String creditTime;
	private String restTime;
	private boolean attendFlag;
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getMailaddress() {
		return mailaddress;
	}
	public void setMailaddress(String mailaddress) {
		this.mailaddress = mailaddress;
	}
	public String getEmpPass() {
		return empPass;
	}
	public void setEmpPass(String empPass) {
		this.empPass = empPass;
	}
	public Time getOpeningTime() {
		return openingTime;
	}
	public void setOpeningTime(Time openingTime) {
		this.openingTime = openingTime;
	}
	public Time getClosingTime() {
		return closingTime;
	}
	public void setClosingTime(Time closingTime) {
		this.closingTime = closingTime;
	}
	public String getCreditTime() {
		return creditTime;
	}
	public void setCreditTime(String creditTime) {
		this.creditTime = creditTime;
	}
	public String getRestTime() {
		return restTime;
	}
	public void setRestTime(String restTime) {
		this.restTime = restTime;
	}
	public boolean isAttendFlag() {
		return attendFlag;
	}
	public void setAttendFlag(boolean attendFlag) {
		this.attendFlag = attendFlag;
	}
	
}
