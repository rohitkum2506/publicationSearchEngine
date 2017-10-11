/**
 * 
 */
package team1.searchengine.mock;

import java.util.ArrayList;

import team1.searchengine.frontend.RawAttribute;
import team1.searchengine.frontend.RawRecord;
import team1.searchengine.frontend.RawRecordCollection;
import team1.searchengine.model.*;

/**
 * Mock data for testing Front-End component
 * 
 * @author hrivks
 *
 */
public class MockFrontEnd {

	// Raw record objects
	public static RawRecord getRawRecordArticle1() {
		/*
		 * <article mdate="2011-01-11" key="journals/acta/Blikle81">
		 * <author>Andrzej Blikle</author> <title>The Clean Termination of
		 * Iterative Programs.</title> <pages>199-217</pages> <year>1981</year>
		 * <volume>16</volume> <journal>Acta Inf.</journal>
		 * <url>db/journals/acta/acta16.html#Blikle81</url>
		 * <ee>http://dx.doi.org/10.1007/BF00261259</ee> </article>
		 */

		RawRecord rawRecordArticle1 = new RawRecord();
		rawRecordArticle1.attributes.add(new RawAttribute("key", "journals/acta/Blikle81"));

		RawRecordCollection innerRecords = new RawRecordCollection();
		innerRecords.records.add(new RawRecord("author", "Andrzej Blikle", null));
		innerRecords.records.add(new RawRecord("title", "The Clean Termination of Iterative Programs.", null));
		innerRecords.records.add(new RawRecord("pages", "199-217", null));
		innerRecords.records.add(new RawRecord("year", "1981", null));
		innerRecords.records.add(new RawRecord("volume", "16", null));
		innerRecords.records.add(new RawRecord("journal", "Acta Inf.", null));
		innerRecords.records.add(new RawRecord("url", "db/journals/acta/acta16.html#Blikle81", null));
		innerRecords.records.add(new RawRecord("ee", "http://dx.doi.org/10.1007/BF00261259", null));

		rawRecordArticle1.key = "article";
		rawRecordArticle1.value = innerRecords;

		return rawRecordArticle1;
	}

	public static RawRecord getRawRecordArticle2() {

		/*
		 * <article mdate="2003-11-25" key="journals/acta/EsikB98">
		 * <author>Zoltn sik</author> <author>Michael Bertol</author>
		 * <title>Nonfinite Axiomatizability of the Equational Theory of
		 * Shuffle.</title> <pages>505-539</pages> <year>1998</year>
		 * <volume>35</volume> <journal>Acta Inf.</journal> <number>6</number>
		 * <url>db/journals/acta/acta35.html#EsikB98</url>
		 * <ee>http://link.springer.de/link/service/journals/00236/bibs/8035006/
		 * 80350505.htm</ee>
		 * <note>Erratum: Acta Informatica 35(6): 541 (1998)</note></article>
		 */

		RawRecord rawRecordArticle2 = new RawRecord();
		rawRecordArticle2.attributes.add(new RawAttribute("key", "journals/acta/EsikB98"));

		RawRecordCollection innerRecords = new RawRecordCollection();
		innerRecords.records.add(new RawRecord("author", "Zoltn sik", null));
		innerRecords.records.add(new RawRecord("author", "Michael Bertol", null));
		innerRecords.records
				.add(new RawRecord("title", "Nonfinite Axiomatizability of the Equational Theory of Shuffle.", null));
		innerRecords.records.add(new RawRecord("pages", "505-539", null));
		innerRecords.records.add(new RawRecord("year", "1998", null));
		innerRecords.records.add(new RawRecord("volume", "35", null));
		innerRecords.records.add(new RawRecord("journal", "Acta Inf.", null));
		innerRecords.records.add(new RawRecord("number", "6", null));
		innerRecords.records.add(new RawRecord("note", "Erratum: Acta Informatica 35(6): 541 (1998)", null));
		innerRecords.records.add(new RawRecord("url", "db/journals/acta/acta35.html#EsikB98", null));
		innerRecords.records.add(new RawRecord("ee",
				"http://link.springer.de/link/service/journals/00236/bibs/8035006/80350505.htm", null));

		rawRecordArticle2.key = "article";
		rawRecordArticle2.value = innerRecords;

		return rawRecordArticle2;
	}

