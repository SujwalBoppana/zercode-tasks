package de.zeroco.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class FileDeserialization implements Serializable{
	public static void main(String[] args) {
		StudentDetails temp = null;
		StudentDetails tempOne = null;
		StudentDetails tempTwo = null;
		StudentDetails tempThree = null;
		
		try {
			FileInputStream file = new FileInputStream("D:\\files\\temp.txt");
			ObjectInputStream input = new ObjectInputStream(file);
			temp = (StudentDetails) input.readObject();
			tempOne = (StudentDetails) input.readObject();
			tempTwo = (StudentDetails) input.readObject();
			tempThree = (StudentDetails) input.readObject();
			input.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(temp.toString());
		System.out.println(tempOne.toString());
		System.out.println(tempTwo.toString());
		System.out.println(tempThree.toString());
	}

}
