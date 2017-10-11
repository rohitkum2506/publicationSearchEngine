package team1.searchengine.frontend;

import org.apache.commons.lang3.StringEscapeUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;
import java.util.List;

public class MyHandler extends DefaultHandler {
	private List<RawRecord> articleList = null;
    private RawRecord art = null;
    private List<RawRecord> proceedingList = null;
    private RawRecord proceed = null;
    private List<RawRecord> inProList = null;
    private RawRecord inProceed = null;
    private List<RawRecord> authorList = null;
    private RawRecord auth = null;
    private List<RawRecord> bookList = null;
    private RawRecord book = null;
    private List<RawRecord> collectList = null;
    private RawRecord collect = null;
    private List<RawRecord> phdList = null;
    private RawRecord phd = null;
    private List<RawRecord> masterList = null;
    private RawRecord master = null;
    private RawRecordCollection innerRecords = null;


    //getter method for employee list
    public List<RawRecord> getArtList() {
        return articleList;
    }
    public List<RawRecord> getProList() {
        return proceedingList;
    }    
    public List<RawRecord> getInProList() {
        return inProList;
    }
    public List<RawRecord> getAuthorList() {
        return authorList;
    }
    public List<RawRecord> getBookList() {
        return bookList;
    }
    public List<RawRecord> getCollectList() {
        return collectList;
    }
    public List<RawRecord> getPhdList() {
        return phdList;
    }
    public List<RawRecord> getMasterList() {
        return masterList;
    }
    
    boolean artAuthor = false;
	boolean artTitle = false;
	boolean artPages = false;
	boolean artYear = false;
	boolean artVolume = false;
	boolean artJournal = false;
	boolean artNumber = false;
	boolean artUrl = false;
	boolean artEe = false;
	boolean artCdrom = false;
	boolean artCrossRef = false;
	boolean artCite = false;
	
	boolean proEditor = false;
	boolean proTitle = false;
	boolean proYear = false;
	boolean proPublisher = false;
	boolean proSeries = false;
	boolean proVolume = false;
	boolean proEe = false;
	boolean proIsbn = false;
	boolean proBookTitle = false;
	boolean proUrl = false;
	
	boolean inAuthor = false;
	boolean inTitle = false;
	boolean inPages = false;
	boolean inEe = false;
	boolean inYear = false;
	boolean inCrossRef = false;
	boolean inBookTitle = false;
	boolean inUrl = false;
	
	boolean authAuthor = false;
	boolean authTitle = false;
	boolean authUrl = false;
	boolean authNote = false;
	
	boolean bookAuthor = false;
	boolean bookTitle = false;
	boolean bookPublisher = false;
	boolean bookYear = false;
	boolean bookPages = false;
	boolean bookSeries = false;
	boolean bookIsbn = false;
	boolean bookEe = false;
	
	boolean collAuthor = false;
	boolean collTitle = false;
	boolean collPages = false;
	boolean collYear = false;
	boolean collBookTitle = false;
	boolean collEe = false;
	boolean collCrossRef = false;
	boolean collUrl = false;
	
	boolean phdAuthor = false;
	boolean phdTitle = false;
	boolean phdYear = false;
	boolean phdSchool = false;
	boolean phdEe = false;
	boolean phdUrl = false;
	boolean phdPages = false;
	
	boolean masterAuthor = false;
	boolean masterTitle = false;
	boolean masterYear = false;
	boolean masterSchool = false;
	boolean masterUrl = false;
	boolean masterEe = false;

	boolean isArticle = false;
	boolean isProceeding = false;
	boolean isInPro = false;
	boolean isAuthor = false;
	boolean isBook = false;
	boolean isCollect = false;
	boolean isPhd = false;
	boolean isMaster = false;
	
	int artAuthorCount = 0;
	int inAuthorCount = 0;
	int authAuthorCount = 0;
	int bookAuthorCount = 0;
	int collAuthorCount = 0;
	int phdAuthorCount = 0;
	int masterAuthorCount = 0;

	public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
		