	public static RawRecord getRawRecordInproceeding1() {
		/*
		 * <inproceedings mdate="2014-11-03" key="conf/icdar/FerrerMP13">
		 * <author>Miguel A. Ferrer</author> <author>Aythami Morales</author>
		 * <author>Umapada Pal</author> <title>LBP Based Line-Wise Script
		 * Identification.</title> <pages>369-373</pages> <year>2013</year>
		 * <booktitle>ICDAR</booktitle>
		 * <ee>http://dx.doi.org/10.1109/ICDAR.2013.81</ee>
		 * <ee>http://doi.ieeecomputersociety.org/10.1109/ICDAR.2013.81</ee>
		 * <crossref>conf/icdar/2013</crossref>
		 * <url>db/conf/icdar/icdar2013.html#FerrerMP13</url> </inproceedings>
		 */

		RawRecord rawRecordInproceeding1 = new RawRecord();
		rawRecordInproceeding1.attributes.add(new RawAttribute("key", "conf/icdar/FerrerMP13"));

		RawRecordCollection innerRecords = new RawRecordCollection();
		innerRecords.records.add(new RawRecord("author", "Miguel A. Ferrer", null));
		innerRecords.records.add(new RawRecord("author", "Aythami Morales", null));
		innerRecords.records.add(new RawRecord("author", "Umapada Pal", null));
		innerRecords.records.add(new RawRecord("title", "LBP Based Line-Wise Script Identification.", null));
		innerRecords.records.add(new RawRecord("pages", "369-373", null));
		innerRecords.records.add(new RawRecord("year", "2013", null));
		innerRecords.records.add(new RawRecord("booktitle", "ICDAR", null));
		innerRecords.records.add(new RawRecord("ee", "http://dx.doi.org/10.1109/ICDAR.2013.81", null));
		innerRecords.records.add(new RawRecord("ee", "http://doi.ieeecomputersociety.org/10.1109/ICDAR.2013.81", null));
		innerRecords.records.add(new RawRecord("crossref", "conf/icdar/2013", null));
		innerRecords.records.add(new RawRecord("url", "db/conf/icdar/icdar2013.html#FerrerMP13", null));

		rawRecordInproceeding1.key = "inproceedings";
		rawRecordInproceeding1.value = innerRecords;

		return rawRecordInproceeding1;
	}

	public static RawRecord getRawRecordInproceeding2() {
		/*
		 * <inproceedings mdate="2016-04-11" key="conf/icdar/ChammasML15">
		 * <author>Edgard Chammas</author> <author>Chafic Mokbel</author>
		 * <author>Laurence Likforman-Sulem</author> <title>Arabic handwritten
		 * document preprocessing and recognition.</title>
		 * <pages>451-455</pages> <year>2015</year> <booktitle>ICDAR</booktitle>
		 * <ee>http://dx.doi.org/10.1109/ICDAR.2015.7333802</ee>
		 * <ee>http://doi.ieeecomputersociety.org/10.1109/ICDAR.2015.7333802</
		 * ee> <crossref>conf/icdar/2015</crossref>
		 * <url>db/conf/icdar/icdar2015.html#ChammasML15</url>
		 */

		RawRecord rawRecordInproceeding2 = new RawRecord();
		rawRecordInproceeding2.attributes.add(new RawAttribute("key", "conf/icdar/ChammasML15"));

		RawRecordCollection innerRecords = new RawRecordCollection();
		innerRecords.records.add(new RawRecord("author", "Edgard Chammas", null));
		innerRecords.records.add(new RawRecord("author", "Chafic Mokbel", null));
		innerRecords.records.add(new RawRecord("author", "Laurence Likforman-Sulem", null));
		innerRecords.records
				.add(new RawRecord("title", "Arabic handwritten document preprocessing and recognition.", null));
		innerRecords.records.add(new RawRecord("pages", "451-455", null));
		innerRecords.records.add(new RawRecord("year", "2015", null));
		innerRecords.records.add(new RawRecord("booktitle", "ICDAR", null));
		innerRecords.records.add(new RawRecord("ee", "http://dx.doi.org/10.1109/ICDAR.2015.7333802", null));
		innerRecords.records
				.add(new RawRecord("ee", "http://doi.ieeecomputersociety.org/10.1109/ICDAR.2015.7333802", null));
		innerRecords.records.add(new RawRecord("crossref", "conf/icdar/2015", null));
		innerRecords.records.add(new RawRecord("url", "db/conf/icdar/icdar2015.html#ChammasML15", null));

		rawRecordInproceeding2.key = "inproceedings";
		rawRecordInproceeding2.value = innerRecords;

		return rawRecordInproceeding2;
	}

