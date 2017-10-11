package team1.tests.frontend;

import static org.junit.Assert.*;

import java.io.File;
import javax.xml.parsers.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.Test;

import team1.searchengine.frontend.*;

public class XMLParserTest {
	
	@Test
	public void testParser() {
		
		XMLParser myParser = new XMLParser();
		
		//Test-1
		RawRecordCollection rrc1 = myParser.parse("XMLFiles/article1.xml");
		String key1 = rrc1.records.get(0).key;
		String attKey1 = rrc1.records.get(0).attributes.get(0).key;
		String attVal1 = rrc1.records.get(0).attributes.get(0).value;
		RawRecordCollection inRrc1 = (RawRecordCollection) rrc1.records.get(0).value;
		String inKey1 = inRrc1.records.get(0).key;
		String inVal1 = (String) inRrc1.records.get(0).value;
		String inKey2 = inRrc1.records.get(1).key;
		String inVal2 = (String) inRrc1.records.get(1).value;
		String inKey3 = inRrc1.records.get(2).key;
		String inVal3 = (String) inRrc1.records.get(2).value;
		String inKey4 = inRrc1.records.get(3).key;
		String inVal4 = (String) inRrc1.records.get(3).value;
		String inKey5 = inRrc1.records.get(4).key;
		String inVal5 = (String) inRrc1.records.get(4).value;
		String inKey6 = inRrc1.records.get(5).key;
		String inVal6 = (String) inRrc1.records.get(5).value;
		String inKey7 = inRrc1.records.get(6).key;
		String inVal7 = (String) inRrc1.records.get(6).value;
		String inKey8 = inRrc1.records.get(7).key;
		String inVal8 = (String) inRrc1.records.get(7).value;
		String inKey9 = inRrc1.records.get(8).key;
		String inVal9 = (String) inRrc1.records.get(8).value;
		assertEquals("Test-FE-1-1: Article key incorrect from pasring", key1, "article");
		assertEquals("Test-FE-1-2: Article attribute key incorrect from pasring", attKey1, "key");
		assertEquals("Test-FE-1-3: Article attribute value incorrect from pasring", attVal1, "journals/acta/GoodmanS83");
		assertEquals("Test-FE-1-4: Article value's author key incorrect from pasring", inKey1, "author");
		assertEquals("Test-FE-1-5: Article value's author value incorrect from pasring", inVal1, "Nathan Goodman");
		assertEquals("Test-FE-1-6: Article value's author key incorrect from pasring", inKey2, "author");
		assertEquals("Test-FE-1-7: Article value's author value incorrect from pasring", inVal2, "Oded Shmueli");
		assertEquals("Test-FE-1-8: Article value's title key incorrect from pasring", inKey3, "title");
		assertEquals("Test-FE-1-9: Article value's title value incorrect from pasring", inVal3, "NP-complete Problems Simplified on Tree Schemas.");
		assertEquals("Test-FE-1-10: Article value's page key incorrect from pasring", inKey4, "pages");
		assertEquals("Test-FE-1-11: Article value's page value incorrect from pasring", inVal4, "171-178");
		assertEquals("Test-FE-1-12: Article value's year key incorrect from pasring", inKey5, "year");
		assertEquals("Test-FE-1-13: Article value's year value incorrect from pasring", inVal5, "1983");
		assertEquals("Test-FE-1-14: Article value's volume key incorrect from pasring", inKey6, "volume");
		assertEquals("Test-FE-1-15: Article value's volume value incorrect from pasring", inVal6, "20");
		assertEquals("Test-FE-1-16: Article value's journal key incorrect from pasring", inKey7, "journal");
		assertEquals("Test-FE-1-17: Article value's journal value incorrect from pasring", inVal7, "Acta Inf.");
		assertEquals("Test-FE-1-18: Article value's url key incorrect from pasring", inKey8, "url");
		assertEquals("Test-FE-1-19: Article value's url value incorrect from pasring", inVal8, "db/journals/acta/acta20.html#GoodmanS83");
		assertEquals("Test-FE-1-20: Article value's ee key incorrect from pasring", inKey9, "ee");
		assertEquals("Test-FE-1-21: Article value's ee value incorrect from pasring", inVal9, "http://dx.doi.org/10.1007/BF00289414");
		
		//Test-2
		RawRecordCollection rrc2 = myParser.parse("XMLFiles/proceedings1.xml");
		String key2 = rrc2.records.get(0).key;
		String attKey2 = rrc2.records.get(0).attributes.get(0).key;
		String attVal2 = rrc2.records.get(0).attributes.get(0).value;
		RawRecordCollection inRrc2 = (RawRecordCollection) rrc2.records.get(0).value;
		String inKey2_1 = inRrc2.records.get(0).key;
		String inVal2_1 = (String) inRrc2.records.get(0).value;
		String inKey2_2 = inRrc2.records.get(1).key;
		String inVal2_2 = (String) inRrc2.records.get(1).value;
		String inKey2_3 = inRrc2.records.get(2).key;
		String inVal2_3 = (String) inRrc2.records.get(2).value;
		String inKey2_4 = inRrc2.records.get(3).key;
		String inVal2_4 = (String) inRrc2.records.get(3).value;
		String inKey2_5 = inRrc2.records.get(4).key;
		String inVal2_5 = (String) inRrc2.records.get(4).value;
		String inKey2_6 = inRrc2.records.get(5).key;
		String inVal2_6 = (String) inRrc2.records.get(5).value;
		String inKey2_7 = inRrc2.records.get(6).key;
		String inVal2_7 = (String) inRrc2.records.get(6).value;
		String inKey2_8 = inRrc2.records.get(7).key;
		String inVal2_8 = (String) inRrc2.records.get(7).value;
		String inKey2_9 = inRrc2.records.get(8).key;
		String inVal2_9 = (String) inRrc2.records.get(8).value;
		String inKey2_10 = inRrc2.records.get(9).key;
		String inVal2_10 = (String) inRrc2.records.get(9).value;
		String inKey2_11 = inRrc2.records.get(10).key;
		String inVal2_11 = (String) inRrc2.records.get(10).value;
		String inKey2_12 = inRrc2.records.get(11).key;
		String inVal2_12 = (String) inRrc2.records.get(11).value;
		assertEquals("Test-FE-2-1: proceedings key incorrect from pasring", key2, "proceedings");
		assertEquals("Test-FE-2-2: proceedings attribute key incorrect from pasring", attKey2, "key");
		assertEquals("Test-FE-2-3: proceedings attribute value incorrect from pasring", attVal2, "conf/isnn/2014");
		assertEquals("Test-FE-2-4: proceedings value's editor key incorrect from pasring", inKey2_1, "editor");
		assertEquals("Test-FE-2-5: proceedings value's editor value incorrect from pasring", inVal2_1, "Zhigang Zeng");
		assertEquals("Test-FE-2-6: proceedings value's editor key incorrect from pasring", inKey2_2, "editor");
		assertEquals("Test-FE-2-7: proceedings value's editor value incorrect from pasring", inVal2_2, "Yangmin Li");
		assertEquals("Test-FE-2-8: proceedings value's editor key incorrect from pasring", inKey2_3, "editor");
		assertEquals("Test-FE-2-9: proceedings value's editor value incorrect from pasring", inVal2_3, "Irwin King");
		assertEquals("Test-FE-2-10: proceedings value's title key incorrect from pasring", inKey2_4, "title");
		assertEquals("Test-FE-2-11: proceedings value's title value incorrect from pasring", inVal2_4, "Advances in Neural Networks - ISNN 2014 - 11th International Symposium on Neural Networks, ISNN 2014, Hong Kong and Macao, China, November 28- December 1, 2014. Proceedings");
		assertEquals("Test-FE-2-12: proceedings value's year key incorrect from pasring", inKey2_5, "year");
		assertEquals("Test-FE-2-13: proceedings value's year value incorrect from pasring", inVal2_5, "2014");
		assertEquals("Test-FE-2-14: proceedings value's publisher key incorrect from pasring", inKey2_6, "publisher");
		assertEquals("Test-FE-2-15: proceedings value's publisher value incorrect from pasring", inVal2_6, "Springer");
		assertEquals("Test-FE-2-16: proceedings value's series key incorrect from pasring", inKey2_7, "series");
		assertEquals("Test-FE-2-17: proceedings value's series value incorrect from pasring", inVal2_7, "Lecture Notes in Computer Science");
		assertEquals("Test-FE-2-18: proceedings value's volume key incorrect from pasring", inKey2_8, "volume");
		assertEquals("Test-FE-2-19: proceedings value's volume value incorrect from pasring", inVal2_8, "8866");
		assertEquals("Test-FE-2-20: proceedings value's ee key incorrect from pasring", inKey2_9, "ee");
		assertEquals("Test-FE-2-21: proceedings value's ee value incorrect from pasring", inVal2_9, "http://dx.doi.org/10.1007/978-3-319-12436-0");
		assertEquals("Test-FE-2-22: proceedings value's isbn key incorrect from pasring", inKey2_10, "isbn");
		assertEquals("Test-FE-2-23: proceedings value's isbn value incorrect from pasring", inVal2_10, "978-3-319-12435-3");
		assertEquals("Test-FE-2-24: proceedings value's booktitle key incorrect from pasring", inKey2_11, "booktitle");
		assertEquals("Test-FE-2-25: proceedings value's booktitle value incorrect from pasring", inVal2_11, "ISNN");
		assertEquals("Test-FE-2-26: proceedings value's url key incorrect from pasring", inKey2_12, "url");
		assertEquals("Test-FE-2-27: proceedings value's url value incorrect from pasring", inVal2_12, "db/conf/isnn/isnn2014.html");
		
		//Test-3
		RawRecordCollection rrc3 = myParser.parse("XMLFiles/inproceedings1.xml");
		String key3 = rrc3.records.get(0).key;
		String attKey3 = rrc3.records.get(0).attributes.get(0).key;
		String attVal3 = rrc3.records.get(0).attributes.get(0).value;
		RawRecordCollection inRrc3 = (RawRecordCollection) rrc3.records.get(0).value;
		String inKey3_1 = inRrc3.records.get(0).key;
		String inVal3_1 = (String) inRrc3.records.get(0).value;
		String inKey3_2 = inRrc3.records.get(1).key;
		String inVal3_2 = (String) inRrc3.records.get(1).value;
		String inKey3_3 = inRrc3.records.get(2).key;
		String inVal3_3 = (String) inRrc3.records.get(2).value;
		String inKey3_4 = inRrc3.records.get(3).key;
		String inVal3_4 = (String) inRrc3.records.get(3).value;
		String inKey3_5 = inRrc3.records.get(4).key;
		String inVal3_5 = (String) inRrc3.records.get(4).value;
		String inKey3_6 = inRrc3.records.get(5).key;
		String inVal3_6 = (String) inRrc3.records.get(5).value;
		String inKey3_7 = inRrc3.records.get(6).key;
		String inVal3_7 = (String) inRrc3.records.get(6).value;
		String inKey3_8 = inRrc3.records.get(7).key;
		String inVal3_8 = (String) inRrc3.records.get(7).value;
		String inKey3_9 = inRrc3.records.get(8).key;
		String inVal3_9 = (String) inRrc3.records.get(8).value;
		String inKey3_10 = inRrc3.records.get(9).key;
		String inVal3_10 = (String) inRrc3.records.get(9).value;
		
		assertEquals("Test-FE-3-1: inproceedings key incorrect from pasring", key3, "inproceedings");
		assertEquals("Test-FE-3-2: inproceedings attribute key incorrect from pasring", attKey3, "key");
		assertEquals("Test-FE-3-3: inproceedings attribute value incorrect from pasring", attVal3, "conf/nips/KambhaltlaL93");
		assertEquals("Test-FE-3-4: inproceedings value's author key incorrect from pasring", inKey3_1, "author");
		assertEquals("Test-FE-3-5: inproceedings value's author value incorrect from pasring", inVal3_1, "Nanda Kambhatla");
		assertEquals("Test-FE-3-6: inproceedings value's author key incorrect from pasring", inKey3_2, "author");
		assertEquals("Test-FE-3-7: inproceedings value's author value incorrect from pasring", inVal3_2, "Todd K. Leen");
		assertEquals("Test-FE-3-8: inproceedings value's title key incorrect from pasring", inKey3_3, "title");
		assertEquals("Test-FE-3-9: inproceedings value's title value incorrect from pasring", inVal3_3, "Fast Non-Linear Dimension Reduction.");
		assertEquals("Test-FE-3-10: inproceedings value's pages key incorrect from pasring", inKey3_4, "pages");
		assertEquals("Test-FE-3-11: inproceedings value's pages value incorrect from pasring", inVal3_4, "152-159");
		assertEquals("Test-FE-3-12: inproceedings value's ee key incorrect from pasring", inKey3_5, "ee");
		assertEquals("Test-FE-3-13: inproceedings value's ee value incorrect from pasring", inVal3_5, "http://papers.nips.cc/paper/825-fast-non-linear-dimension-reduction");
		assertEquals("Test-FE-3-14: inproceedings value's ee key incorrect from pasring", inKey3_6, "ee");
		assertEquals("Test-FE-3-15: inproceedings value's ee value incorrect from pasring", inVal3_6, "http://nips.djvuzone.org/djvu/nips06/0152.djvu");
		assertEquals("Test-FE-3-16: inproceedings value's year key incorrect from pasring", inKey3_7, "year");
		assertEquals("Test-FE-3-17: inproceedings value's year value incorrect from pasring", inVal3_7, "1993");
		assertEquals("Test-FE-3-18: inproceedings value's crossref key incorrect from pasring", inKey3_8, "crossref");
		assertEquals("Test-FE-3-19: inproceedings value's crossref value incorrect from pasring", inVal3_8, "conf/nips/1993");
		assertEquals("Test-FE-3-20: inproceedings value's booktitle key incorrect from pasring", inKey3_9, "booktitle");
		assertEquals("Test-FE-3-21: inproceedings value's booktitle value incorrect from pasring", inVal3_9, "NIPS");
		assertEquals("Test-FE-3-22: inproceedings value's url key incorrect from pasring", inKey3_10, "url");
		assertEquals("Test-FE-3-23: inproceedings value's url value incorrect from pasring", inVal3_10, "db/conf/nips/nips1993.html#KambhaltlaL93");
		
		//Test-4
		RawRecordCollection rrc4 = myParser.parse("XMLFiles/author1.xml");
		String key4 = rrc4.records.get(0).key;
		String attKey4 = rrc4.records.get(0).attributes.get(0).key;
		String attVal4 = rrc4.records.get(0).attributes.get(0).value;
		RawRecordCollection inRrc4 = (RawRecordCollection) rrc4.records.get(0).value;
		String inKey4_1 = inRrc4.records.get(0).key;
		String inVal4_1 = (String) inRrc4.records.get(0).value;
		String inKey4_2 = inRrc4.records.get(1).key;
		String inVal4_2 = (String) inRrc4.records.get(1).value;
		String inKey4_3 = inRrc4.records.get(2).key;
		String inVal4_3 = (String) inRrc4.records.get(2).value;
		String inKey4_4 = inRrc4.records.get(3).key;
		String inVal4_4 = (String) inRrc4.records.get(3).value;
		String inKey4_5 = inRrc4.records.get(4).key;
		String inVal4_5 = (String) inRrc4.records.get(4).value;
		String inKey4_6 = inRrc4.records.get(5).key;
		String inVal4_6 = (String) inRrc4.records.get(5).value;
		
		assertEquals("Test-FE-4-1: author key incorrect from pasring", key4, "author");
		assertEquals("Test-FE-4-2: author attribute key incorrect from pasring", attKey4, "key");
		assertEquals("Test-FE-4-3: author attribute value incorrect from pasring", attVal4, "homepages/140/4242");
		assertEquals("Test-FE-4-4: author value's author key incorrect from pasring", inKey4_1, "author");
		assertEquals("Test-FE-4-5: author value's author value incorrect from pasring", inVal4_1, "Megan Hardy Frankosky");
		assertEquals("Test-FE-4-6: author value's author key incorrect from pasring", inKey4_2, "author");
		assertEquals("Test-FE-4-7: author value's author value incorrect from pasring", inVal4_2, "Megan Frankosky");
		assertEquals("Test-FE-4-8: author value's author key incorrect from pasring", inKey4_3, "author");
		assertEquals("Test-FE-4-9: author value's author value incorrect from pasring", inVal4_3, "Megan Hardy");
		assertEquals("Test-FE-4-10: author value's title key incorrect from pasring", inKey4_4, "title");
		assertEquals("Test-FE-4-11: author value's title value incorrect from pasring", inVal4_4, "Home Page");
		assertEquals("Test-FE-4-12: author value's url key incorrect from pasring", inKey4_5, "url");
		assertEquals("Test-FE-4-13: author value's url value incorrect from pasring", inVal4_5, "https://scholar.google.com/citations?user=_l_BqNgAAAAJ");
		assertEquals("Test-FE-4-14: author value's note key incorrect from pasring", inKey4_6, "note");
		assertEquals("Test-FE-4-15: author value's note value incorrect from pasring", inVal4_6, "North Carolina State University");
		
		//Test-5
		RawRecordCollection rrc5 = myParser.parse("XMLFiles/book1.xml");
		String key5 = rrc5.records.get(0).key;
		String attKey5 = rrc5.records.get(0).attributes.get(0).key;
		String attVal5 = rrc5.records.get(0).attributes.get(0).value;
		RawRecordCollection inRrc5 = (RawRecordCollection) rrc5.records.get(0).value;
		String inKey5_1 = inRrc5.records.get(0).key;
		String inVal5_1 = (String) inRrc5.records.get(0).value;
		String inKey5_2 = inRrc5.records.get(1).key;
		String inVal5_2 = (String) inRrc5.records.get(1).value;
		String inKey5_3 = inRrc5.records.get(2).key;
		String inVal5_3 = (String) inRrc5.records.get(2).value;
		String inKey5_4 = inRrc5.records.get(3).key;
		String inVal5_4 = (String) inRrc5.records.get(3).value;
		String inKey5_5 = inRrc5.records.get(4).key;
		String inVal5_5 = (String) inRrc5.records.get(4).value;
		String inKey5_6 = inRrc5.records.get(5).key;
		String inVal5_6 = (String) inRrc5.records.get(5).value;
		String inKey5_7 = inRrc5.records.get(6).key;
		String inVal5_7 = (String) inRrc5.records.get(6).value;
		String inKey5_8 = inRrc5.records.get(7).key;
		String inVal5_8 = (String) inRrc5.records.get(7).value;
		String inKey5_9 = inRrc5.records.get(8).key;
		String inVal5_9 = (String) inRrc5.records.get(8).value;
		String inKey5_10 = inRrc5.records.get(9).key;
		String inVal5_10 = (String) inRrc5.records.get(9).value;
		
		assertEquals("Test-FE-5-1: book key incorrect from pasring", key5, "book");
		assertEquals("Test-FE-5-2: book attribute key incorrect from pasring", attKey5, "key");
		assertEquals("Test-FE-5-3: book attribute value incorrect from pasring", attVal5, "series/wn/ZouZ16");
		assertEquals("Test-FE-5-4: book value's author key incorrect from pasring", inKey5_1, "author");
		assertEquals("Test-FE-5-5: book value's author value incorrect from pasring", inVal5_1, "Yulong Zou");
		assertEquals("Test-FE-5-6: book value's author key incorrect from pasring", inKey5_2, "author");
		assertEquals("Test-FE-5-7: book value's author value incorrect from pasring", inVal5_2, "Jia Zhu");
		assertEquals("Test-FE-5-8: book value's title key incorrect from pasring", inKey5_3, "title");
		assertEquals("Test-FE-5-9: book value's title value incorrect from pasring", inVal5_3, "Physical-Layer Security for Cooperative Relay Networks");
		assertEquals("Test-FE-5-10: book value's publisher key incorrect from pasring", inKey5_4, "publisher");
		assertEquals("Test-FE-5-11: book value's publisher value incorrect from pasring", inVal5_4, "Springer");
		assertEquals("Test-FE-5-12: book value's year key incorrect from pasring", inKey5_5, "year");
		assertEquals("Test-FE-5-13: book value's year value incorrect from pasring", inVal5_5, "2016");
		assertEquals("Test-FE-5-14: book value's pages key incorrect from pasring", inKey5_6, "pages");
		assertEquals("Test-FE-5-15: book value's pages value incorrect from pasring", inVal5_6, "1-104");
		assertEquals("Test-FE-5-16: book value's series key incorrect from pasring", inKey5_7, "series");
		assertEquals("Test-FE-5-17: book value's series value incorrect from pasring", inVal5_7, "Wireless Networks");
		assertEquals("Test-FE-5-18: book value's isbn key incorrect from pasring", inKey5_8, "isbn");
		assertEquals("Test-FE-5-19: book value's isbn value incorrect from pasring", inVal5_8, "978-3-319-31173-9");
		assertEquals("Test-FE-5-20: book value's isbn key incorrect from pasring", inKey5_9, "isbn");
		assertEquals("Test-FE-5-21: book value's isbn value incorrect from pasring", inVal5_9, "978-3-319-31174-6");
		assertEquals("Test-FE-5-22: book value's ee key incorrect from pasring", inKey5_10, "ee");
		assertEquals("Test-FE-5-23: book value's ee value incorrect from pasring", inVal5_10, "http://dx.doi.org/10.1007/978-3-319-31174-6");
		
		//Test-6
		RawRecordCollection rrc6 = myParser.parse("XMLFiles/incollection1.xml");
		String key6 = rrc6.records.get(0).key;
		String attKey6 = rrc6.records.get(0).attributes.get(0).key;
		String attVal6 = rrc6.records.get(0).attributes.get(0).value;
		RawRecordCollection inRrc6 = (RawRecordCollection) rrc6.records.get(0).value;
		String inKey6_1 = inRrc6.records.get(0).key;
		String inVal6_1 = (String) inRrc6.records.get(0).value;
		String inKey6_2 = inRrc6.records.get(1).key;
		String inVal6_2 = (String) inRrc6.records.get(1).value;
		String inKey6_3 = inRrc6.records.get(2).key;
		String inVal6_3 = (String) inRrc6.records.get(2).value;
		String inKey6_4 = inRrc6.records.get(3).key;
		String inVal6_4 = (String) inRrc6.records.get(3).value;
		String inKey6_5 = inRrc6.records.get(4).key;
		String inVal6_5 = (String) inRrc6.records.get(4).value;
		String inKey6_6 = inRrc6.records.get(5).key;
		String inVal6_6 = (String) inRrc6.records.get(5).value;
		String inKey6_7 = inRrc6.records.get(6).key;
		String inVal6_7 = (String) inRrc6.records.get(6).value;
		String inKey6_8 = inRrc6.records.get(7).key;
		String inVal6_8 = (String) inRrc6.records.get(7).value;
		String inKey6_9 = inRrc6.records.get(8).key;
		String inVal6_9 = (String) inRrc6.records.get(8).value;
		
		assertEquals("Test-FE-6-1: incollection key incorrect from pasring", key6, "incollection");
		assertEquals("Test-FE-6-2: incollection attribute key incorrect from pasring", attKey6, "key");
		assertEquals("Test-FE-6-3: incollection attribute value incorrect from pasring", attVal6, "series/rnti/CouturierA09");
		assertEquals("Test-FE-6-4: incollection value's author key incorrect from pasring", inKey6_1, "author");
		assertEquals("Test-FE-6-6: incollection value's author key incorrect from pasring", inKey6_2, "author");
		assertEquals("Test-FE-6-7: incollection value's author value incorrect from pasring", inVal6_2, "Saddo Ag Almouloud");
		assertEquals("Test-FE-6-8: incollection value's title key incorrect from pasring", inKey6_3, "title");
		assertEquals("Test-FE-6-9: incollection value's title value incorrect from pasring", inVal6_3, "Historique et fonctionnalit");
		assertEquals("Test-FE-6-10: incollection value's pages key incorrect from pasring", inKey6_4, "pages");
		assertEquals("Test-FE-6-11: incollection value's pages value incorrect from pasring", inVal6_4, "279-294");
		assertEquals("Test-FE-6-12: incollection value's year key incorrect from pasring", inKey6_5, "year");
		assertEquals("Test-FE-6-13: incollection value's year value incorrect from pasring", inVal6_5, "2009");
		assertEquals("Test-FE-6-14: incollection value's booktitle key incorrect from pasring", inKey6_6, "booktitle");
		assertEquals("Test-FE-6-15: incollection value's booktitle value incorrect from pasring", inVal6_6, "Analyse Statistique Implicative");
		assertEquals("Test-FE-6-16: incollection value's ee key incorrect from pasring", inKey6_7, "ee");
		assertEquals("Test-FE-6-17: incollection value's ee value incorrect from pasring", inVal6_7, "http://editions-rnti.fr/?inprocid=1000856");
		assertEquals("Test-FE-6-18: incollection value's crossref key incorrect from pasring", inKey6_8, "crossref");
		assertEquals("Test-FE-6-19: incollection value's crossref value incorrect from pasring", inVal6_8, "series/rnti/E16");
		assertEquals("Test-FE-6-20: incollection value's url key incorrect from pasring", inKey6_9, "url");
		assertEquals("Test-FE-6-21: incollection value's url value incorrect from pasring", inVal6_9, "db/series/rnti/rnti-E16.html#CouturierA09");
		
		//Test-7
		RawRecordCollection rrc7 = myParser.parse("XMLFiles/phd1.xml");
		String key7 = rrc7.records.get(0).key;
		String attKey7 = rrc7.records.get(0).attributes.get(0).key;
		String attVal7 = rrc7.records.get(0).attributes.get(0).value;
		RawRecordCollection inRrc7 = (RawRecordCollection) rrc7.records.get(0).value;
		String inKey7_1 = inRrc7.records.get(0).key;
		String inVal7_1 = (String) inRrc7.records.get(0).value;
		String inKey7_2 = inRrc7.records.get(1).key;
		String inVal7_2 = (String) inRrc7.records.get(1).value;
		String inKey7_3 = inRrc7.records.get(2).key;
		String inVal7_3 = (String) inRrc7.records.get(2).value;
		String inKey7_4 = inRrc7.records.get(3).key;
		String inVal7_4 = (String) inRrc7.records.get(3).value;
		String inKey7_5 = inRrc7.records.get(4).key;
		String inVal7_5 = (String) inRrc7.records.get(4).value;
		String inKey7_6 = inRrc7.records.get(5).key;
		String inVal7_6 = (String) inRrc7.records.get(5).value;
		
		assertEquals("Test-FE-7-1: phdthesis key incorrect from pasring", key7, "phdthesis");
		assertEquals("Test-FE-7-2: phdthesis attribute key incorrect from pasring", attKey7, "key");
		assertEquals("Test-FE-7-3: phdthesis attribute value incorrect from pasring", attVal7, "phd/Leinen90");
		assertEquals("Test-FE-7-4: phdthesis value's author key incorrect from pasring", inKey7_1, "author");
		assertEquals("Test-FE-7-5: phdthesis value's author value incorrect from pasring", inVal7_1, "Peter Leinen");
		assertEquals("Test-FE-7-6: phdthesis value's title key incorrect from pasring", inKey7_2, "title");
		assertEquals("Test-FE-7-7: phdthesis value's title value incorrect from pasring", inVal7_2, "Ein schneller adaptiver L");
		assertEquals("Test-FE-7-8: phdthesis value's year key incorrect from pasring", inKey7_3, "year");
		assertEquals("Test-FE-7-9: phdthesis value's year value incorrect from pasring", inVal7_3, "1990");
		assertEquals("Test-FE-7-10: phdthesis value's school key incorrect from pasring", inKey7_4, "school");
		assertEquals("Test-FE-7-11: phdthesis value's school value incorrect from pasring", inVal7_4, "Technical University of Dortmund, Germany");
		assertEquals("Test-FE-7-12: phdthesis value's url key incorrect from pasring", inKey7_5, "url");
		assertEquals("Test-FE-7-13: phdthesis value's url value incorrect from pasring", inVal7_5, "http://www.uni-trier.de/~leinen/Research/Papers/Download/diss.ps.gz");
		assertEquals("Test-FE-7-14: phdthesis value's ee key incorrect from pasring", inKey7_6, "ee");
		assertEquals("Test-FE-7-15: phdthesis value's ee value incorrect from pasring", inVal7_6, "http://d-nb.info/910265135");
		
		//Test-8
		RawRecordCollection rrc8 = myParser.parse("XMLFiles/master1.xml");
		String key8 = rrc8.records.get(0).key;
		String attKey8 = rrc8.records.get(0).attributes.get(0).key;
		String attVal8 = rrc8.records.get(0).attributes.get(0).value;
		RawRecordCollection inRrc8 = (RawRecordCollection) rrc8.records.get(0).value;
		String inKey8_1 = inRrc8.records.get(0).key;
		String inVal8_1 = (String) inRrc8.records.get(0).value;
		String inKey8_2 = inRrc8.records.get(1).key;
		String inVal8_2 = (String) inRrc8.records.get(1).value;
		String inKey8_3 = inRrc8.records.get(2).key;
		String inVal8_3 = (String) inRrc8.records.get(2).value;
		String inKey8_4 = inRrc8.records.get(3).key;
		String inVal8_4 = (String) inRrc8.records.get(3).value;
		
		assertEquals("Test-FE-8-1: mastersthesis key incorrect from pasring", key8, "mastersthesis");
		assertEquals("Test-FE-8-2: mastersthesis attribute key incorrect from pasring", attKey8, "key");
		assertEquals("Test-FE-8-3: mastersthesis attribute value incorrect from pasring", attVal8, "phd/Schulte92");
		assertEquals("Test-FE-8-4: mastersthesis value's author key incorrect from pasring", inKey8_1, "author");
		assertEquals("Test-FE-8-5: mastersthesis value's author value incorrect from pasring", inVal8_1, "Christian Schulte");
		assertEquals("Test-FE-8-6: mastersthesis value's title key incorrect from pasring", inKey8_2, "title");
		assertEquals("Test-FE-8-7: mastersthesis value's title value incorrect from pasring", inVal8_2, "Entwurf und Implementierung eines ");
		assertEquals("Test-FE-8-8: mastersthesis value's year key incorrect from pasring", inKey8_3, "year");
		assertEquals("Test-FE-8-9: mastersthesis value's year value incorrect from pasring", inVal8_3, "1991");
		assertEquals("Test-FE-8-10: mastersthesis value's school key incorrect from pasring", inKey8_4, "school");
		assertEquals("Test-FE-8-11: mastersthesis value's school value incorrect from pasring", inVal8_4, "Universit");
		
	}

}