		if (qName.equalsIgnoreCase("article")) {
			isArticle = true;
			String articleKey = attributes.getValue("key");
			//System.out.println("key: " + articleKey); 
			art = new RawRecord();
			art.attributes.add(new RawAttribute("key", articleKey));
			art.key = "article";
			innerRecords = new RawRecordCollection();
			if (articleList == null)
				articleList = new ArrayList<>();
		}else if (qName.equalsIgnoreCase("proceedings")) {
			isProceeding = true;
			String proceedingKey = attributes.getValue("key");
			//System.out.println("key: " + proceedingKey);
			proceed = new RawRecord();
			proceed.attributes.add(new RawAttribute("key", proceedingKey));
			proceed.key = "proceedings";
			innerRecords = new RawRecordCollection();
			if (proceedingList == null)
				proceedingList = new ArrayList<>();
		}else if (qName.equalsIgnoreCase("inproceedings")) {
			isInPro = true;
			String inProKey = attributes.getValue("key");
			//System.out.println("key: " + inProKey);
			inProceed = new RawRecord();
			inProceed.attributes.add(new RawAttribute("key", inProKey));
			inProceed.key = "inproceedings";
			innerRecords = new RawRecordCollection();
			if (inProList == null)
				inProList = new ArrayList<>();
		}else if (qName.equalsIgnoreCase("www")) {
			isAuthor = true;
			String authKey = attributes.getValue("key");
			//System.out.println("key: " + authKey);
			auth = new RawRecord();
			auth.attributes.add(new RawAttribute("key", authKey));
			auth.key = "author";
			innerRecords = new RawRecordCollection();
			if (authorList == null)
				authorList = new ArrayList<>();
		}else if (qName.equalsIgnoreCase("book")) {
			isBook = true;
			String bookKey = attributes.getValue("key");
			//System.out.println("key: " + bookKey);
			book = new RawRecord();
			book.attributes.add(new RawAttribute("key", bookKey));
			book.key = "book";
			innerRecords = new RawRecordCollection();
			if (bookList == null)
				bookList = new ArrayList<>();
		}else if (qName.equalsIgnoreCase("incollection")) {
			isCollect = true;
			String collectKey = attributes.getValue("key");
			//System.out.println("key: " + collectKey);
			collect = new RawRecord();
			collect.attributes.add(new RawAttribute("key", collectKey));
			collect.key = "incollection";
			innerRecords = new RawRecordCollection();
			if (collectList == null)
				collectList = new ArrayList<>();
		}else if (qName.equalsIgnoreCase("phdthesis")) {
			isPhd = true;
			String phdKey = attributes.getValue("key");
			//System.out.println("key: " + phdKey);
			phd = new RawRecord();
			phd.attributes.add(new RawAttribute("key", phdKey));
			phd.key = "phdthesis";
			innerRecords = new RawRecordCollection();
			if (phdList == null)
				phdList = new ArrayList<>();
		}else if (qName.equalsIgnoreCase("mastersthesis")) {
			isMaster = true;
			String masterKey = attributes.getValue("key");
			//System.out.println("key: " + masterKey);
			master = new RawRecord();
			master.attributes.add(new RawAttribute("key", masterKey));
			master.key = "mastersthesis";
			innerRecords = new RawRecordCollection();
			if (masterList == null)
				masterList = new ArrayList<>();
		}
		else if (qName.equalsIgnoreCase("author") && isArticle) {
			artAuthor = true;
		}else if (qName.equalsIgnoreCase("title") && isArticle) {
			artTitle = true;
		}else if (qName.equalsIgnoreCase("pages") && isArticle) {
			artPages = true;
		}else if (qName.equalsIgnoreCase("year") && isArticle) {
			artYear = true;
		}else if (qName.equalsIgnoreCase("volume") && isArticle) {
			artVolume = true;
		}else if (qName.equalsIgnoreCase("journal") && isArticle) {
			artJournal = true;
		}else if (qName.equalsIgnoreCase("number") && isArticle) {
			artNumber = true;
		}else if (qName.equalsIgnoreCase("url") && isArticle) {
			artUrl = true;
		}else if (qName.equalsIgnoreCase("ee") && isArticle) {
			artEe = true;
		}else if (qName.equalsIgnoreCase("cdrom") && isArticle) {
			artCdrom = true;
		}else if (qName.equalsIgnoreCase("crossref") && isArticle) {
			artCrossRef = true;
		}else if (qName.equalsIgnoreCase("cite") && isArticle) {
			artCite = true;
		}else if (qName.equalsIgnoreCase("editor") && isProceeding) {
			proEditor = true;
		}else if (qName.equalsIgnoreCase("title") && isProceeding) {
			proTitle = true;
		}else if (qName.equalsIgnoreCase("year") && isProceeding) {
			proYear = true;
		}else if (qName.equalsIgnoreCase("publisher") && isProceeding) {
			proPublisher = true;
		}else if (qName.equalsIgnoreCase("series") && isProceeding) {
			proSeries = true;
		}else if (qName.equalsIgnoreCase("volume") && isProceeding) {
			proVolume = true;
		}else if (qName.equalsIgnoreCase("ee") && isProceeding) {
			proEe = true;
		}else if (qName.equalsIgnoreCase("isbn") && isProceeding) {
			proIsbn = true;
		}else if (qName.equalsIgnoreCase("booktitle") && isProceeding) {
			proBookTitle = true;
		}else if (qName.equalsIgnoreCase("url") && isProceeding) {
			proUrl = true;
		}else if (qName.equalsIgnoreCase("author") && isInPro) {
			inAuthor = true;
		}else if (qName.equalsIgnoreCase("title") && isInPro) {
			inTitle = true;
		}else if (qName.equalsIgnoreCase("pages") && isInPro) {
			inPages = true;
		}else if (qName.equalsIgnoreCase("ee") && isInPro) {
			inEe = true;
		}else if (qName.equalsIgnoreCase("year") && isInPro) {
			inYear = true;
		}else if (qName.equalsIgnoreCase("crossref") && isInPro) {
			inCrossRef = true;
		}else if (qName.equalsIgnoreCase("booktitle") && isInPro) {
			inBookTitle = true;
		}else if (qName.equalsIgnoreCase("url") && isInPro) {
			inUrl = true;
		}else if (qName.equalsIgnoreCase("author") && isAuthor) {
			authAuthor = true;
		}else if (qName.equalsIgnoreCase("title") && isAuthor) {
			authTitle = true;
		}else if (qName.equalsIgnoreCase("url") && isAuthor) {
			authUrl = true;
		}else if (qName.equalsIgnoreCase("note") && isAuthor) {
			authNote = true;
		}else if (qName.equalsIgnoreCase("author") && isBook) {
			bookAuthor = true;
		}else if (qName.equalsIgnoreCase("title") && isBook) {
			bookTitle = true;
		}else if (qName.equalsIgnoreCase("publisher") && isBook) {
			bookPublisher = true;
		}else if (qName.equalsIgnoreCase("year") && isBook) {
			bookYear = true;
		}else if (qName.equalsIgnoreCase("pages") && isBook) {
			bookPages = true;
		}else if (qName.equalsIgnoreCase("series") && isBook) {
			bookSeries = true;
		}else if (qName.equalsIgnoreCase("isbn") && isBook) {
			bookIsbn = true;
		}else if (qName.equalsIgnoreCase("ee") && isBook) {
			bookEe = true;
		}else if (qName.equalsIgnoreCase("author") && isCollect) {
			collAuthor = true;
		}else if (qName.equalsIgnoreCase("title") && isCollect) {
			collTitle = true;
		}else if (qName.equalsIgnoreCase("pages") && isCollect) {
			collPages = true;
		}else if (qName.equalsIgnoreCase("year") && isCollect) {
			collYear = true;
		}else if (qName.equalsIgnoreCase("booktitle") && isCollect) {
			collBookTitle = true;
		}else if (qName.equalsIgnoreCase("ee") && isCollect) {
			collEe = true;
		}else if (qName.equalsIgnoreCase("crossref") && isCollect) {
			collCrossRef = true;
		}else if (qName.equalsIgnoreCase("url") && isCollect) {
			collUrl = true;
		}else if (qName.equalsIgnoreCase("author") && isPhd) {
			phdAuthor = true;
		}else if (qName.equalsIgnoreCase("title") && isPhd) {
			phdTitle = true;
		}else if (qName.equalsIgnoreCase("year") && isPhd) {
			phdYear = true;
		}else if (qName.equalsIgnoreCase("school") && isPhd) {
			phdSchool = true;
		}else if (qName.equalsIgnoreCase("url") && isPhd) {
			phdUrl = true;
		}else if (qName.equalsIgnoreCase("ee") && isPhd) {
			phdEe = true;
		}else if (qName.equalsIgnoreCase("pages") && isPhd) {
			phdPages = true;
		}else if (qName.equalsIgnoreCase("author") && isMaster) {
			masterAuthor = true;
		}else if (qName.equalsIgnoreCase("title") && isMaster) {
			masterTitle = true;
		}else if (qName.equalsIgnoreCase("year") && isMaster) {
			masterYear = true;
		}else if (qName.equalsIgnoreCase("school") && isMaster) {
			masterSchool = true;
		}else if (qName.equalsIgnoreCase("ee") && isMaster) {
			masterEe = true;
		}else if (qName.equalsIgnoreCase("url") && isMaster) {
			masterUrl = true;
		}
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("article")) {
        	isArticle = false;
        	art.value = innerRecords;
            articleList.add(art);
            //System.out.println("EndElement: " + qName);
        }else if (qName.equalsIgnoreCase("proceedings")) {
        	isProceeding = false;
        	proceed.value = innerRecords;
        	proceedingList.add(proceed);
            //System.out.println("EndElement: " + qName);
        }else if (qName.equalsIgnoreCase("inproceedings")) {
        	isInPro = false;
        	inProceed.value = innerRecords;
        	inProList.add(inProceed);
            //System.out.println("EndElement: " + qName);
        }else if (qName.equalsIgnoreCase("www")) {
        	isAuthor = false;
        	auth.value = innerRecords;
        	authorList.add(auth);
            //System.out.println("EndElement: " + qName);
        }else if (qName.equalsIgnoreCase("book")) {
        	isBook = false;
        	book.value = innerRecords;
        	bookList.add(book);
            //System.out.println("EndElement: " + qName);
        }else if (qName.equalsIgnoreCase("incollection")) {
        	isCollect = false;
        	collect.value = innerRecords;
        	collectList.add(collect);
            //System.out.println("EndElement: " + qName);
        }else if (qName.equalsIgnoreCase("phdthesis")) {
        	isPhd = false;
        	phd.value = innerRecords;
        	phdList.add(phd);
            //System.out.println("EndElement: " + qName);
        }else if (qName.equalsIgnoreCase("mastersthesis")) {
        	isMaster = false;
        	master.value = innerRecords;
        	masterList.add(master);
            //System.out.println("EndElement: " + qName);
        }else if (qName.equalsIgnoreCase("author") && isArticle) {
          artAuthor = false;
          artAuthorCount = 0;
        }else if (qName.equalsIgnoreCase("author") && isInPro) {
          inAuthor = false;
          inAuthorCount = 0;
        }else if (qName.equalsIgnoreCase("author") && isAuthor) {
          authAuthor = false;
          authAuthorCount = 0;
        }else if (qName.equalsIgnoreCase("author") && isBook) {
          bookAuthor = false;
          bookAuthorCount = 0;
        }else if (qName.equalsIgnoreCase("author") && isCollect) {
          collAuthor = false;
          collAuthorCount = 0;
        }else if (qName.equalsIgnoreCase("author") && isPhd) {
          phdAuthor = false;
          phdAuthorCount = 0;
        }else if (qName.equalsIgnoreCase("author") && isMaster) {
          masterAuthor = false;
          masterAuthorCount = 0;
        }
    }
	
	public void characters(char[] ch, int start, int length) throws SAXException {
	    String str = new String(ch, start, length);
		if (artAuthor) {
		    if (artAuthorCount > 0) {
		        String combStr = innerRecords.records.get(innerRecords.records.size() - 1).value + str;
		        innerRecords.records.get(innerRecords.records.size() - 1).value = combStr;
		        artAuthorCount++;
		    }else {
	            innerRecords.records.add(new RawRecord("author", str, null));
	            artAuthorCount++;
		    }
		}else if (artTitle) {
			innerRecords.records.add(new RawRecord("title", str, null));
			artTitle = false;
		}else if (artPages) {
			innerRecords.records.add(new RawRecord("pages", str, null));
			artPages = false;
		}else if (artYear) {
			innerRecords.records.add(new RawRecord("year", str, null));
			artYear = false;
		}else if (artVolume) {
			innerRecords.records.add(new RawRecord("volume", str, null));
			artVolume = false;
		}else if (artJournal) {
			innerRecords.records.add(new RawRecord("journal", str, null));
			artJournal = false;
		}else if (artNumber) {
			innerRecords.records.add(new RawRecord("number", str, null));
			artNumber = false;
		}else if (artUrl) {
			innerRecords.records.add(new RawRecord("url", str, null));
			artUrl = false;
		}else if (artEe) {
			innerRecords.records.add(new RawRecord("ee", str, null));
			artEe = false;
		}else if (artCdrom) {
			innerRecords.records.add(new RawRecord("cdrom", str, null));
			artCdrom = false;
		}else if (artCrossRef) {
			innerRecords.records.add(new RawRecord("crossref", str, null));
			artCrossRef = false;
		}else if (artCite) {
			innerRecords.records.add(new RawRecord("cite", str, null));
			artCite = false;
		}else if (proEditor) {
			innerRecords.records.add(new RawRecord("editor", str, null));
			proEditor = false;
		}else if (proTitle) {
			innerRecords.records.add(new RawRecord("title", str, null));
			proTitle = false;
		}else if (proYear) {
			innerRecords.records.add(new RawRecord("year", str, null));
			proYear = false;
		}else if (proPublisher) {
			innerRecords.records.add(new RawRecord("publisher", str, null));
			proPublisher = false;
		}else if (proSeries) {
			innerRecords.records.add(new RawRecord("series", str, null));
			proSeries = false;
		}else if (proVolume) {
			innerRecords.records.add(new RawRecord("volume", str, null));
			proVolume = false;
		}else if (proEe) {
			innerRecords.records.add(new RawRecord("ee", str, null));
			proEe = false;
		}else if (proIsbn) {
			innerRecords.records.add(new RawRecord("isbn", str, null));
			proIsbn = false;
		}else if (proBookTitle) {
			innerRecords.records.add(new RawRecord("booktitle", str, null));
			proBookTitle = false;
		}else if (proUrl) {
			innerRecords.records.add(new RawRecord("url", str, null));
			proUrl = false;
		}else if (inAuthor) {
		    if (inAuthorCount > 0) {
              String combStr = innerRecords.records.get(innerRecords.records.size() - 1).value + str;
              innerRecords.records.get(innerRecords.records.size() - 1).value = combStr;
              inAuthorCount++;
            }else {
              innerRecords.records.add(new RawRecord("author", str, null));
              inAuthorCount++;
            }
		}else if (inTitle) {
			innerRecords.records.add(new RawRecord("title", str, null));
			inTitle = false;
		}else if (inPages) {
			innerRecords.records.add(new RawRecord("pages", str, null));
			inPages = false;
		}else if (inEe) {
			innerRecords.records.add(new RawRecord("ee", str, null));
			inEe = false;
		}else if (inYear) {
			innerRecords.records.add(new RawRecord("year", str, null));
			inYear = false;
		}else if (inCrossRef) {
			innerRecords.records.add(new RawRecord("crossref", str, null));
			inCrossRef = false;
		}else if (inBookTitle) {
			innerRecords.records.add(new RawRecord("booktitle", str, null));
			inBookTitle = false;
		}else if (inUrl) {
			innerRecords.records.add(new RawRecord("url", str, null));
			inUrl = false;
		}else if (authAuthor) {
		    if (authAuthorCount > 0) {
              String combStr = innerRecords.records.get(innerRecords.records.size() - 1).value + str;
              innerRecords.records.get(innerRecords.records.size() - 1).value = combStr;
              authAuthorCount++;
            }else {
              innerRecords.records.add(new RawRecord("author", str, null));
              authAuthorCount++;
            }
		}else if (authTitle) {
			innerRecords.records.add(new RawRecord("title", str, null));
			authTitle = false;
		}else if (authUrl) {
			innerRecords.records.add(new RawRecord("url", str, null));
			authUrl = false;
		}else if (authNote) {
			innerRecords.records.add(new RawRecord("note", str, null));
			authNote = false;
		}else if (bookAuthor) {
		  if (bookAuthorCount > 0) {
            String combStr = innerRecords.records.get(innerRecords.records.size() - 1).value + str;
            innerRecords.records.get(innerRecords.records.size() - 1).value = combStr;
            bookAuthorCount++;
        }else {
            innerRecords.records.add(new RawRecord("author", str, null));
            bookAuthorCount++;
        }
		}else if (bookTitle) {
			innerRecords.records.add(new RawRecord("title", str, null));
			bookTitle = false;
		}else if (bookPublisher) {
			innerRecords.records.add(new RawRecord("publisher", str, null));
			bookPublisher = false;
		}else if (bookYear) {
			innerRecords.records.add(new RawRecord("year", str, null));
			bookYear = false;
		}else if (bookPages) {
			innerRecords.records.add(new RawRecord("pages", str, null));
			bookPages = false;
		}else if (bookSeries) {
			innerRecords.records.add(new RawRecord("series", str, null));
			bookSeries = false;
		}else if (bookIsbn) {
			innerRecords.records.add(new RawRecord("isbn", str, null));
			bookIsbn = false;
		}else if (bookEe) {
			innerRecords.records.add(new RawRecord("ee", str, null));
			bookEe = false;
		}else if (collAuthor) {
		  if (collAuthorCount > 0) {
            String combStr = innerRecords.records.get(innerRecords.records.size() - 1).value + str;
            innerRecords.records.get(innerRecords.records.size() - 1).value = combStr;
            collAuthorCount++;
        }else {
            innerRecords.records.add(new RawRecord("author", str, null));
            collAuthorCount++;
        }
		}else if (collTitle) {
			innerRecords.records.add(new RawRecord("title", str, null));
			collTitle = false;
		}else if (collPages) {
			innerRecords.records.add(new RawRecord("pages", str, null));
			collPages = false;
		}else if (collYear) {
			innerRecords.records.add(new RawRecord("year", str, null));
			collYear = false;
		}else if (collBookTitle) {
			innerRecords.records.add(new RawRecord("booktitle", str, null));
			collBookTitle = false;
		}else if (collEe) {
			innerRecords.records.add(new RawRecord("ee", str, null));
			collEe = false;
		}else if (collCrossRef) {
			innerRecords.records.add(new RawRecord("crossref", str, null));
			collCrossRef = false;
		}else if (collUrl) {
			innerRecords.records.add(new RawRecord("url", str, null));
			collUrl = false;
		}else if (phdAuthor) {
		  if (phdAuthorCount > 0) {
            String combStr = innerRecords.records.get(innerRecords.records.size() - 1).value + str;
            innerRecords.records.get(innerRecords.records.size() - 1).value = combStr;
            phdAuthorCount++;
        }else {
            innerRecords.records.add(new RawRecord("author", str, null));
            phdAuthorCount++;
        }
		}else if (phdTitle) {
			innerRecords.records.add(new RawRecord("title", str, null));
			phdTitle = false;
		}else if (phdYear) {
			innerRecords.records.add(new RawRecord("year", str, null));
			phdYear = false;
		}else if (phdSchool) {
			innerRecords.records.add(new RawRecord("school", str, null));
			phdSchool = false;
		}else if (phdEe) {
			innerRecords.records.add(new RawRecord("ee", str, null));
			phdEe = false;
		}else if (phdUrl) {
			innerRecords.records.add(new RawRecord("url", str, null));
			phdUrl = false;
		}else if (phdPages) {
			innerRecords.records.add(new RawRecord("pages", str, null));
			phdPages = false;
		}else if (masterAuthor) {
		  if (masterAuthorCount > 0) {
            String combStr = innerRecords.records.get(innerRecords.records.size() - 1).value + str;
            innerRecords.records.get(innerRecords.records.size() - 1).value = combStr;
            masterAuthorCount++;
        }else {
            innerRecords.records.add(new RawRecord("author", str, null));
            masterAuthorCount++;
        }
		}else if (masterTitle) {
			innerRecords.records.add(new RawRecord("title", str, null));
			masterTitle = false;
		}else if (masterYear) {
			innerRecords.records.add(new RawRecord("year", str, null));
			masterYear = false;
		}else if (masterSchool) {
			innerRecords.records.add(new RawRecord("school", str, null));
			masterSchool = false;
		}else if (masterEe) {
			innerRecords.records.add(new RawRecord("ee", str, null));
			masterEe = false;
		}else if (masterUrl) {
			innerRecords.records.add(new RawRecord("url", str, null));
			masterUrl = false;
		}
	}

}
