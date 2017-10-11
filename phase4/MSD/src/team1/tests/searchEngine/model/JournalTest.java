package team1.tests.searchEngine.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import junit.framework.TestCase;
import team1.searchengine.database.preProcessor.JournalPopulator;
import team1.searchengine.model.Author;
import team1.searchengine.model.Journal;

public class JournalTest extends TestCase {
	Journal j1, j2;
	Author author;
	ArrayList<Author> authors;
	Journal journal;
	ArrayList<Journal> journals = new ArrayList<Journal>();

	@Override
	protected void setUp() throws Exception {
		j1 = new Journal();
		author = new Author("test", "ut", "no note", true, true);
		authors = new ArrayList<Author>();
		authors.add(author);
		journal = new Journal(null, "CLoud computing", authors, 2, 2002, "OOISBN01", "Openstack: nova cloud", "oriely",
				"www.cloudComputing.com", "xyz", "computing in cloud", "this is seriesurl", null);

	}

	@Test
	public void testJoruanlPopulateTest() {
		String stringJournal = journal.getJsonForm();
		assertNotNull(stringJournal);
	}

	@Test
	public void test() {
		String stringJournal = journal.getJsonForm();
		assertNotNull(stringJournal);
	}
	
	@Test
	public void testConvertToObjFromString() {
		String stringJournal = journal.getJsonForm();
		Journal jrnl = Journal.createObjFromString(stringJournal);
		assertEquals(journal.getBookTitle(), jrnl.getBookTitle());
	}
	
	@Test
	public void testgetPopulatorTest(){
		JournalPopulator jp = j1.getPopulator();
		assertTrue(jp instanceof JournalPopulator);
	}
}
