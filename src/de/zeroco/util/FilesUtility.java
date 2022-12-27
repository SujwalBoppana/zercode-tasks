package de.zeroco.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FilesUtility {
	/**
	 * this method will creates a new file
	 * @author sujwal b
	 * @since 2022-12-05
	 * @param fileName
	 * @return status
	 * @throws IOException
	 */
	public static File getFile(String fileName)  {
		File file = new File(fileName);
		if (file.exists()) {
			return file;
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

	/**
	 * this method will creates a new directory
	 * @author sujwal b
	 * @since 2022-12-05
	 * @param directory
	 * @return status
	 * @throws IOException
	 */
	public static File getDirectory(String directory) {
		File file = new File(directory);
		if (file.exists()) {
			return file;
		}
		file.mkdir();
		return file;
	}
	
	public static void main(String[] args) throws IOException{
		System.out.println(getFile("F://student.csv"));
		System.out.println(getLettersCount("D:\\Practice\\fileOne.txt"));
	//	System.out.println(getDirectory(directory));
	}
	
	/**
	 * this method will gives the word count
	 * @author sujwal b
	 * @since 2022-12-05
	 * @param fileName
	 * @return String
	 * @throws IOException
	 */
	public static String getLettersCount(String fileName) {
		int count = 0;
		try {
			FileReader readerOne = new FileReader(fileName);

			int temp = readerOne.read();
			while (temp != -1) {
				if ((char) temp != ' ') {
					count++;
				}
				temp = readerOne.read();
			}
			readerOne.close();

		} catch (IOException e) {
			return "File not found";
		}

		return "Count is :" + count;
	}
	
	/**
	  * this method will add the data to the file
	 * @author sujwal b
	 * @since 2022-12-05 
	 * @param fileName
	 * @param data
	 * @return String
	 */
	public static String addData(String fileName, String data) {
		try {
			BufferedWriter writerOne = new BufferedWriter(new FileWriter(fileName));
			writerOne.write(data);
			writerOne.flush();
			writerOne.close();
		} catch (IOException e) {
			return "file not found";
		}
		finally {
		//	writerOne.close();
		}
		return "Added successful ";

	}
	
	/**
	 * this method will add the data to the existing file
	 * @author sujwal b
	 * @since 2022-12-05
	 * @param fileName
	 * @param data
	 * @return status
	 */
	public static String appendData(String fileName, String data) {
		try {
			BufferedWriter writerTwo = new BufferedWriter(new FileWriter(fileName, true));
			writerTwo.append(data);
			writerTwo.flush();
			writerTwo.close();
		} catch (Exception e) {
			return "Error occurred";
		}
		return "Added successful";
	}
	
	/**
	 * this method will add the data to the existing file
	 * @author sujwal b
	 * @since 2022-12-05
	 * @param fileName
	 * @return
	 */
	public static String deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.exists()) {
			file.delete();
			return "Deleted Successful";
		}
		return "File Not Exist";
	}
	public static boolean isBlank(Object input) {
		if(input==null) 
			return true;
		else if (input instanceof String) {
			String variable = (String) input;
			if ((variable.trim().equals(""))) 
				return true;
		} else if (input instanceof Boolean) {
			if ((Boolean) input == false)
				return true;
		} else if (input instanceof Character) {
			if ((Character) input == ' ') 
				return true;
		} else if (input instanceof Byte) {
			if ((Byte) input <= 0)
				return true;
		} else if (input instanceof Short) {
			if ((Short) input <= 0) 
				return true;
		} else if (input instanceof Integer) {
			if ((Integer) input <= 0) 
				return true;
		}  else if (input instanceof Long) {
			if ((Long) input <= 0) 
				return true;
		} else if (input instanceof Float) {
			if ((Float) input <= 0)
				return true;
		} else if (input instanceof Double) {
			if ((Double) input <= 0)
				return true;
		} 
		return false;
	}


}
