package com.writemissmatchfile;
/*
 * This class used to create a deployment missing file and write a missing
 * properties in it
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.JSONException;
import org.json.JSONObject;

public class WriteDeploymentMissing {
	static File f;
	static JSONObject jfinal = new JSONObject();
	static String product;
	static int cnt;

	public static void DeployMissing(JSONObject obj, String pName, int miscnt) throws IOException, JSONException {
		product = pName;
		cnt = miscnt;

		for (Object key : JSONObject.getNames(obj)) {
			String k1 = (String) key;
			Object val = obj.get(k1);
			jfinal.put(k1, val);
		}

	}

	public JSONObject getDmissJsonData() {

		return jfinal;

	}

	public File getfile() throws Exception {

		// file
		if (cnt != 0) {
			Path path1 = Paths.get("C://JSONResult//Deployment//" + product);
			// if directory exists?
			if (!Files.exists(path1)) {
				Files.createDirectories(path1);
			}
			f = new File(path1 + "/" + product + "-" + "deploymiss_" + cnt + ".json");

			if (!f.exists()) {
				f.createNewFile();
			}
		}
		return f;

	}

	public void clearDmissJsonData() throws JSONException {
		for (Object ob : JSONObject.getNames(jfinal)) {
			String kr = (String) ob;
			jfinal.remove(kr);
		}
	}
}