	public static RawRecord getRawRecordProceeding1() {
		/*
		 * <proceedings mdate="2016-03-11" key="journals/thipeac/2011-4">
		 * <editor>Per Stenstr&ouml;m</editor> <title>Transactions on
		 * High-Performance Embedded Architectures and Compilers IV</title>
		 * <volume>6760</volume> <year>2011</year>
		 * <publisher>Springer</publisher> <series
		 * href="db/journals/lncs.html">Lecture Notes in Computer
		 * Science</series> <ee>http://dx.doi.org/10.1007/978-3-642-24568-8</ee>
		 * <isbn>978-3-642-24567-1</isbn> <booktitle>Trans. HiPEAC</booktitle>
		 * <url>db/journals/thipeac/thipeac4.html</url> </proceedings>
		 */

		RawRecord rawRecordProceeding1 = new RawRecord();
		rawRecordProceeding1.attributes.add(new RawAttribute("key", "journals/thipeac/2011-4"));

		RawRecordCollection innerRecords = new RawRecordCollection();
		innerRecords.records.add(new RawRecord("editor", "Per Stenstr&ouml;m", null));
		innerRecords.records.add(new RawRecord("title",
				"Transactions on High-Performance Embedded Architectures and Compilers IV", null));
		innerRecords.records.add(new RawRecord("volume", "6760", null));
		innerRecords.records.add(new RawRecord("year", "2011", null));
		innerRecords.records.add(new RawRecord("publisher", "Springer", null));
		RawRecord seriesRecord = new RawRecord();
		seriesRecord.attributes.add(new RawAttribute("href", "db/journals/lncs.html"));
		seriesRecord.key = "series";
		seriesRecord.value = "Lecture Notes in Computer Science";
		innerRecords.records.add(seriesRecord);
		innerRecords.records.add(new RawRecord("ee", "http://dx.doi.org/10.1007/978-3-642-24568-8", null));
		innerRecords.records.add(new RawRecord("isbn", "978-3-642-24567-1", null));
		innerRecords.records.add(new RawRecord("booktitle", "Trans. HiPEAC", null));
		innerRecords.records.add(new RawRecord("url", "db/journals/thipeac/thipeac4.html", null));

		rawRecordProceeding1.key = "proceedings";
		rawRecordProceeding1.value = innerRecords;

		return rawRecordProceeding1;
	}

	public static RawRecord getRawRecordProceeding2() {
		/*
		 * <proceedings key="conf/dexa/2016-1" mdate="2016-08-08"> <editor>Sven
		 * Hartmann</editor> <editor>Hui Ma</editor> <title> Database and Expert
		 * Systems Applications - 27th International Conference, DEXA 2016,
		 * Porto, Portugal, September 5-8, 2016, Proceedings, Part I </title>
		 * <booktitle>DEXA (1)</booktitle> <publisher>Springer</publisher>
		 * <year>2016</year> <series href="db/journals/lncs.html">Lecture Notes
		 * in Computer Science</series> <volume>9827</volume>
		 * <isbn>978-3-319-44402-4</isbn>
		 * <ee>http://dx.doi.org/10.1007/978-3-319-44403-1</ee>
		 * <url>db/conf/dexa/dexa2016-1.html</url> </proceedings>
		 */

		RawRecord rawRecordProceeding2 = new RawRecord();
		rawRecordProceeding2.attributes.add(new RawAttribute("key", "conf/dexa/2016-1"));

		RawRecordCollection innerRecords = new RawRecordCollection();
		innerRecords.records.add(new RawRecord("editor", "Sven Hartmann", null));
		innerRecords.records.add(new RawRecord("editor", "Hui Ma", null));
		innerRecords.records.add(new RawRecord("title",
				"Database and Expert Systems Applications - 27th International Conference, DEXA 2016, Porto, Portugal, September 5-8, 2016, Proceedings, Part I ",
				null));
		innerRecords.records.add(new RawRecord("volume", "9827", null));
		innerRecords.records.add(new RawRecord("booktitle", "DEXA", null));
		innerRecords.records.add(new RawRecord("year", "2016", null));
		innerRecords.records.add(new RawRecord("publisher", "Springer", null));
		RawRecord seriesRecord = new RawRecord();
		seriesRecord.attributes.add(new RawAttribute("href", "db/journals/lncs.html"));
		seriesRecord.key = "series";
		seriesRecord.value = "Lecture Notes in Computer Science";
		innerRecords.records.add(seriesRecord);
		innerRecords.records.add(new RawRecord("ee", "http://dx.doi.org/10.1007/978-3-319-44403-1", null));
		innerRecords.records.add(new RawRecord("isbn", "978-3-319-44402-4", null));
		innerRecords.records.add(new RawRecord("url", "db/conf/dexa/dexa2016-1.html", null));

		rawRecordProceeding2.key = "proceedings";
		rawRecordProceeding2.value = innerRecords;

		return rawRecordProceeding2;
	}

