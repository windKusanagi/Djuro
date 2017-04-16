package com.ram.translation;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;

import com.rmtheis.yandtran.language.Language;
import com.rmtheis.yandtran.translate.Translate;




/**
 * 
 * @author Xiang	
 * @date : 2017-04-16
 * 
 * 
 * This class includes function that reads and translate a set of xml content based on the language tags detected,  and a function to generate
 * the .xml file consists of translated content    
 */

public class TranlationService {

	public static ArrayList<String[]> GetTranslatedWordSet ( ArrayList<String[]> dataset , String SrcL , String TarL){
		ArrayList<String []> translatedWordSet = new ArrayList<String[]>() ;
		for( int i=0; i< dataset.size();i++){
			String [] temp = {GetTranslation(dataset.get(i)[0], SrcL , TarL ) , GetTranslation(dataset.get(i)[1], SrcL, TarL)};
			translatedWordSet.add(temp);
		}
		return translatedWordSet;
	}
	public static String GetTranslation( String word , String srcLanguage , String tarLanguage){
		String result = null;
		Translate.setKey("trnsl.1.1.20170127T132221Z.56d908daddb4ecd1.f630c558d60d952e62150968dd3f5afc340436a5");
		
		Language dLang=DetectLang(srcLanguage);
		
		
		try {
			result = Translate.execute(word, DetectLang(srcLanguage), DetectLang(tarLanguage));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 * This is a code stub that decides the target language translation. Based on your requirement please feel free to add new languages
	 * 
	 * @param Lan language string
	 * @return An language object
	 * 
	 */
	
	private static Language DetectLang(String Lan) {
		// TODO Auto-generated method stub
      if(Lan.equals("ENG")){
    	return Language.ENGLISH;  
      }
      else if(Lan.equals("FRE")) {
    	  return Language.FRENCH;
      }else if(Lan.equals("ESP")) {
    	  return Language.SPANISH;
      }else if(Lan.equals("DE")) {
    	  return Language.GERMAN;
      }else{
    	  return null;
      }
     
	}
	public static void XMLGenerator (ArrayList<String[]> data , String filePath) {  
	    PrintWriter writer;
		try {
			writer = new PrintWriter(filePath, "UTF-8");
			writer.println(" <?xml version=\"1.0\" encoding=\"UTF-8\"?> ");
		    writer.println("<resources>");
		    
		    for ( int i=0 ; i<data.size(); i++){
		    	String entry = GenerateXMLEntry ( data.get(i));
		    	writer.println(entry);
		    }
		    
		    writer.println("</resources>");
		    writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public static String GenerateXMLEntry ( String[] data){
		String entry = null;
		String seg1 = "	<string name=\"";
		String seg2 = "\">";
		String seg3 = "</string>";
		entry = seg1 + data[0] + seg2 + data[1] + seg3;
		return entry;		
	}
}