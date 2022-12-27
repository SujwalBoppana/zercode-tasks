package de.zeroco.util;

public class EmployeeDetails {
	String employeeName;
	String employeeId;
	String emailId;
	String phno;
	String address;

	public EmployeeDetails(String employeeName, String employeeId, String emailId, String phno, String address) {
		this.employeeName = employeeName;
		this.employeeId = employeeId;
		this.emailId = emailId;
		this.phno = phno;
		this.address = address;
	}

	@Override
	public String toString() {
		return "employeeName=" + employeeName  + ", emailId=" + emailId
				+ ", phno=" + phno + ", address=" + address + " ";
	}
	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	

}
