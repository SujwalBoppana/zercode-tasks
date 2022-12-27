package de.zeroco.testing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import de.zeroco.util.EmployeeDetails;

public class EmployeeDetailsTesting {
	public static void main(String[] args) {
		List<EmployeeDetails> employeelist = new ArrayList<EmployeeDetails>();
		employeelist.add(new EmployeeDetails("ravi","12341", "ravi123@gmail.com", "6309638199", "Gudivada"));
		employeelist.add(new EmployeeDetails("raju","12321", "raju123@gmail.com", "7309638199", "vijayawada"));
		employeelist.add(new EmployeeDetails("ramu","12352", "ramu123@gmail.com", "972345673", "hyderabad"));
		employeelist.add(new EmployeeDetails("ramu","12355", "ramu123@gmail.com", "972345673", "hyderabad"));
	//	System.out.println(employeelist);
		Collections.sort(employeelist,new EidComparator());
		Map<String, EmployeeDetails> map = new TreeMap<String, EmployeeDetails>();
		for (EmployeeDetails employeeDetails : employeelist) {
			map.put(employeeDetails.getEmployeeId(), employeeDetails);
		}
		System.out.println(map);
		//System.out.println(map.get("12341"));
	}
	
}
	class EidComparator implements Comparator<EmployeeDetails> {
		@Override
		public int compare(EmployeeDetails employeeDetails, EmployeeDetails employeeDetailsTwo) {
			return employeeDetailsTwo.getEmployeeId().compareTo(employeeDetails.getEmployeeId());
		}


}

