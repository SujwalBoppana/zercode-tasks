package de.zeroco.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GenerateLicense {
	final static String SOURCE = "D:\\files\\lisence.json";

	/**
	 * this method will return the data present inside the file
	 * @author sujwal b
	 * @since 2022-12-16
	 * @param source
	 * @return
	 * @throws IOException
	 */
	public static String getData(String source) throws IOException {
		return new String(Files.readAllBytes(Paths.get(source)));
	}

	/**
	 * this method will generates the files 
	 * @param client
	 * @param from
	 * @param to
	 * @param desti
	 * @throws IOException
	 */
	public static String generateLicense(String client, int from, int to, String desti) throws IOException {
		if (Utility.isBlank(client) || (Utility.isBlank(from) && Utility.isBlank(to))) {
			return null;
		}
		String data = getData(SOURCE);
		client=client.toLowerCase();
		desti = Utility.isBlank(desti)?FilesUtil.getDirectory(System.getProperty("java.io.tmpdir")).getAbsolutePath():FilesUtil.getDirectory(desti).getAbsolutePath();
		for (int i = from; i <= to; i++) {
			String fileName = i >= 0 && i <= 9 ? client + "0" + i : client + i;
			FilesUtil.writeData(desti + File.separator + (FilesUtil.getFile(fileName + ".zc_lic")).getName(),
					data.replaceAll("\\$([a-zA-Z]*)\\$", fileName));
		}
		return desti;
	}
	public static String generateLicense(List<String>names,String desti) throws IOException {
		String data="";
		for (String string : names) {
			data=generateLicense(string, 1, 1, desti);
		}
		return data;
		
	}

	public static void main(String[] args) throws IOException {
		List<String>names = new ArrayList<>();
		names.add("Sujwal");
		System.out.println(generateLicense(names, "D:\\temp"));
		//System.out.println(generateLicense("sujwal", 0, 1, "D:\\temp"));
	}

}
