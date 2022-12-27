package de.zeroco.util;

import java.util.Comparator;

public class NameComparator implements Comparator<Student>{
	@Override
	public int compare(Student studentOne, Student studentTwo) {
		return studentOne.compareTo(studentTwo);
	}

}
