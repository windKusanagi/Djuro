package com.ram.translation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This is the Main file.
 * 
 * Reads the Input filename,source language,target language and the name of the output file.
 * 
 * @version 1.0
 * @author Ramkumar Velmurugan
 *
 */

public class Main {

	private static ArrayList<String[]> lPair = new ArrayList<String[]>();
	private static ArrayList<String[]> rPair = new ArrayList<String[]>();

	public static void main(String args[]) {

		try {
			parseXML(args[0],args[1],args[2],args[3]);
		} 
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void parseXML(String args, String args2, String args3,
			String args4) throws 
			IOException {
		// TODO Auto-generated method stub

		File fp = new File(args);
		String readLine = "";

		BufferedReader bufferedReader = new BufferedReader(new FileReader(fp));
		while ((readLine = bufferedReader.readLine()) != null) {
			if (!readLine.equals("<resources>")) {
				if (!readLine.equals("</resources>")) {
			
					try
					{
					String r[] = readLine.split(">");
					String id = r[0].substring(17);
					String lSource = r[1].substring(0, r[1].lastIndexOf("<"));
					String lParam[]={id.substring(1,id.length()-1),lSource};
					lPair.add(lParam);
					}
					catch (Exception e) {
						// TODO: handle exception
					}
					}
			}
		}
		
		TranlationService mTranslation = new TranlationService();
		rPair=mTranslation.GetTranslatedWordSet(lPair,args2,args3);
		mTranslation.XMLGenerator(rPair,args4);		
	}
}