	public static RawRecord getRawRecordAuthor1() {
		/*
		 * <www mdate="2010-10-27" key="homepages/24/8638"> <author>Chaoping
		 * Wang</author> <title>Home Page</title> </www>
		 */

		RawRecord rawRecordAuthor1 = new RawRecord();
		rawRecordAuthor1.attributes.add(new RawAttribute("key", "homepages/24/8638"));

		RawRecordCollection innerRecords = new RawRecordCollection();
		innerRecords.records.add(new RawRecord("author", "Chaoping Wang", null));
		innerRecords.records.add(new RawRecord("title", "Home Page", null));

		rawRecordAuthor1.key = "author";
		rawRecordAuthor1.value = innerRecords;

		return rawRecordAuthor1;
	}

	public static RawRecord getRawRecordAuthor2() {
		/*
		 * <www mdate="2017-02-01" key="homepages/24/7521"> <author>Myounghoon
		 * Jeon</author> <author>Myounghoon Philart Jeon</author> <title>Home
		 * Page</title>
		 * <url>http://www.mtu.edu/cls/department/people/faculty-allied/jeon/</
		 * url> <note type="affiliation">Michigan Tech</note> </www>
		 */

		RawRecord rawRecordAuthor2 = new RawRecord();
		rawRecordAuthor2.attributes.add(new RawAttribute("key", "homepages/24/7521"));

		RawRecordCollection innerRecords = new RawRecordCollection();
		innerRecords.records.add(new RawRecord("author", "Myounghoon Jeon", null));
		innerRecords.records.add(new RawRecord("author", "Myounghoon Philart Jeon", null));
		innerRecords.records.add(new RawRecord("title", "Home Page", null));
		innerRecords.records
				.add(new RawRecord("url", "http://www.mtu.edu/cls/department/people/faculty-allied/jeon/", null));
		RawRecord innerAuthorNote = new RawRecord();
		innerAuthorNote.attributes.add(new RawAttribute("type", "affiliation"));
		innerAuthorNote.key = "note";
		innerAuthorNote.value = "Michigan Tech";
		innerRecords.records.add(innerAuthorNote);

		rawRecordAuthor2.key = "author";
		rawRecordAuthor2.value = innerRecords;

		return rawRecordAuthor2;
	}

	// Corresponding PublicationEntity objects
	public static JournalPublication getArticle1() {
		JournalPublication thisArticle = new JournalPublication();
		thisArticle.id = 324;
		thisArticle.title = "The Clean Termination of Iterative Programs.";
		thisArticle.url = "db/journals/acta/acta16.html#Blikle81";
		thisArticle.year = 1981;
		thisArticle.volume = 16;

		ArrayList<String> ee = new ArrayList<String>();
		ee.add("http://dx.doi.org/10.1007/BF00261259");
		thisArticle.externalReference = ee;

		Author thisAuthor = new Author();
		thisAuthor.id = 23432;
		thisAuthor.hasBeenEditor = false;
		thisAuthor.hasBeenPC = false;
		thisAuthor.name = "Andrzej Blikle";
		ArrayList<Author> authors = new ArrayList<>();
		authors.add(thisAuthor);
		thisArticle.authors = authors;

		Journal thisJournal = new Journal();
		thisJournal.title = "Acta Inf.";

		thisArticle.journal = thisJournal;

		return thisArticle;
	}

