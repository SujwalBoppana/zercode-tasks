package de.zeroco.task;

import java.util.List;

public class Branch {
	String name;
	List<Student> details;

	public Branch(String name, List<Student> details) {
		this.name = name;
		this.details = details;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Student> getDetails() {
		return details;
	}

	public void setDetails(List<Student> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "Branch [name=" + name + ", details=" + details + "]";
	}

}
