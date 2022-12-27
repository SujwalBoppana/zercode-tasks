package de.zeroco.testing;

import java.util.Set;
import java.util.TreeSet;

import de.zeroco.util.AgeComparator;
import de.zeroco.util.NameComparator;
import de.zeroco.util.Student;

public class StudentTesting {
	public static void main(String[] args) {
		Set<Student> set = new TreeSet<>();
		set.add(new Student("sujwal", 302, "6309638199",16));
		set.add( new Student("Abhi", 305, "8919789898",17));
		set.add( new Student("ramu", 304, "78945451254",15));
		System.out.print("by roll no : ");
		System.out.println(set);
		Set<Student> setOne = new TreeSet<>(new NameComparator());
		setOne.add(new Student("sujwal", 302, "6309638199",16));
		setOne.add( new Student("Abhi", 301, "8919789898",17));
		setOne.add( new Student("ramu", 304, "78945451254",15));
		System.out.print("by name  : ");
		System.out.println(setOne);
		Set<Student> setTwo = new TreeSet<>(new AgeComparator());
		setTwo.add(new Student("sujwal", 302, "6309638199",16));
		setTwo.add( new Student("Abhi", 301, "8919789898",17));
		setTwo.add( new Student("ramu", 304, "78945451254",15));
		System.out.print("by age : ");
		System.out.println(setTwo);
	}
}
