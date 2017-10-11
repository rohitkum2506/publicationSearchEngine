package team1.searchengine.logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
	private final String fileName = "appLog.log";
	private static BufferedWriter bw = null;
	private static FileWriter fw = null;
	
	static{
		new Logger();
	}
	
	public Logger() {	
		try {
			File file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			fw = new FileWriter(file.getAbsoluteFile(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		bw = new BufferedWriter(fw);
	}

	public static void log(String message, String logLevel) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		writeToFile(dateFormat.format(date) + " - " + logLevel + " - " + message);
	}
	
	public void log(Object obj, String logLevel) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		writeToFile(dateFormat.format(date) + " - " + logLevel + " - " + obj.toString());
	}

	public static void writeToFile(String cont) {		
		try {
			bw.write(cont+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
//			try {
//				if (bw != null)
//					bw.close();
////				if (fw != null)
////					fw.close();
//			} catch (IOException ex) {
//				ex.printStackTrace();
//			}
		}
	}
}
