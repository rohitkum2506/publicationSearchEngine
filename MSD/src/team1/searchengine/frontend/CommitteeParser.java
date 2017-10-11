package team1.searchengine.frontend;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;


public class CommitteeParser {
	private static String G = "";
	private static String P = "";
	private static String C = "";
	private static String E = "";
	private static String M = "";
	
	public static HashMap<String, String> parsing(String folderP) {
		HashMap<String, String> hashM = new HashMap<String, String>();
		List<String> filePaths = new ArrayList<String>();
		try(Stream<Path> paths = Files.walk(Paths.get(folderP)))
		{
		    paths.forEach(filePath -> {
		        if (Files.isRegularFile(filePath) && filePath.toString().endsWith(".txt"))
		        {
		            filePaths.add(filePath.toString());
		        }
		    });
		} 
		catch (IOException e) {
			System.out.println("Couldn't load committees");
			e.printStackTrace();
		}

		Scanner input = null;
		for (int i = 0; i < filePaths.size(); i++) {
			try {
				FileInputStream file = new FileInputStream(filePaths.get(i));
		    	input = new Scanner(file);
				while(input.hasNext())
		    	   {
		    	       //process line by line
		    		   String nextLine = input.nextLine();
		    		   if(nextLine.startsWith("G:"))
		    		   {
		    			   G += nextLine.substring(2) + ",";
		    		   }
		    		   else if(nextLine.startsWith("P:"))
		    		   {
		    			   P += nextLine.substring(2) + ",";
		    		   }
		    		   else if(nextLine.startsWith("C:"))
		    		   {
		    			   C += nextLine.substring(2) + ",";
		    		   }
		    		   else if(nextLine.startsWith("E:"))
		    		   {
		    			   E += nextLine.substring(2) + ",";
		    		   }
		    		   else
		    		   {
		    			   M += nextLine + ",";
		    		   }
		    	   }
		
		    	   input.close();
			} catch (FileNotFoundException e) {
				System.out.println("Couldn't load committees");
				e.printStackTrace();
			}
		}
		hashM.put("G", G);
		hashM.put("P", P);
		hashM.put("C", C);
		hashM.put("E", E);
		hashM.put("M", M);
		return hashM;
	}
}
