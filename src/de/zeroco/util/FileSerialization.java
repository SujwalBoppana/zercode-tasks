package de.zeroco.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class FileSerialization implements Serializable {

	public static void main(String[] args) {
		StudentDetails student = new StudentDetails("Name", "RollNo", "EmailId", "Phone Number");
		StudentDetails studentOne = new StudentDetails("raju", "102", "raju123@gmail.com", "8919190233");
		StudentDetails studentTwo = new StudentDetails("Manikanta", "103", "manikanta123@gmail.com", "6309638199");
		StudentDetails studentThree = new StudentDetails("Bhanu", "104", "bhanu.k@gmail.com", "9553014360");
		try {
			FileOutputStream file = new FileOutputStream("D:\\files\\temp.txt");
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(student);
			out.writeObject(studentOne);
			out.writeObject(studentTwo);
			out.writeObject(studentThree);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
