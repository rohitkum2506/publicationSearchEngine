package team1.tests.searchEngine.database.dataPreprocessorTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.junit.Test;

import junit.framework.TestCase;
import team1.searchengine.database.ElasticSearchClient;
import team1.searchengine.database.QueryService;
import team1.searchengine.database.preProcessor.AuthorPopulator;
import team1.searchengine.database.preProcessor.JournalPopulator;
import team1.searchengine.model.Author;
import team1.searchengine.model.Journal;
import team1.tests.searchEngine.database.TestDataManager;

public class JournalPopulatorTest extends TestCase {
	Journal j1, j2;
	Author author;
	ArrayList<Author> authors;
	Journal journal;
	ArrayList<Journal> journals = new ArrayList<Journal>();
	TestDataManager manager = new TestDataManager();

	@Override
	protected void setUp() throws Exception {
		manager.deleteIndex("test_dblp");
		j1 = new Journal();
		author = new Author("Jane", "ut", "no note", true, true);
		authors = new ArrayList<Author>();
		authors.add(author);
		journal = new Journal(null, "CLoud computing", authors, 2, 2002, "OOISBN01", "Openstack: nova cloud", "oriely",
				"www.cloudComputing.com", "xyz", "computing in cloud", "this is seriesurl", null);

	}

	@Test
	public void testjournalPopulatorTest() {
		QueryService qs = null;
		try {
			qs = new QueryService();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JournalPopulator ap = new JournalPopulator(qs);
		boolean res = ap.populate("test_dblp", journal);
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
		assertTrue(res);
	}

}
