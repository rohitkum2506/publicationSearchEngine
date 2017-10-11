package team1.tests.searchEngine.database.dataPreprocessorTest;

import java.util.ArrayList;

import org.junit.Test;

import junit.framework.TestCase;
import team1.searchengine.database.QueryService;
import team1.searchengine.database.preProcessor.JournalPopulator;
import team1.searchengine.model.Author;
import team1.searchengine.model.Journal;

public class ConferencePopulatorTest extends TestCase {
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
	public void testjournalPopulatorTest() {
		JournalPopulator ap = null;
		try {
			ap = new JournalPopulator(new QueryService());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ap.populate("test_dblp", journal);
		assertEquals("journal", "journal");
	}

	@Test
	public void testjournalsPopulatorTest() {
		JournalPopulator ap = null;
		try {
			ap = new JournalPopulator(new QueryService());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		journals.add(journal);
		journals.add(j1);
		ap.populate("test_dblp", journals);
		assertEquals("journals", "journals");
	}
	
	@Test
	public void testBulkJournalsPopulatorTest() {
		JournalPopulator ap = null;
		try {
			ap = new JournalPopulator(new QueryService());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		journals.add(journal);
		journals.add(j1);
		Boolean res = ap.populateBulk("test_dblp", journals);
		assertTrue(res);
	}
}
