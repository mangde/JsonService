package com.util;

/*
 * This class accept the file name from user and return to respective calling of method from main class
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetFileNameFromUser {
	BufferedReader reader;
	String str,envfile, metafile,depfile;


	// Method that Return Environment JSONFile path
	public String getEnvironmentConfigJson(String str) {
		 reader = new BufferedReader(new InputStreamReader(System.in));
		  System.out.println("If you do not want to compare with Environment/Deployment press Enter for next");


		  System.out.println("\n Enter the Environment file path of "+ str +"  ");

		  try { envfile = reader.readLine();
		  
		  } catch (IOException e) { e.printStackTrace(); }
		 
		if (envfile.contains(".jar") || envfile.contains(".war")) {
			File fp = new File(envfile);
			str = fp.getAbsolutePath().substring(fp.getAbsolutePath().lastIndexOf("\\") + 1);
			return str;

		} else 
			return envfile;

	}
	
	//Method which return a deployment file path
	public String getDeployment(String product) {
		  reader = new BufferedReader(new InputStreamReader(System.in));

		  System.out.println("\n Enter the Deployment file path of "+ product +"");
		  try { depfile = reader.readLine();
		  
		  } catch (IOException e) { e.printStackTrace(); }		 

		if (depfile.contains(".jar") || depfile.contains(".war")) {
			File fp = new File(depfile);
			str = fp.getAbsolutePath().substring(fp.getAbsolutePath().lastIndexOf("\\") + 1);
			return str;

		} else
			return depfile;

	}

	/*// Method that return JSONMetadata file name/ path
	public String getJsonMetaDataFile() {
		
		 reader = new BufferedReader(new InputStreamReader(System.in));
		  System.out.
		  println("\n Enter the Json MetaDataFile file path location  " );
		
		  try { 
			  metafile = reader.readLine();
		  
		  } catch (IOException e) { e.printStackTrace(); }
		
		if (metafile.contains(".jar") || metafile.contains(".war")) {
			File fp = new File(metafile);
			str = fp.getAbsolutePath().substring(fp.getAbsolutePath().lastIndexOf("\\") + 1);
			return str;

		} else
			return metafile;

	}*/

	

	

}