	public static JournalPublication getArticle2() {
		JournalPublication thisArticle = new JournalPublication();
		thisArticle.id = 324;
		thisArticle.title = "Nonfinite Axiomatizability of the Equational Theory of Shuffle.";
		thisArticle.url = "db/journals/acta/acta35.html#EsikB98";
		thisArticle.year = 1998;
		thisArticle.volume = 35;
		thisArticle.issueNumber = 6;

		ArrayList<String> ee = new ArrayList<String>();
		ee.add("http://link.springer.de/link/service/journals/00236/bibs/8035006/80350505.htm");
		thisArticle.externalReference = ee;

		Author thisAuthor1 = new Author();
		thisAuthor1.id = 1001;
		thisAuthor1.hasBeenEditor = false;
		thisAuthor1.hasBeenPC = false;
		thisAuthor1.name = "Zoltn sik";

		Author thisAuthor2 = new Author();
		thisAuthor2.id = 1002;
		thisAuthor2.hasBeenEditor = false;
		thisAuthor2.hasBeenPC = false;
		thisAuthor2.name = "Michael Bertol";

		ArrayList<Author> authors = new ArrayList<>();
		authors.add(thisAuthor1);
		authors.add(thisAuthor2);
		thisArticle.authors = authors;

		Journal thisJournal = new Journal();
		thisJournal.title = "Acta Inf.";

		thisArticle.journal = thisJournal;

		return thisArticle;
	}

	public static ConferencePublication getConferencePublication1() {
		ConferencePublication thisConferencePub = new ConferencePublication();
		thisConferencePub.id = 5555;
		thisConferencePub.title = "LBP Based Line-Wise Script Identification.";
		thisConferencePub.year = 2013;
		thisConferencePub.url = "db/conf/icdar/icdar2013.html#FerrerMP13";
		thisConferencePub.bookTitle = "ICDAR";
		thisConferencePub.crossRef = "conf/icdar/2013";

		ArrayList<String> ee = new ArrayList<String>();
		ee.add("http://dx.doi.org/10.1109/ICDAR.2013.81");
		ee.add("http://doi.ieeecomputersociety.org/10.1109/ICDAR.2013.81");
		thisConferencePub.externalReference = ee;

		Author thisAuthor1 = new Author();
		thisAuthor1.id = 1003;
		thisAuthor1.hasBeenEditor = false;
		thisAuthor1.hasBeenPC = false;
		thisAuthor1.name = "Miguel A. Ferrer";

		Author thisAuthor2 = new Author();
		thisAuthor2.id = 1004;
		thisAuthor2.hasBeenEditor = false;
		thisAuthor2.hasBeenPC = false;
		thisAuthor2.name = "Aythami Morales";

		Author thisAuthor3 = new Author();
		thisAuthor3.id = 1005;
		thisAuthor3.hasBeenEditor = false;
		thisAuthor3.hasBeenPC = false;
		thisAuthor3.name = "Umapada Pal";

		ArrayList<Author> authors = new ArrayList<>();
		authors.add(thisAuthor1);
		authors.add(thisAuthor2);
		authors.add(thisAuthor3);
		thisConferencePub.authors = authors;

		return thisConferencePub;
	}

	public static ConferencePublication getConferencePublication2() {
		ConferencePublication thisConferencePub = new ConferencePublication();
		thisConferencePub.id = 5556;
		thisConferencePub.title = "Arabic handwritten document preprocessing and recognition.";
		thisConferencePub.year = 2015;
		thisConferencePub.url = "db/conf/icdar/icdar2013.html#FerrerMP13";
		thisConferencePub.bookTitle = "ICDAR";

		ArrayList<String> ee = new ArrayList<String>();
		ee.add("http://dx.doi.org/10.1109/ICDAR.2015.7333802");
		ee.add("http://doi.ieeecomputersociety.org/10.1109/ICDAR.2015.7333802");
		thisConferencePub.externalReference = ee;

		thisConferencePub.crossRef = "conf/icdar/2015";
		thisConferencePub.url = "db/conf/icdar/icdar2015.html#ChammasML15";

		Author thisAuthor1 = new Author();
		thisAuthor1.id = 1006;
		thisAuthor1.hasBeenEditor = false;
		thisAuthor1.hasBeenPC = false;
		thisAuthor1.name = "Edgard Chammas";

		Author thisAuthor2 = new Author();
		thisAuthor2.id = 1007;
		thisAuthor2.hasBeenEditor = false;
		thisAuthor2.hasBeenPC = false;
		thisAuthor2.name = "Chafic Mokbel";

		Author thisAuthor3 = new Author();
		thisAuthor3.id = 1008;
		thisAuthor3.hasBeenEditor = false;
		thisAuthor3.hasBeenPC = false;
		thisAuthor3.name = "Laurence Likforman-Sulem";

		ArrayList<Author> authors = new ArrayList<>();
		authors.add(thisAuthor1);
		authors.add(thisAuthor2);
		authors.add(thisAuthor3);
		thisConferencePub.authors = authors;

		return thisConferencePub;
	}

