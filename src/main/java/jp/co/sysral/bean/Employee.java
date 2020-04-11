package jp.co.sysral.bean;

import java.sql.Time;

public class Employee {

	private int empId;
	private String empName;
	private String mailaddress;
	private String empPass;
	private Time startWork;
	private Time endWork;
	private String unitTime;
	private String breakTime;
	private String accessId;
	private boolean atendFlag;
	
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
	public Time getStartWork() {
		return startWork;
	}
	public void setStartWork(Time startWork) {
		this.startWork = startWork;
	}
	public Time getEndWork() {
		return endWork;
	}
	public void setEndWork(Time endWork) {
		this.endWork = endWork;
	}
	public String getUnitTime() {
		return unitTime;
	}
	public void setUnitTime(String unitTime) {
		this.unitTime = unitTime;
	}
	public String getBreakTime() {
		return breakTime;
	}
	public void setBreakTime(String breakTime) {
		this.breakTime = breakTime;
	}
	public String getAccessId() {
		return accessId;
	}
	public void setAccessId(String accessId) {
		this.accessId = accessId;
	}
	public boolean isAtendFlag() {
		return atendFlag;
	}
	public void setAtendFlag(boolean atendFlag) {
		this.atendFlag = atendFlag;
	}
	
}
