package team1.tests.searchEngine.database.dataPreprocessorTest;

import java.util.ArrayList;

import org.junit.Test;

import junit.framework.TestCase;
import team1.searchengine.database.QueryService;
import team1.searchengine.database.preProcessor.ConferencePublicationPopulator;
import team1.searchengine.database.preProcessor.JournalPopulator;
import team1.searchengine.model.Author;
import team1.searchengine.model.ConferencePublication;
import team1.searchengine.model.Journal;

public class ConferencePublicationPopulatorTest extends TestCase {
	Journal j1, j2;
	Author author;
	ArrayList<Author> authors;
	Journal journal;
	ArrayList<Journal> journals = new ArrayList<Journal>();
	ConferencePublication cp;
	ConferencePublicationPopulator cpp;
	
	@Override
	protected void setUp() throws Exception {
		QueryService qs = new QueryService();
		cpp =  new  ConferencePublicationPopulator(qs);
		cp = new ConferencePublication();
	}

	@Test
	public void testPopulatorTest() {
		JournalPopulator ap = null;
		
		boolean res= cpp.populate("test_dblp", cp);
		assertTrue(res);
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
		boolean res = ap.populate("test_dblp", journals);
		assertFalse(false);
	}
	
	@Test
	public void testConferencePublicationPopulatorTest() {
		ConferencePublicationPopulator ap = null;
		ConferencePublication cPublication = new ConferencePublication();
		cPublication.setBookTitle("ConferencePublicationPopulator");
		ArrayList<ConferencePublication> cpubs = new ArrayList<>();
		cpubs.add(cPublication);
		try {
			ap = new ConferencePublicationPopulator(new QueryService());
		} catch (Exception e) {
			e.printStackTrace();
		}
		boolean res = ap.populateBulk("test_dblp", cpubs);
		assertTrue(res);
	}
}
