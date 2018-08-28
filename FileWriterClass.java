package com.writemissmatchfile;

/*
 * This class is a common Writer class for all file writing operation
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

public class FileWriterClass {

	JSONObject jobj1;
	JSONObject jb;
	ObjectMapper mapper = new ObjectMapper();
	String key;
	BufferedWriter bw;

	public void writerForAll(JSONObject jobj, File p) throws IOException, JSONException {

		try {
			bw = new BufferedWriter(new FileWriter(p.getAbsoluteFile(), false));

			if (p.getAbsoluteFile().length() == 0) {
				bw.write("{");
				if (p.getAbsolutePath().contains("Deployment")) {
					bw.write('"');

					bw.append("deploymentProperties");
					bw.append('"');
					bw.append(':');

				} else {
					bw.write('"');
					bw.append("envProperties");
					bw.append('"');
					bw.append(':');
				}

				bw.write(jobj.toString());
				bw.flush();
				bw.write("}"); 
				bw.close();

			} 

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		 
		 
	}// method

}
