package de.zeroco.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import de.zeroco.util.Utility;

public class StudentUtility {
	/**
	 * this method will returns the students having given hobby
	 * @author sujwal b
	 * @since 2022-12-14
	 * @param branchs
	 * @param hobby
	 * @return list
	 */
	public static List<Student> getStudentByHobby(List<Branch> branchs, String hobby) {
		if (Utility.isBlank(branchs) || Utility.isBlank(hobby)) {
			return null;
		}
		List<Student> output = new ArrayList<>();
		for (Branch branch : branchs) {
			for (Student details : branch.getDetails()) {
				if (details.getHobbies().contains(hobby)) {
					output.add(details);
				}
			}
		}
		return output;
	}
	/**
	 * this method will returns the sorted order of given student list
	 * @author sujwal b
	 * @since 2022-12-14
	 * @param students
	 * @return list
	 */
	public static List<Student> getSort(List<Student> students) {
		if (Utility.isBlank(students)) {
			return null;
		}
		Collections.sort(students, new NameComparator());
		return students;
	}

	public static void main(String[] args) {
		List<Student> mechList = new ArrayList<>();
		mechList.add(new Student("Ravi", "mech", "ravi@gmail.com", new ArrayList<String>(Arrays.asList("cricket"))));
		mechList.add(new Student("Raju", "mech", "raju@gmail.com",
				new ArrayList<String>(Arrays.asList("cricket", "kabbadi"))));
		mechList.add(
				new Student("venkat", "mech", "venkat@gmail.com", new ArrayList<String>(Arrays.asList("vollyball"))));
		mechList.add(new Student("Ramu", "mech", "ramu@gmail.com",
				new ArrayList<String>(Arrays.asList("shortput", "baseball"))));
		Branch mech = new Branch("mech", mechList);
		// System.out.println(mech);
		List<Student> civilList = new ArrayList<>();
		civilList.add(new Student("Yash", "civil", "Yash@gmail.com",
				new ArrayList<String>(Arrays.asList("volleyball", "tabletennis"))));
		civilList.add(
				new Student("Vihaan", "civil", "Vihaan@gmail.com", new ArrayList<String>(Arrays.asList("cricket"))));
		civilList.add(new Student("Sai", "civil", "Sai@gmail.com", new ArrayList<String>(Arrays.asList("vollyball"))));
		civilList.add(new Student("Harish", "civil", "Harish@gmail.com",
				new ArrayList<String>(Arrays.asList("cricket", "badminton"))));
		Branch civil = new Branch("civil", civilList);
		List<Student> cseList = new ArrayList<>();
		cseList.add(new Student("Girish", "cse", "Girish@gmail.com",
				new ArrayList<String>(Arrays.asList("volleyball", "chess"))));
		cseList.add(new Student("Raju", "cse", "raju@gmail.com", new ArrayList<String>(Arrays.asList("boxing"))));
		cseList.add(new Student("nani", "cse", "nani@gmail.com", new ArrayList<String>(Arrays.asList("tennis"))));
		cseList.add(new Student("Ramu", "cse", "ramu@gmail.com",
				new ArrayList<String>(Arrays.asList("cricket", "kabbadi"))));
		Branch cse = new Branch("cse", cseList);
		// System.out.println(civil);
		List<Branch> branch = new ArrayList<Branch>();
		branch.add(mech);
		branch.add(civil);
		branch.add(cse);
		// System.out.println(branch);
		
	//	System.out.println(getStudentByHobby(branch, "cricket"));
		for (Student details :getSort( getStudentByHobby(branch, "cricket"))) {
			System.out.println(details);
		}
		System.out.println("----------------");
		for (Student details : getStudentByHobby(branch, "cricket")) {
			System.out.println(details);
		}

	}
}