	public static Journal getJournal1() {
		Journal thisJournal = new Journal();
		thisJournal.title = "Transactions on High-Performance Embedded Architectures and Compilers IV";
		thisJournal.isbn = "978-3-642-24567-1";
		thisJournal.key = "journals/thipeac/2011-4";
		thisJournal.publisher = "Springer";
		thisJournal.year = 2011;
		thisJournal.volume = 6760;
		thisJournal.seriesTitle = "Lecture Notes in Computer Science";
		thisJournal.seriesUrl = "db/journals/lncs.html";
		thisJournal.url = "db/journals/thipeac/thipeac4.html";
		thisJournal.bookTitle = "Trans. HiPEAC";

		Author thisEditor = new Author();
		thisEditor.id = 1008;
		thisEditor.hasBeenEditor = false;
		thisEditor.hasBeenPC = false;
		thisEditor.name = "Per Stenstr&ouml;m";

		ArrayList<Author> editors = new ArrayList<>();
		editors.add(thisEditor);
		thisJournal.editors = editors;

		return thisJournal;

	}

	public static Conference getConference1() {
		Conference thisConference = new Conference();
		thisConference.title = "Database and Expert Systems Applications - 27th International Conference, DEXA 2016, Porto, Portugal, September 5-8, 2016, Proceedings, Part I ";
		thisConference.isbn = "978-3-319-44402-4";
		thisConference.key = "conf/dexa/2016-1";
		thisConference.publisher = "Springer";
		thisConference.year = 2016;
		thisConference.volume = 9827;
		thisConference.seriesTitle = "Lecture Notes in Computer Science";
		thisConference.seriesUrl = "db/journals/lncs.html";
		thisConference.url = "db/conf/dexa/dexa2016-1.html";
		thisConference.bookTitle = "DEXA";

		Author thisEditor1 = new Author();
		thisEditor1.id = 1013;
		thisEditor1.hasBeenEditor = false;
		thisEditor1.hasBeenPC = false;
		thisEditor1.name = "Sven Hartmann";

		Author thisEditor2 = new Author();
		thisEditor2.id = 1013;
		thisEditor2.hasBeenEditor = false;
		thisEditor2.hasBeenPC = false;
		thisEditor2.name = "Hui Ma";

		ArrayList<Author> editors = new ArrayList<>();
		editors.add(thisEditor1);
		editors.add(thisEditor2);
		thisConference.editors = editors;

		return thisConference;
	}

	public static Author getAuthor1() {
		Author thisAuthor = new Author();
		thisAuthor.hasBeenEditor = false;
		thisAuthor.hasBeenPC = false;
		thisAuthor.name = "Chaoping Wang";
		thisAuthor.key = "homepages/24/8638";
		ArrayList<String> urls = new ArrayList<>();
		thisAuthor.url = urls;
		return thisAuthor;
	}

	public static Author getAuthor2() {
		Author thisAuthor = new Author();
		thisAuthor.hasBeenEditor = false;
		thisAuthor.hasBeenPC = false;
		thisAuthor.name = "Myounghoon Jeon";
		thisAuthor.alias = "Myounghoon Philart Jeon";
		thisAuthor.key = "homepages/24/7521";
		ArrayList<String> urls = new ArrayList<>();
		urls.add("http://www.mtu.edu/cls/department/people/faculty-allied/jeon/");
		thisAuthor.url = urls;
		return thisAuthor;
	}
}
