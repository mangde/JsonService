package com.mainclass;
/*
 * This is a main class which is take a JSON MetaDataFile and EnvironmentJSON File 
 * Find the path of respective product file and call a methods for further processing 
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.JsonMappingException;

import com.checkfiletype.CheckFileType;
import com.util.GetFileNameFromUser;
import com.writemissmatchfile.FileDataSender;

public class ReadJsonMetaDataFile {

	static BufferedReader reader;
	static String envfilepath;

	@SuppressWarnings("resource")
	public static void main(String args[]) throws Throwable {

		JsonFactory jfactory = new JsonFactory();
		JsonParser jParser;
		String choice;

		// Getting a JsonMetaData File from User
		System.out.println("JsonMetada file:" + args[0]);
		String jmetafile = args[0];
		do {
			try {
					jParser = jfactory.createJsonParser(new File(jmetafile));

					// loop until token equal to "]"
					while (jParser.nextToken() != null) {

						String fieldname = jParser.getCurrentName();
						if ("product".equals(fieldname)) {

							jParser.nextToken();
							String str = jParser.getText();
							jParser = readJsonMeta(jParser, str);

						}
					}

				} catch (JsonGenerationException e) {
					e.printStackTrace();
				} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			

			System.out.println();
			Scanner sc = new Scanner(System.in);

			System.out.println("If you want to exit enter [y]");

			choice = sc.next();

		} while (choice == "y"); // doWhileEnd

	}// main Method End

	public static JsonParser readJsonMeta(JsonParser jParser, String product) throws Throwable {
		try {

			System.out.println("\n" + product);
			System.out.println();
			// Getting a Environment.Json File from user
			GetFileNameFromUser gf = new GetFileNameFromUser();
			envfilepath = gf.getEnvironmentConfigJson(product);

			String deployfilepath = gf.getDeployment(product);

			jParser.nextToken();
			String fieldname = jParser.getCurrentName();

			if ("path".equals(fieldname)) {
				jParser.nextToken();

				if (jParser.hasTextCharacters()) {
					CheckFileType rp = new CheckFileType();
					rp.checkType(jParser.getText(), product, deployfilepath, envfilepath);
				}

			}
			if ("onFS".equals(fieldname)) {

				while (jParser.nextToken() != JsonToken.END_ARRAY) {
					fieldname = jParser.getCurrentName();

					if ("path".equals(fieldname)) {
						jParser.nextToken();
					}

					if (jParser.hasTextCharacters()) {
						CheckFileType rp = new CheckFileType();
						rp.checkType(jParser.getText(), product, deployfilepath, envfilepath);
					}

				}

			}

			jParser.nextToken();
			fieldname = jParser.getCurrentName();

			if ("inArtifact".equals(fieldname)) {

				while (jParser.nextToken() != JsonToken.END_ARRAY) {
					fieldname = jParser.getCurrentName();
					if ("path".equals(fieldname)) {
						jParser.nextToken();
					}

					if (jParser.hasTextCharacters()) {
						CheckFileType rp = new CheckFileType();
						System.out.println(jParser.getText());
						rp.checkType(jParser.getText(), product, deployfilepath, envfilepath);
					}
				}
			}

			jParser.nextToken();
			fieldname = jParser.getCurrentName();

			if ("inXML".equals(fieldname)) {

				while (jParser.nextToken() != JsonToken.END_ARRAY) {
					fieldname = jParser.getCurrentName();

					if ("path".equals(fieldname)) {
						jParser.nextToken();
					}

					if (jParser.hasTextCharacters()) {
						CheckFileType rp = new CheckFileType();
						rp.checkType(jParser.getText(), product, deployfilepath, envfilepath);
					}
				}

			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		FileDataSender fds = new FileDataSender();
		fds.callWriter();

		return jParser;

	}
}
