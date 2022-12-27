package de.zeroco.util;

import java.util.Comparator;

public class AgeComparator implements Comparator<Student> {

	@Override
	public int compare(Student studentOne, Student studentTwo) {
		if (studentOne.age == studentTwo.age)
			return 0;
		else if (studentOne.age > studentTwo.age)
			return 1;
		else
			return -1;
	}
}
