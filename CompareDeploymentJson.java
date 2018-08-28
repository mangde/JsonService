package com.comparejsonfile;
/*
 * This class Having a function of Read a Deployment-JSON And 
 * find out that the deployment JSON contain that property of MetaDataJSON File or not
 * if he contain that property call the match-function else Missing-function
 * of another class
 */

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.writemissmatchfile.WriteDeploymentMatch;
import com.writemissmatchfile.WriteDeploymentMissing;

public class CompareDeploymentJson {

	JSONObject jsonMasterObj;
	JSONObject jsonLocalObj;
	boolean flag;
	String localKey;
	
	static int  miscnt;
 	static int matcnt;
	@SuppressWarnings({ "static-access", "unchecked" })
	public void compareDepjson(String localfiledata, String ProductName, String deployfile) throws JSONException {
		miscnt=0;
		matcnt=0;
		
		HashMap<String, Object> hmatch = new HashMap<String, Object>();
		HashMap<String, Object> hnotmatch = new HashMap<String, Object>();

		JSONObject objm = new JSONObject();
		JSONObject objn = new JSONObject();
		
		try {
			// Read a master JSON file
			String mastercontent = FileUtils.readFileToString(new File(deployfile), "utf-8");

			// Convert string to JSONObject of Master File data
			jsonMasterObj = new JSONObject(mastercontent);

			// Convert JSON string to JSONObject of Local File data
			jsonLocalObj = new JSONObject(localfiledata);

			for (Object key : jsonLocalObj.getNames(jsonLocalObj)) {
				flag = false;
				localKey = (String) key;
				Object localKeyValue = jsonLocalObj.get(localKey);

				if (localKey.startsWith("$")) {
					if (localKey.startsWith("{", 1)) {
						localKey = localKey.substring(2, localKey.length() - 1);
					} else {
						localKey = localKey.substring(1, localKey.length() - 1);
					}
				} else if (localKey.startsWith("{")) {
					localKey = localKey.substring(1, localKey.length() - 1);
				}

				@SuppressWarnings("rawtypes")
				Iterator iterator = jsonMasterObj.keys();
				while (iterator.hasNext()) {
					String key2 = (String) iterator.next();
					JSONObject masterk = jsonMasterObj.getJSONObject(key2);
					masterk.keys().forEachRemaining(k -> {
						try {
							//put match properties into jsonObject
							if (k.equals(localKey)) {
								hmatch.put("description", "");
								hmatch.put("alias", "");
								hmatch.put("category", "");
								hmatch.put("value", localKeyValue);
								hmatch.put("required", "");

								objm.put(localKey, hmatch);
									matcnt++;
								flag = true;
							}
						} catch (JSONException e) {
							e.printStackTrace();

						}

					});

				} // while

				//put missing properties into notmatch
				if (!flag) {  
					hnotmatch.put("description", "");
					hnotmatch.put("alias", "");
					hnotmatch.put("category", "");
					hnotmatch.put("value", localKeyValue);
					hnotmatch.put("required", "");
					try {
						objn.put(localKey, hnotmatch);
						miscnt++;
					} catch (JSONException e) {
						e.printStackTrace();
					}

				} // if

			} // end for loop

			if (!objm.toString().equals("{}")) {
				WriteDeploymentMatch.writeDeploymentJson(objm, ProductName,matcnt);
			}
			if (!objn.toString().equals("{}")) {
				WriteDeploymentMissing.DeployMissing(objn, ProductName,miscnt);
			}
		} // end of first try
		catch (Exception e) {
			System.out.println(e);
		}

	}

}
