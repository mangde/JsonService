package com.checkfiletype;
/*
 * This class is used to check the file type(.property or xml) and call the respective read method
 */

import com.readxmlandproperty.ReadPropertyFile;
import com.readxmlandproperty.ReadXmlFile;

public class CheckFileType {

	public void checkType(String path, String productName, String deployfilename, String envfilepath) throws Throwable {

		String tp = getFileExtension(path);

		if (tp.equals("properties")||tp.equals("config")) {

			ReadPropertyFile rpf = new ReadPropertyFile();
			rpf.readPropertyFile(path, productName, deployfilename, envfilepath);
		}

		if (tp.equals("xml")) {

			ReadXmlFile rxf = new ReadXmlFile();
			rxf.readXmlFile(path, productName, deployfilename, envfilepath);
		}
	}

	private static String getFileExtension(String fileName) {
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		else
			return "";
	}

}
