package com.writemissmatchfile;
/*
 * This class used to create a deployment match file and write a match
 * properties in it
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.JSONException;
import org.json.JSONObject;

public class WriteDeploymentMatch {

	static File f;
	static JSONObject jfinal = new JSONObject();
	static String product;
	static int cnt;

	public static void writeDeploymentJson(JSONObject obj, String pName, int matcnt) throws IOException, JSONException {

		product = pName;
		cnt = matcnt;

		for (Object key : JSONObject.getNames(obj)) {
			String k1 = (String) key;
			Object val = obj.get(k1);
			jfinal.put(k1, val);
		}

	}

	public JSONObject getDmatchJsonData() {

		return jfinal;

	}

	public File getfile() throws IOException {

		if (cnt != 0) {
			Path path1 = Paths.get("C://JSONResult//Deployment//" + product);

			// if directory exists?
			if (!Files.exists(path1)) {
				Files.createDirectories(path1);
			}
			f = new File(path1 + "/" + product + "-" + "deploymatch_" + cnt + ".json");

			if (!f.exists()) {
				f.createNewFile();
			}
		}
		return f;

	}

	public void clearDmatchJsonData() throws JSONException {
		for (Object ob : JSONObject.getNames(jfinal)) {
			String kr = (String) ob;
			jfinal.remove(kr);
		}
		cnt = 0;
	}
}
