package de.zeroco.util;

import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
interface Addition{
	public int add(int first,int second);
}

public class LambdaExpressionUtility {
	public static void main(String[] args) {
		Addition sum = (first,second)->{return first+second;};
		System.out.println(sum.add(20, 30));
		 List<String> list=new ArrayList<String>();  
	        list.add("ankit");  
	        list.add("mayank");  
	        list.add("irfan");  
	        list.add("jai");  
	        
	        list.forEach((n)->System.out.println(n));
	}

}
