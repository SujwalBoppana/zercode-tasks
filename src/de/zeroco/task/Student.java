package de.zeroco.task;

import java.util.List;

public class Student {
	String name;
	String branch;
	String email;
	List<String> hobbies;
	public Student(String name, String branch, String email, List<String> hobbies) {
		this.name = name;
		this.branch = branch;
		this.email = email;
		this.hobbies = hobbies;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getemail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", branch=" + branch + ", email=" + email + ", hobbies=" + hobbies + "]";
	}
}

