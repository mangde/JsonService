package com.readxmlandproperty;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.json.XML;

import com.comparejsonfile.CompareDeploymentJson;
import com.comparejsonfile.CompareEnvironmentJson;
import com.findfromjar.FindJarEntry;

public class ReadXmlFile {

	static String str = "";

	CompareDeploymentJson djson = new CompareDeploymentJson();
	CompareEnvironmentJson rjson2 = new CompareEnvironmentJson();

	// method to Read Xml file format
	public void readXmlFile(String path, String productname, String deployfile, String envfilepath) throws IOException {

		try {
			File fp = new File(path);

			if (path.contains(".jar") || path.contains(".war")) {

				String fname = fp.getAbsoluteFile().getName();

				String path1 = fp.getParent();

				File filepath = FindJarEntry.main1(path, path1, fname);

				str = FileUtils.readFileToString(filepath);

			} else {
				str = FileUtils.readFileToString(fp);
			}

			JSONObject jsondata = XML.toJSONObject(str);
			
			if(deployfile.length()>5)
			djson.compareDepjson(jsondata.toString(), productname, deployfile);
			
			if(envfilepath.length()>5)
			rjson2.compareEnvjson(jsondata.toString(), productname, envfilepath);

		} catch (Throwable e) {
			e.printStackTrace();
		}

	}
}
