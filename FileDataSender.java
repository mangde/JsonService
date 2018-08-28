package com.writemissmatchfile;

import java.io.File;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

public class FileDataSender {
	WriteEnvironmentMissing emiss = new WriteEnvironmentMissing();
	WriteEnvironmentMatch ematch = new WriteEnvironmentMatch();
	WriteDeploymentMatch dmatch = new WriteDeploymentMatch();
	WriteDeploymentMissing dmiss = new WriteDeploymentMissing();

	File fn;
	FileWriterClass fwc = new FileWriterClass();

	public void callWriter() throws IOException, JSONException {
		try {
			// Call Environment Missing writer file
			JSONObject jemiss = emiss.getMissJsonData();
			if ((fn = emiss.getfile()) != null) {
				fwc.writerForAll(jemiss, fn);
				emiss.clearEmissJsonData();
			}

			JSONObject jematch = ematch.getMatchJsonData();
			if ((fn = ematch.getfile()) != null) {
				fwc.writerForAll(jematch, fn);
				ematch.clearEmatchJsonData();
			}

			JSONObject jdmatch = dmatch.getDmatchJsonData();
			if ((fn = dmatch.getfile()) != null) {
				fwc.writerForAll(jdmatch, fn);
				dmatch.clearDmatchJsonData();
			}

			JSONObject jdmiss = dmiss.getDmissJsonData();
			if ((fn = dmiss.getfile()) != null) {
				fwc.writerForAll(jdmiss, fn);
				dmiss.clearDmissJsonData();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
