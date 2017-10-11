package team1.searchengine.database.preProcessor;

import java.util.HashMap;

import team1.searchengine.frontend.CommitteeParser;

public class ComitteePopulator {
	public static HashMap<String, String> committeeData = new HashMap<String, String>(); 
	
	
	public static void populate(String folderPath){
		committeeData = CommitteeParser.parsing(folderPath);
	}
	
	public static boolean Match(String searchAuth, String tpeOfCom){
		if(committeeData.containsKey(tpeOfCom)){
			String authors = committeeData.get(tpeOfCom);
			return authors.contains(searchAuth);
		}
		return false;
	}
}
