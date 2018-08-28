package com.readxmlandproperty;
/*
 * This class is used to accept the property file path and converted that property file into the
 * JSON file and call for comparison methods for further processing 
 */

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import org.codehaus.jackson.map.ObjectMapper;

import com.comparejsonfile.CompareDeploymentJson;
import com.comparejsonfile.CompareEnvironmentJson;
import com.findfromjar.FindJarEntry;

public class ReadPropertyFile {

	File filepath;
	String jsondata;
	CompareDeploymentJson djson = new CompareDeploymentJson();
	CompareEnvironmentJson ejson = new CompareEnvironmentJson();

	// Method for read properties file
	public void readPropertyFile(String fullpath, String productname, String deployfile, String envfilepath)
			throws Throwable {
		ObjectMapper mapper = new ObjectMapper();
		Properties props = new Properties();

		File fp = new File(fullpath);

		if (fullpath.contains(".jar") || fullpath.contains(".war")) {

			String fname = fp.getAbsoluteFile().getName();
			String fpath = fp.getParent();

			filepath = FindJarEntry.main1(fullpath, fpath, fname);

			FileReader reader = new FileReader(filepath);
			props.load(reader);
			jsondata = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(props);

		} else {
			FileReader reader = new FileReader(fp);
			props.load(reader);
			jsondata = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(props);

		}

		if(deployfile.length()>5)
		djson.compareDepjson(jsondata, productname, deployfile);

		if(envfilepath.length()>5){
		   ejson.compareEnvjson(jsondata, productname, envfilepath);
		}
	}

}
