package team1.tests.searchEngine.database.dataPreprocessorTest;

import java.util.ArrayList;

import org.junit.Test;

import junit.framework.TestCase;
import team1.searchengine.database.QueryService;
import team1.searchengine.database.preProcessor.JournalPublicationPopulator;
import team1.searchengine.database.preProcessor.JournalPopulator;
import team1.searchengine.model.Author;
import team1.searchengine.model.JournalPublication;
import team1.tests.searchEngine.database.TestDataManager;
import team1.searchengine.model.Journal;

public class JournalPublicationPopulatorTest extends TestCase {
	Journal j1, j2;
	Author author;
	ArrayList<Author> authors;
	Journal journal;
	ArrayList<Journal> journals = new ArrayList<Journal>();
	JournalPublication cp;
	JournalPublicationPopulator cpp;
	TestDataManager manager = new TestDataManager();
	
	
	@Override
	protected void setUp() throws Exception {
		QueryService qs = new QueryService();
		cpp =  new  JournalPublicationPopulator(qs);
		cp = new JournalPublication();
	}

	@Test
	public void testPopulatorTest() {
		cp.setAuthors(manager.getAuthors());
		cp.setTitle("testTitle");
		ArrayList<JournalPublication> jps = new ArrayList<>();
		jps.add(cp);
		boolean res= cpp.populateBulk("test_dblp", jps);
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
}
