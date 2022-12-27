package de.zeroco.task;

import java.util.Comparator;

public class NameComparator implements Comparator<Student> {
	@Override
	public int compare(Student studentOne, Student studentTwo) {
		return studentOne.getName().compareTo(studentTwo.getName());
	}

}
