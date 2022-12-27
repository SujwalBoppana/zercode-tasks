package de.zeroco.util;

import java.io.Serializable;


public class StudentDetails implements Serializable{
	public String name;
	public String rollNo;
	public String emailId;
	public String phNo;

	/**
	 * this constructor will initialize the variables
	 * @author Sujwal B
	 * @since 2022-12-05
	 * @param name
	 * @param rollNo
	 * @param emailId
	 * @param phno
	 */
	public StudentDetails(String name, String rollNo,String emailId, String phNo) {
		this.name = name;
		this.rollNo = rollNo;
		this.emailId = emailId;
		this.phNo = phNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhNo() {
		return phNo;
	}

	public void setPhNo(String phNo) {
		this.phNo = phNo;
	}

	@Override
	public String toString() {
		return  name + ","  + rollNo + "," + phNo + "," + emailId;
	}

	

}
