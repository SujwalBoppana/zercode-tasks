package de.zeroco.util;

public class Student implements Comparable<Object> {
	String name;
	int rollNo;
	String phNo;
	int age;

	public Student(String name, int rollNo, String phNo,int age) {
		this.name = name;
		this.rollNo = rollNo;
		this.phNo = phNo;
		this.age = age;
	}

	@Override
	public String toString() {
		return name + "," + rollNo + "," + phNo + "," +age ;
	}

	@Override
	public int compareTo(Object o) {
		int rollNo = this.rollNo;
		Student student = (Student) o;
		int tempRoll = student.rollNo;
		if (rollNo < tempRoll) {
			return -1;
		} else if (rollNo > tempRoll) {
			return +1;
		}
		return 0;
	}

}
