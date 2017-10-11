package team1.searchengine.frontend;

import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class XMLParser implements PublicationParser {

	public RawRecordCollection parse(String path) {
		RawRecordCollection result = null;
		try {
			File inputFile = new File(path);
	        SAXParserFactory factory = SAXParserFactory.newInstance();
	        SAXParser saxParser = factory.newSAXParser();
	        MyHandler userhandler = new MyHandler();
	        saxParser.parse(inputFile, userhandler);
	        List<RawRecord> articleList = userhandler.getArtList();
	        List<RawRecord> proceedingList = userhandler.getProList();
	        List<RawRecord> inProList = userhandler.getInProList();
	        List<RawRecord> authorList = userhandler.getAuthorList();
	        List<RawRecord> bookList = userhandler.getBookList();
	        List<RawRecord> collectList = userhandler.getCollectList();
	        List<RawRecord> phdList = userhandler.getPhdList();
	        List<RawRecord> masterList = userhandler.getMasterList();

	        ArrayList<RawRecord> finalList = new ArrayList<>();
	        if (articleList != null) {
	        	finalList.addAll(articleList);
	        }
	        if (proceedingList != null) {
		        finalList.addAll(proceedingList);
	        }
	        if (inProList != null) {
		        finalList.addAll(inProList);
	        }
	        if (authorList != null) {
		        finalList.addAll(authorList);
	        }
	        if (bookList != null) {
		        finalList.addAll(bookList);
	        }
	        if (collectList != null) {
		        finalList.addAll(collectList);
	        }
	        if (phdList != null) {
		        finalList.addAll(phdList);
	        }
	        if (masterList != null) {
		        finalList.addAll(masterList);
	        }
	        
	        result = new RawRecordCollection(finalList);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}	
}
