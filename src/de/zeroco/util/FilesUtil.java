package de.zeroco.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class FilesUtil {
	/**
	 * this method will creates a new file
	 * @author sujwal b
	 * @since 2022-12-05
	 * @param fileName
	 * @return status
	 * @throws FileNotFoundException 
	 * @throws IOException
	 */
	public static File getFile(String fileName) {
		if (Instance.isBlank(fileName)) {
			return null;
		}
		File file = new File(fileName);
		try {
			if (file.exists()) {
				if(file.isDirectory()) {
					throw new Exception("It is a directory");
				}
				return file;
			}
			file.createNewFile();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}
	/**
	 * This method will creates a new directory 
	 * @author sujwal b
	 * @since 2022-12-13
	 * @param fileName
	 * @return file
	 */
	public static File getDirectory(String fileName) {
		if (Instance.isBlank(fileName)) {
			return null;
		}
		File file = new File(fileName);
		try {
			if (file.exists()) {
				if(file.isDirectory()) {
					return file;
				}
			}
			file.mkdir();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}

	/**
	 * this method  will writes the data into the given file
	 * @author sujwal b
	 * @since 2022-12-05
	 * @param fileName
	 * @param data
	 * @return String
	 * @throws IOException 
	 */
	public static File writeData(String fileName, String data)  {
		if (Instance.isBlank(fileName)||Instance.isBlank(data)) {
			return null;
		}
		File file = getFile(fileName);
		BufferedWriter writerOne = null;
		try {
			writerOne = new BufferedWriter(new FileWriter(file));
			writerOne.write(data);
			writerOne.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writerOne.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}

	/**
	 * this method will append data to the existing file
	 * @author sujwal b
	 * @since 2022-12-05
	 * @param fileName
	 * @param data
	 * @return file name
	 * @throws IOException
	 */
	public static File appendData(String fileName, String data)   {
		if (Instance.isBlank(fileName)||Instance.isBlank(data)) {
			return null;
		}
		File file = getFile(fileName);
		BufferedWriter writer=null;
		try {
			 writer = new BufferedWriter(new FileWriter(file, true));
			writer.newLine();
			writer.write(data);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			 try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}
	
	/**
	 * this method will reads the data present inside the the file
	 * @author sujwal B
	 * @since 2022-12-05
	 * @param fileName
	 * @return data
	 * @throws IOException
	 */
	public static String [] readData(String fileName)  {
		if (Instance.isBlank(fileName)) {
			return null;
		}
		String data = "";
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String temp = null;
			while ((temp = reader.readLine()) != null) {
				data = data + "\n" + temp;
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return data.split(",");
	}
	
	/**
	 * this method will delete the particular line with given input matches it
	 * @author sujwal B
	 * @since 2022-12-06
	 * @param fileName
	 * @param removeData
	 * @return
	 * @throws IOException
	 */
	public static File removeData(String fileName, String removeData)  {
		if (Instance.isBlank(fileName) || Instance.isBlank(removeData)) {
			return null;
		}
		int line = 1;
		int lineNo = 0;
		File tempFile = getFile("temp.csv");
		File file = getFile(fileName);
		BufferedReader reader=null;
		BufferedWriter writer=null;
		try {
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter(tempFile));
			String temp = null;
			while ((temp = reader.readLine()) != null) {
				String[] details = temp.split(",");
				for (String data : details) {
					if (data.equalsIgnoreCase(removeData)) {
						lineNo = line;
						break;
					}
				}
				if (lineNo != line) {
					writer.write(temp);
					writer.newLine();
				}
				line++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				writer.close();
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		file.delete();
		tempFile.renameTo(file);
		return file;
	}
	
	/**
	 * this method will removes the particular line by taking input as line Number
	 * @author sujwal B
	 * @since 2022-12-06
	 * @param fileName
	 * @param lineNo
	 * @return
	 * @throws IOException
	 */
	public static File removeLine(String fileName, int lineNo) throws IOException {
		if (Instance.isBlank(fileName) || Instance.isBlank(lineNo)) {
			return null;
		}
		int line = 1;
		File tempFile = getFile("temp.csv");
		File file = getFile(fileName);
		BufferedReader reader=null;
		BufferedWriter writer=null;
		try {
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter(tempFile));
			String temp = null;
			while ((temp = reader.readLine()) != null) {
				if (lineNo != line) {
					writer.write(temp);
					writer.newLine();
				}
				line++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				writer.close();
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		file.delete();
		tempFile.renameTo(file);
		return file;
	}
	public static void main(String[] args) throws Exception {
		System.out.println(getFile("D:\\files\\temp.txt"));
		StudentDetails student = new StudentDetails("Name", "RollNo", "EmailId", "Phone Number");
		StudentDetails studentOne = new StudentDetails("raju", "102", "raju123@gmail.com", "8919190233");
		StudentDetails studentTwo = new StudentDetails("Manikanta", "103", "manikanta123@gmail.com", "6309638199");
		StudentDetails studentThree = new StudentDetails("Bhanu", "104", "bhanu.k@gmail.com", "9553014360");
		System.out.println(getFile("D:\\student.csv"));
		String data = student.toString();
		String dataOne = studentOne.toString();
		String dataTwo = studentTwo.toString();
		String dataThree = studentThree.toString();
		String fileName = "D:\\student.csv";
	try {
			writeData(fileName, data);
			appendData(fileName, dataOne);
			appendData(fileName, dataTwo);
			appendData(fileName, dataThree);
			appendData(fileName, dataTwo);
			System.out.println(Arrays.toString(readData(fileName)));
//			System.out.println(getCopy("D:\\student.csv", "D:\\data.csv"));
//			System.out.println(removeData(fileName, "raju"));
			//System.out.println(removeLine(fileName, 1));
			System.out.println(getWordCount("D:\\first.java"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	/**
	 * this method will gives a copy 
	 * @author sujwal b
	 * @since 2022-12-06
	 * @param fileName
	 * @param newFileName
	 * @return
	 * @throws IOException
	 */
	public static File getCopy(String fileName, String newFileName) throws IOException {
		if (Instance.isBlank(fileName) || Instance.isBlank(newFileName)) {
			return null;
		}
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		File file = getFile(newFileName);
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		try {

			String temp = null;
			while ((temp = reader.readLine()) != null) {
				writer.write(temp);
				writer.newLine();
			}
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			writer.close();
			reader.close();
		}

		return file;
	}
	
	public static int getWordCount(String fileName) {
		if (Instance.isBlank(fileName)) {
			return 0;
		}

		int count =0;
		
		try {
			BufferedReader reader =new  BufferedReader(new FileReader(getFile(fileName)));
			String temp = null;
			while ((temp = reader.readLine()) != null) {
				String [] tempArray = temp.split(" ");
				for (String string : tempArray) {
					if (string.equals(" ")) {
						continue;
					}
					count++;
				}
				
			}
			
		} catch ( IOException e) {
					e.printStackTrace();
		}
		return count;
		
	}

}
